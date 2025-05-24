#!/usr/bin/python3

import os

print(os.name) # imprimir padrão (posix para linux/unix e nt para windows)
print(os.uname()) # distro

# variaveis de ambiente
print(os.getenv("LOGNAME"))
print(os.environ['LOGNAME']))

# diretório e arquivo
print(os.getcwd())
os.chdir("/tmp/")
os.chdir(chmod("/tmp/meuarquivo.txt", stat.S_IWRITE + stat.S_IREAD + stat.S_IRGRP) # permissão de arquivo
os.chown("/tmp/meuarquivo.txt", uid, gid)
print(os.path.expanduser('~'))
print(os.path.abspath(os.sep))
         
