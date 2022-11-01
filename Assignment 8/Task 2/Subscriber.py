import sys
import zmq

context = zmq.Context()
socket = context.socket(zmq.SUB)

print("Getting updates from server")
socket.connect("tcp://localhost:4262")

zip_filter = sys.argv[1] if len(sys.argv) > 0 else "2"
socket.setsockopt_string(zmq.SUBSCRIBE, zip_filter)

total_temp = 0

for update_nbr in range(5):
    string = socket.recv_string()
    print((f"File Received '{string}' "))