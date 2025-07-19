#!/usr/bin/python3 
# /tmp/abrirarquivo.py

import os; 

fd = os.open("/tmp/exemplo.txt", os.O_RDONLY | os.O_CREAT ); 
os.lseek(fd, 0, 0); 
bytes_arquivo = os.read(fd, os.path.getsize(fd)); 
print(bytes_arquivo.decode()); 
os.close(fd);