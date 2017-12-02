import socket
import sys
import time
import serial

from thread import *

HOST = ''
PORT = 65524

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'Socket created'

#Bind socket to local host and port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()

print 'Socket bind complete'

s.listen(10)
print 'Socket now listening'
ser = serial.Serial('/dev/cu.usbmodem1421', 9600)
#Function for handling connections. This will be used to create threads
def clientthread(conn):
    #Sending message to connected client
    while True:
            conn.send(ser.readline())
            time.sleep(.30)

    #came out of loop
    conn.close()

#now keep talking with the client
while 1:
    #wait to accept a connection - blocking call
    conn, addr = s.accept()
    print 'Connected with ' + addr[0] + ':' + str(addr[1])

    start_new_thread(clientthread ,(conn,))

s.close()
