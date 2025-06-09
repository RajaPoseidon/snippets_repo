"use client";

import { useState, useRef, useEffect } from "react";
import { BotIcon, ChevronDownIcon, SendHorizontalIcon, XIcon } from "lucide-react";

interface Message {
  id: string;
  content: string;
  type: 'user' | 'assistant';
  timestamp: Date;
}

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

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const getMockResponse = (userMessage: string) => {
    const randomResponse = mockResponses[Math.floor(Math.random() * mockResponses.length)];
    return `${randomResponse}\n\nRegarding your message: "${userMessage}"`;
  };

  const simulateAssistantResponse = async (userMessage: string) => {
    setIsLoading(true);
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
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="fixed bottom-4 right-4 size-11 grid place-items-center rounded-full bg-primary text-primary-foreground shadow-lg transition-transform hover:scale-110 active:scale-90"
      >
        <BotIcon
          className={`absolute size-6 transition-all ${
            isOpen ? "rotate-90 scale-0" : "rotate-0 scale-100"
          }`}
        />
        <ChevronDownIcon
          className={`absolute size-6 transition-all ${
            isOpen ? "rotate-0 scale-100" : "-rotate-90 scale-0"
          }`}
        />
      </button>

      {isOpen && (
        <div 
          className="fixed inset-0" 
          onClick={(e) => e.target === e.currentTarget && setIsOpen(false)}
        >
          <div className="absolute inset-0 bg-black/20" />
          
          <div className="fixed bottom-20 right-4 flex h-[500px] w-[400px] flex-col overflow-hidden rounded-xl border bg-white dark:bg-slate-950 shadow-lg">
            <div className="flex h-14 items-center justify-between border-b border-gray-200 dark:border-gray-800 px-4">
              <h2 className="font-semibold">Assistant</h2>
              <button 
                onClick={() => setIsOpen(false)}
                className="grid place-items-center rounded-full p-2 text-gray-500 hover:text-gray-700 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-gray-200 dark:hover:bg-slate-800 transition-colors"
              >
                <XIcon className="size-4" />
              </button>
            </div>

            <div className="flex-1 overflow-y-auto p-4 scroll-smooth" style={{ scrollbarWidth: 'thin' }}>
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
                <div ref={messagesEndRef} />
              </div>
            </div>

            <div className="sticky bottom-0 mt-3 w-full bg-white dark:bg-slate-950 pb-4">
              <form 
                onSubmit={handleSubmit}
                className="mx-4"
              >
                <div className="flex w-full items-end rounded-lg border bg-white dark:bg-slate-950 px-2.5 
                  border-gray-200 dark:border-gray-800
                  transition-all duration-300 ease-in-out
                  focus-within:border-blue-600 
                  focus-within:ring-4 
                  focus-within:ring-blue-600/40
                  focus-within:shadow-[0_0_8px_rgba(37,99,235,0.5)]">
                  <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="Write a message..."
                    className="max-h-40 flex-grow resize-none border-none bg-transparent px-2 py-4 text-sm outline-none 
                      placeholder:text-gray-500 dark:placeholder:text-gray-400
                      focus:outline-none focus:ring-0
                      selection:bg-blue-600/20"
                    disabled={isLoading}
                  />
                  <button 
                    type="submit"
                    className="my-2.5 grid place-items-center size-8 p-2 
                      text-blue-600 hover:text-blue-700 
                      transition-all duration-200 ease-in
                      disabled:opacity-50 disabled:cursor-not-allowed"
                    disabled={!message.trim() || isLoading}
                  >
                    <SendHorizontalIcon className="size-4" />
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default SimpleAssistantModal;