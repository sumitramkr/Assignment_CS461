import zmq

context = zmq.Context()

print("Connecting to server")
socket = context.socket(zmq.REQ)
socket.connect("tcp://localhost:1000")

for request in range(10):
    print("Sent req", request)
    socket.send(b"Req for file")
    message = socket.recv()
    print("Recieved reply : ", request, "--------->", message)