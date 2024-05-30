from fastapi import FastAPI
import uvicorn

from fastapi.middleware.cors import CORSMiddleware
from yahoo_test import total
app = FastAPI()
origins = [
    "http://localhost.tiangolo.com",
    "https://localhost.tiangolo.com",
    "http://localhost",
    "http://localhost:8080",
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
@app.get("/")
def root(data):
    print(data)
    return total(data)

if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)