"use client";

import { useState, useRef, useEffect } from "react";
import { BotIcon, ChevronDownIcon, SendHorizontalIcon, XIcon } from "lucide-react";

interface Message {
  id: string;
  content: string;
  type: 'user' | 'assistant';
  timestamp: Date;
}

// Mock responses
const mockResponses = [
  "I'd be happy to help you with that!",
  "That's an interesting question. Let me explain...",
  "Based on my understanding, here's what I think...",
  "Here's what I found about that topic...",
  "Let me break this down for you...",
];

const SimpleAssistantModal = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState<Message[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const messagesEndRef = useRef<HTMLDivElement>(null);

  // Scroll to bottom when new messages arrive
  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  // Get random mock response
  const getMockResponse = (userMessage: string) => {
    const randomResponse = mockResponses[Math.floor(Math.random() * mockResponses.length)];
    return `${randomResponse}\n\nRegarding your message: "${userMessage}"\n\nHere's some additional context to make the response longer and show the scrolling behavior. This will help demonstrate how the chat bubbles appear in a diagonal pattern and how the scroll works.`;
  };

  const simulateAssistantResponse = async (userMessage: string) => {
    setIsLoading(true);
    // Simulate typing delay (1-2 seconds)
    await new Promise(resolve => setTimeout(resolve, 1000 + Math.random() * 1000));
    
    const response = getMockResponse(userMessage);
    const assistantMessage: Message = {
      id: Date.now().toString(),
      content: response,
      type: 'assistant',
      timestamp: new Date()
    };
    
    setMessages(prev => [...prev, assistantMessage]);
    setIsLoading(false);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!message.trim()) return;

    const userMessage: Message = {
      id: Date.now().toString(),
      content: message.trim(),
      type: 'user',
      timestamp: new Date()
    };
    
    setMessages(prev => [...prev, userMessage]);
    setMessage('');

    await simulateAssistantResponse(message.trim());
  };

  return (
    <>
      {/* ... Floating Button code remains the same ... */}

      {/* Modal Container */}
      {isOpen && (
        <div 
          className="fixed inset-0" 
          onClick={(e) => e.target === e.currentTarget && setIsOpen(false)}
        >
          <div className="absolute inset-0 bg-black/20" />
          
          {/* Chat Window */}
          <div 
            className="fixed bottom-20 right-4 flex h-[500px] w-[400px] flex-col overflow-hidden rounded-xl border bg-white dark:bg-slate-950 shadow-lg"
          >
            {/* Header remains the same */}

            {/* Messages Area with improved scroll behavior */}
            <div className="flex-1 overflow-y-auto p-4 scroll-smooth" style={{ scrollbarWidth: 'thin' }}>
              {messages.length === 0 ? (
                // Welcome Message remains the same
              ) : (
                // Message List with improved layout
                <div className="space-y-6">
                  {messages.map((msg, index) => (
                    <div 
                      key={msg.id} 
                      className={`flex ${msg.type === 'user' ? 'justify-end' : 'justify-start'} ${
                        index > 0 && messages[index - 1].type === msg.type ? 'mt-2' : 'mt-6'
                      }`}
                    >
                      <div 
                        className={`
                          max-w-[80%] rounded-2xl px-4 py-2 
                          ${msg.type === 'user' 
                            ? 'bg-blue-600 text-white rounded-br-none' 
                            : 'bg-gray-100 dark:bg-slate-800 rounded-bl-none'
                          }
                          ${index > 0 && messages[index - 1].type === msg.type 
                            ? msg.type === 'user' ? 'rounded-tr-lg' : 'rounded-tl-lg'
                            : ''
                          }
                        `}
                      >
                        <div className="whitespace-pre-wrap">{msg.content}</div>
                        <div 
                          className={`text-xs mt-1 ${
                            msg.type === 'user' 
                              ? 'text-blue-200' 
                              : 'text-gray-500 dark:text-gray-400'
                          }`}
                        >
                          {msg.timestamp.toLocaleTimeString([], { 
                            hour: '2-digit', 
                            minute: '2-digit' 
                          })}
                        </div>
                      </div>
                    </div>
                  ))}
                  {isLoading && (
                    <div className="flex justify-start">
                      <div className="max-w-[80%] rounded-2xl bg-gray-100 dark:bg-slate-800 rounded-bl-none px-4 py-2">
                        <div className="flex space-x-2">
                          <div className="h-2 w-2 rounded-full bg-gray-400 animate-bounce [animation-delay:-0.3s]"></div>
                          <div className="h-2 w-2 rounded-full bg-gray-400 animate-bounce [animation-delay:-0.15s]"></div>
                          <div className="h-2 w-2 rounded-full bg-gray-400 animate-bounce"></div>
                        </div>
                      </div>
                    </div>
                  )}
                  <div ref={messagesEndRef} /> {/* Scroll anchor */}
                </div>
              )}
            </div>

            {/* Input Area remains the same */}
          </div>
        </div>
      )}
    </>
  );
};

export default SimpleAssistantModal;