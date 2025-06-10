"use client";

import { useState, useRef, useEffect } from "react";
import { BotIcon, SendHorizontalIcon, XIcon } from "lucide-react";
import { Source_Code_Pro } from 'next/font/google';

const sourceCode = Source_Code_Pro({ 
  subsets: ['latin'],
  weight: ['400', '500', '600']
});

interface Message {
  id: string;
  content: string;
  type: 'user' | 'assistant';
  timestamp: Date;
  status?: 'pending' | 'complete' | 'error';
  attachments?: any[];
}

const SimpleAssistantModal = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState<Message[]>([]);
  const [currentConversationId, setCurrentConversationId] = useState<string | null>(null);
  const workerRef = useRef<Worker | null>(null);
  const messagesEndRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // Initialize worker
    if (typeof window !== 'undefined') {
      workerRef.current = new Worker(new URL('./genie-worker.ts', import.meta.url));
      
      // Initialize Genie API in worker
      workerRef.current.postMessage({
        type: 'START_CONVERSATION',
        payload: {
          config: {
            baseUrl: process.env.NEXT_PUBLIC_DATABRICKS_URL,
            authToken: process.env.NEXT_PUBLIC_DATABRICKS_TOKEN,
            spaceId: process.env.NEXT_PUBLIC_GENIE_SPACE_ID,
          }
        }
      });

      // Handle worker messages
      workerRef.current.onmessage = (event) => {
        const { type, payload } = event.data;

        switch (type) {
          case 'CONVERSATION_STARTED':
            setCurrentConversationId(payload.conversation.id);
            handleWorkerResponse(payload);
            break;

          case 'RESULTS_RECEIVED':
            handleWorkerResponse(payload);
            break;

          case 'MESSAGE_SENT':
            handleWorkerResponse(payload);
            break;

          case 'ERROR':
            console.error('Worker error:', payload);
            break;
        }
      };
    }

    return () => {
      workerRef.current?.terminate();
    };
  }, []);

  const handleWorkerResponse = (response: any) => {
    if (response.status === 'COMPLETED' && response.attachments) {
      setMessages(prev => prev.map(msg => 
        msg.id === response.id 
          ? { ...msg, status: 'complete', attachments: response.attachments }
          : msg
      ));
    }
  };

  const pollResults = async (conversationId: string, messageId: string) => {
    const pollInterval = setInterval(() => {
      workerRef.current?.postMessage({
        type: 'GET_RESULTS',
        payload: { conversationId, messageId }
      });
    }, 5000); // Poll every 5 seconds

    // Clear polling after 10 minutes
    setTimeout(() => clearInterval(pollInterval), 600000);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!message.trim()) return;

    const userMessage: Message = {
      id: Date.now().toString(),
      content: message.trim(),
      type: 'user',
      timestamp: new Date(),
      status: 'pending'
    };

    setMessages(prev => [...prev, userMessage]);
    setMessage('');

    if (!currentConversationId) {
      // Start new conversation
      workerRef.current?.postMessage({
        type: 'START_CONVERSATION',
        payload: { content: userMessage.content }
      });
    } else {
      // Send message in existing conversation
      workerRef.current?.postMessage({
        type: 'SEND_MESSAGE',
        payload: {
          conversationId: currentConversationId,
          content: userMessage.content
        }
      });
    }
  };

  return (
    <div className={sourceCode.className}>
      {/* Your existing UI code here */}
      <div className="messages-area">
        {messages.map((msg) => (
          <div
            key={msg.id}
            className={`message ${
              msg.type === 'user' ? 'user-message' : 'assistant-message'
            }`}
          >
            <div className="message-content">
              {msg.content}
              {msg.status === 'pending' && <span className="loading">...</span>}
              {msg.attachments && (
                <div className="attachments">
                  {msg.attachments.map((attachment, index) => (
                    <div key={index} className="attachment">
                      {attachment.type === 'query' && (
                        <pre className="query-code">{attachment.content}</pre>
                      )}
                      {attachment.type === 'result' && (
                        <div className="query-result">{attachment.content}</div>
                      )}
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        ))}
        <div ref={messagesEndRef} />
      </div>

      {/* Input area */}
      <div className="input-area">
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            placeholder="Ask a question..."
            className="input-field"
          />
          <button type="submit" className="send-button">
            <SendHorizontalIcon />
          </button>
        </form>
      </div>
    </div>
  );
};

export default SimpleAssistantModal;