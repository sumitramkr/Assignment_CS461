import zmq
from random import randrange

context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind("tcp://127.0.0.1:4262")

while True:
    zipcode = randrange(1,5)
    string_to_send = str(zipcode) + " received"
    socket.send_string(string_to_send)