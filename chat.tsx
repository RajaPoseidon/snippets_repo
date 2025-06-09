"use client";

import { useState, useRef, useEffect } from "react";
import { BotIcon, ChevronDownIcon, SendHorizontalIcon, XIcon } from "lucide-react";

interface Message {
  id: string;
  content: string;
  type: 'user' | 'assistant';
  timestamp: Date;
}

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

  const getRandomResponse = (): string => {
    const responses = [
      "I'd be happy to help you with that!",
      "That's an interesting question. Let me explain...",
      "Based on my understanding, here's what I think...",
      "Here's what I found about that topic...",
      "Let me break this down for you...",
    ];
    return responses[Math.floor(Math.random() * responses.length)];
  };

  const handleBackdropClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.target === e.currentTarget) {
      setIsOpen(false);
    }
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!message.trim()) return;

    // Add user message
    const userMessage: Message = {
      id: Date.now().toString(),
      content: message.trim(),
      type: 'user',
      timestamp: new Date(),
    };
    setMessages(prev => [...prev, userMessage]);
    setMessage('');
    setIsLoading(true);

    // Simulate API delay and add assistant response
    setTimeout(() => {
      const assistantMessage: Message = {
        id: (Date.now() + 1).toString(),
        content: getRandomResponse(),
        type: 'assistant',
        timestamp: new Date(),
      };
      setMessages(prev => [...prev, assistantMessage]);
      setIsLoading(false);
    }, 1000);
  };

  return (
    <>
      {/* Floating Button */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="fixed bottom-4 right-4 size-11 grid place-items-center rounded-full bg-blue-600 text-white shadow-lg transition-transform hover:scale-110 active:scale-90"
      >
        <BotIcon className={`size-6 transition-all ${isOpen ? "scale-0" : "scale-100"}`} />
        <XIcon className={`absolute size-6 transition-all ${isOpen ? "scale-100" : "scale-0"}`} />
      </button>

      {/* Modal */}
      {isOpen && (
        <div className="fixed inset-0" onClick={handleBackdropClick}>
          {/* Backdrop */}
          <div className="absolute inset-0 bg-black/20" />

          {/* Chat Window */}
          <div 
            className="fixed bottom-20 right-4 flex h-[500px] w-[400px] flex-col overflow-hidden rounded-xl border bg-white shadow-lg"
            onClick={(e) => e.stopPropagation()}
          >
            {/* Header */}
            <div className="flex h-14 items-center justify-between border-b px-4">
              <span className="font-semibold">Assistant</span>
              <button
                onClick={() => setIsOpen(false)}
                className="rounded-full p-2 hover:bg-gray-100"
              >
                <XIcon className="size-5" />
              </button>
            </div>

            {/* Messages Area */}
            <div className="flex-1 overflow-y-auto p-4">
              <div className="flex flex-col space-y-4">
                {messages.map((msg) => (
                  <div
                    key={msg.id}
                    className={`flex ${msg.type === 'user' ? 'justify-end' : 'justify-start'}`}
                  >
                    <div
                      className={`rounded-lg px-4 py-2 max-w-[80%] ${
                        msg.type === 'user'
                          ? 'bg-blue-600 text-white'
                          : 'bg-gray-100 text-gray-900'
                      }`}
                    >
                      {msg.content}
                    </div>
                  </div>
                ))}
                {isLoading && (
                  <div className="flex justify-start">
                    <div className="bg-gray-100 rounded-lg px-4 py-2">
                      Typing...
                    </div>
                  </div>
                )}
                <div ref={messagesEndRef} />
              </div>
            </div>

            {/* Input Area */}
            <div className="border-t bg-white p-4">
              <form onSubmit={handleSubmit} className="flex space-x-2">
                <div className="flex flex-1 items-end rounded-lg border bg-white px-3 
                  transition-all duration-300 ease-in-out
                  focus-within:border-blue-600 
                  focus-within:ring-4 
                  focus-within:ring-blue-600/40">
                  <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="Write a message..."
                    className="flex-1 resize-none border-0 bg-transparent py-3 focus:outline-none"
                  />
                  <button
                    type="submit"
                    disabled={!message.trim() || isLoading}
                    className="ml-2 rounded-md p-2 text-gray-500 hover:text-blue-600 disabled:opacity-50"
                  >
                    <SendHorizontalIcon className="size-5" />
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