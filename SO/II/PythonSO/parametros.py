#!/usr/bin/python3
# /tmp/script.py

import sys

if len(sys.argv) > 1:
    print("Parâmetro 1 passado no terminal:", sys.argv[1])
else:
    print("Nenhum parâmetro foi passado.")