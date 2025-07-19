#!/usr/bin/python3  
# /tmp/criararquivo.py

import os;

fd = os.open("/tmp/exemplo.txt", os.O_RDWR | os.O_CREAT ); 
os.write(fd, "Aied é top".encode()); 
os.close(fd);