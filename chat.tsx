"use client";

import { useState } from "react";
import { BotIcon, ChevronDownIcon, SendHorizontalIcon, XIcon } from "lucide-react";

const SimpleAssistantModal = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [message, setMessage] = useState('');

  const handleBackdropClick = (e: React.MouseEvent) => {
    // Stop if clicking on the modal itself
    if (e.target === e.currentTarget) {
      setIsOpen(false);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (message.trim()) {
      // Handle message submission
      setMessage('');
    }
  };

  return (
    <>
      {/* Floating Button */}
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

      {/* Modal Container */}
      {isOpen && (
        <div 
          className="fixed inset-0 bg-black/20" 
          onClick={handleBackdropClick}
        >
          {/* Chat Window */}
          <div 
            className="fixed bottom-20 right-4 flex h-[500px] w-[400px] flex-col overflow-hidden rounded-xl border bg-background shadow-lg"
          >
            {/* Header */}
            <div className="flex h-14 items-center justify-between border-b px-4">
              <h2 className="font-semibold">Assistant</h2>
              <button 
                onClick={() => setIsOpen(false)}
                className="grid place-items-center rounded-full p-2 hover:bg-gray-100"
              >
                <XIcon className="size-4" />
              </button>
            </div>

            {/* Messages Area */}
            <div className="flex-1 overflow-y-auto p-4">
              {/* Welcome Message */}
              <div className="text-center">
                <p className="text-lg font-medium">How can I help you today?</p>
                <div className="mt-4 space-y-2">
                  <button className="w-full rounded-lg border p-2 text-sm hover:bg-accent">
                    What is the weather in Tokyo?
                  </button>
                  <button className="w-full rounded-lg border p-2 text-sm hover:bg-accent">
                    Tell me a joke
                  </button>
                </div>
              </div>

              {/* Example Messages */}
              <div className="mt-4 space-y-4">
                <div className="flex justify-end">
                  <div className="max-w-[80%] rounded-lg bg-muted px-4 py-2">
                    Hello! How are you?
                  </div>
                </div>

                <div className="flex justify-start">
                  <div className="max-w-[80%] rounded-lg bg-muted px-4 py-2">
                    I'm doing great! How can I assist you today?
                  </div>
                </div>
              </div>
            </div>

            {/* Input Area */}
            <div className="sticky bottom-0 mt-3 w-full pb-4">
              <form 
                onSubmit={handleSubmit}
                className="mx-4"
              >
                <div className="focus-within:border-ring/20 flex w-full items-end rounded-lg border bg-inherit px-2.5 shadow-sm transition-colors ease-in">
                  <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="Write a message..."
                    className="placeholder:text-muted-foreground max-h-40 flex-grow resize-none border-none bg-transparent px-2 py-4 text-sm outline-none focus:ring-0"
                  />
                  <button 
                    type="submit"
                    className="my-2.5 grid place-items-center size-8 p-2 text-primary hover:text-primary/80 transition-opacity ease-in disabled:opacity-50"
                    disabled={!message.trim()}
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