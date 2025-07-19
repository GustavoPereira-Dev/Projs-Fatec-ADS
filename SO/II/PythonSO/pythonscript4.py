#!/usr/bin/python3
import sys

# Recebe o argumento da posição 1
valor = sys.argv[1]

# Salva o valor no arquivo /tmp/argumento.txt
with open("/tmp/argumento.txt", "w") as f:
    f.write(valor + "\n")