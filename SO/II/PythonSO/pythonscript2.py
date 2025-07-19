#!/usr/bin/python3
import hashlib
import sys

# Recebe o primeiro argumento
entrada = sys.argv[1]

# Gera o hash MD5
md5_hash = hashlib.md5(entrada.encode()).hexdigest()

# Imprime o resultado
print("MD5:", md5_hash)
