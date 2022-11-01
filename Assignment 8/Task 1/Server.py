import time
import zmq

context = zmq.Context() #creating context object
socket = context.socket(zmq.REP)
socket.bind("tcp://*:1000") #binding socket to port 1000

while True:
    # Wait for request from client
    message = socket.recv()
    print("Received request for :", message)
    time.sleep(1)
    words = message.split()
    socket.send(words[2]) #sending message to client