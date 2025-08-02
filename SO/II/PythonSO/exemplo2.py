#!/usr/bin/python3
# /tmp/exemplo2.py
# Um exemplo de como usar o módulo argparse para tratar argumentos da linha de comando

import argparse

parser = argparse.ArgumentParser(description='Exemplo de uso do módulo argparse.')
parser.add_argument('-f', '--file', required=True, help="Informe o caminho do arquivo")

args = parser.parse_args()

print("Caminho do arquivo:", args.file)