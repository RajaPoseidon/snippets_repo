from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import Optional, List, Dict, Any
import httpx
import anyio
from fastapi.middleware.cors import CORSMiddleware
from dotenv import load_dotenv
import os

# Load environment variables
load_dotenv()

# Environment variables
DATABRICKS_INSTANCE = os.getenv("DATABRICKS_INSTANCE")
GENIE_SPACE_ID = os.getenv("GENIE_SPACE_ID")
AUTH_TOKEN = os.getenv("AUTH_TOKEN")
API_TIMEOUT = int(os.getenv("API_TIMEOUT", "30"))
MAX_POLLING_ATTEMPTS = int(os.getenv("MAX_POLLING_ATTEMPTS", "120"))
POLLING_INTERVAL = int(os.getenv("POLLING_INTERVAL", "5"))

# Validate required environment variables
if not all([DATABRICKS_INSTANCE, GENIE_SPACE_ID, AUTH_TOKEN]):
    raise ValueError("Missing required environment variables")

# Pydantic Models
class Conversation(BaseModel):
    created_timestamp: int
    id: str
    last_updated_timestamp: int
    space_id: str
    title: str
    user_id: int

class Message(BaseModel):
    attachments: Optional[List[Dict[str, Any]]] = None
    content: str
    conversation_id: str
    created_timestamp: int
    error: Optional[str] = None
    id: str
    last_updated_timestamp: int
    query_result: Optional[Dict[str, Any]] = None
    space_id: str
    status: str
    user_id: int

class StartConversationResponse(BaseModel):
    conversation: Conversation
    message: Message

class MessageRequest(BaseModel):
    content: str

class QueryResult(BaseModel):
    attachment_id: str
    content: Dict[str, Any]

# FastAPI App
app = FastAPI(title="Genie API Proxy")

# CORS Middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# HTTP Client Factory
async def get_httpx_client():
    async with httpx.AsyncClient(
        base_url=DATABRICKS_INSTANCE,
        headers={"Authorization": f"Bearer {AUTH_TOKEN}"},
        timeout=API_TIMEOUT
    ) as client:
        yield client

# API Routes
@app.post("/api/start-conversation", response_model=StartConversationResponse)
async def start_conversation(request: MessageRequest):
    async with httpx.AsyncClient() as client:
        try:
            response = await client.post(
                f"{DATABRICKS_INSTANCE}/api/2.0/genie/spaces/{GENIE_SPACE_ID}/start-conversation",
                headers={"Authorization": f"Bearer {AUTH_TOKEN}"},
                json={"content": request.content}
            )
            response.raise_for_status()
            return response.json()
        except httpx.HTTPError as e:
            raise HTTPException(status_code=e.response.status_code, detail=str(e))

@app.get("/api/messages/{conversation_id}/{message_id}", response_model=Message)
async def get_message(conversation_id: str, message_id: str):
    async with httpx.AsyncClient() as client:
        try:
            response = await client.get(
                f"{DATABRICKS_INSTANCE}/api/2.0/genie/spaces/{GENIE_SPACE_ID}/conversations/{conversation_id}/messages/{message_id}",
                headers={"Authorization": f"Bearer {AUTH_TOKEN}"}
            )
            response.raise_for_status()
            return response.json()
        except httpx.HTTPError as e:
            raise HTTPException(status_code=e.response.status_code, detail=str(e))

# Polling helper using anyio
async def poll_message_status(conversation_id: str, message_id: str):
    attempts = 0
    while attempts < MAX_POLLING_ATTEMPTS:
        message = await get_message(conversation_id, message_id)
        if message.status in ["COMPLETED", "FAILED", "CANCELLED"]:
            return message
        
        # Implement exponential backoff after 2 minutes
        if attempts > 24:  # After 2 minutes (24 * 5 seconds)
            wait_time = min(POLLING_INTERVAL * (1.5 ** (attempts - 24)), 30)
        else:
            wait_time = POLLING_INTERVAL
            
        await anyio.sleep(wait_time)  # Using anyio.sleep instead of asyncio.sleep
        attempts += 1
    
    raise HTTPException(status_code=408, detail="Polling timeout")

@app.post("/api/chat")
async def chat(request: MessageRequest):
    response = await start_conversation(request)
    conversation_id = response.conversation.id
    message_id = response.message.id
    
    try:
        result = await poll_message_status(conversation_id, message_id)
        return result
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)