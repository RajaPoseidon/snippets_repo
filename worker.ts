// genie-worker.ts
type GenieMessage = {
    type: 'START_CONVERSATION' | 'GET_RESULTS' | 'SEND_MESSAGE';
    payload: any;
  };
  
  class GenieAPI {
    private baseUrl: string;
    private authToken: string;
    private spaceId: string;
  
    constructor(baseUrl: string, authToken: string, spaceId: string) {
      this.baseUrl = baseUrl;
      this.authToken = authToken;
      this.spaceId = spaceId;
    }
  
    async startConversation(content: string) {
      const response = await fetch(
        `${this.baseUrl}/api/2.0/genie/spaces/${this.spaceId}/start-conversation`,
        {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${this.authToken}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ content }),
        }
      );
      return await response.json();
    }
  
    async getMessageResults(conversationId: string, messageId: string) {
      const response = await fetch(
        `${this.baseUrl}/api/2.0/genie/spaces/${this.spaceId}/conversations/${conversationId}/messages/${messageId}`,
        {
          headers: {
            'Authorization': `Bearer ${this.authToken}`,
          },
        }
      );
      return await response.json();
    }
  
    async sendMessage(conversationId: string, content: string) {
      const response = await fetch(
        `${this.baseUrl}/api/2.0/genie/spaces/${this.spaceId}/conversations/${conversationId}/messages`,
        {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${this.authToken}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ content }),
        }
      );
      return await response.json();
    }
  }
  
  // Worker context
  const ctx: Worker = self as any;
  let genieAPI: GenieAPI;
  
  // Handle messages from the main thread
  ctx.addEventListener('message', async (event: MessageEvent<GenieMessage>) => {
    const { type, payload } = event.data;
  
    try {
      switch (type) {
        case 'START_CONVERSATION':
          if (!genieAPI) {
            const { baseUrl, authToken, spaceId } = payload.config;
            genieAPI = new GenieAPI(baseUrl, authToken, spaceId);
          }
          const conversation = await genieAPI.startConversation(payload.content);
          ctx.postMessage({ type: 'CONVERSATION_STARTED', payload: conversation });
          break;
  
        case 'GET_RESULTS':
          const results = await genieAPI.getMessageResults(
            payload.conversationId,
            payload.messageId
          );
          ctx.postMessage({ type: 'RESULTS_RECEIVED', payload: results });
          break;
  
        case 'SEND_MESSAGE':
          const message = await genieAPI.sendMessage(
            payload.conversationId,
            payload.content
          );
          ctx.postMessage({ type: 'MESSAGE_SENT', payload: message });
          break;
      }
    } catch (error) {
      ctx.postMessage({ type: 'ERROR', payload: error.message });
    }
  });