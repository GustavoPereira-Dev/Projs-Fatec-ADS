#!/usr/bin/python3

# Abrir o arquivo /etc/passwd
with open("/etc/passwd", "r") as f:
    linhas = f.readlines()

# Laço para verificar usuários com shell /bin/bash
for linha in linhas:
    partes = linha.strip().split(":")
    if partes[-1] == "/bin/bash":
        print("Usuário:", partes[0])