#!/usr/bin/python3
# /tmp/main.py

import argparse
import subprocess
import os

'''
python3 /tmp/main.py
# (menu interativo aparecerá)

python3 /tmp/main.py --script exemplo2 --args --file /etc/passwd

'''

# Configuração dos scripts
scripts = {
    "abrirarquivo":  { "path": "/tmp/abrirarquivo.py",  "args": [] },
    "criararquivo":  { "path": "/tmp/criararquivo.py",  "args": [] },
    "banner":        { "path": "/tmp/exemplo_banner.py", "args": [] },
    "cores":         { "path": "/tmp/exemplo_cores.py",  "args": [] },
    "exemplo":       { "path": "/tmp/exemplo.py",        "args": ["--example"] },
    "exemplo2":      { "path": "/tmp/exemplo2.py",       "args": ["--file"] },
    "parametros":    { "path": "/tmp/script.py",         "args": ["positional"] },
    "pai":           { "path": "/tmp/pai.py",            "args": [] },
    "hash":          { "path": "/tmp/hash.py",           "args": [] },
    "inspect":       { "path": "/tmp/inspect.py",        "args": [] },
    "libos":         { "path": "/tmp/libos.py",          "args": [] },
    "run_cmd":       { "path": "/tmp/run_cmd.py",        "args": [] },
    "popen_cmd":     { "path": "/tmp/popen_cmd.py",      "args": [] },
}

# Argumentos da linha de comando
parser = argparse.ArgumentParser(description="Executor de scripts Python com validações.")
parser.add_argument("--script", type=str, help="Nome do script para execução")
parser.add_argument("--args", nargs=argparse.REMAINDER, help="Argumentos do script")
args = parser.parse_args()

def mostrar_menu():
    print("\n\033[93m📋 Scripts disponíveis:\033[0m")
    for i, key in enumerate(scripts.keys(), 1):
        print(f"{i}. {key}")
    escolha = int(input("\nDigite o número correspondente: "))
    return list(scripts.keys())[escolha - 1]

def solicitar_argumentos(chave_script):
    config = scripts[chave_script]
    entrada = []
    for arg in config["args"]:
        if arg == "positional":
            valor = input(f"Digite o valor do parâmetro posicional: ")
            entrada.append(valor)
        else:
            valor = input(f"Digite o valor para {arg}: ")
            entrada.extend([arg, valor])
    return entrada

# Escolher script
chave = args.script if args.script else mostrar_menu()
config = scripts.get(chave)

if not config:
    print("\033[91m Script inválido.\033[0m")
    exit(1)

caminho = config["path"]

if not os.path.exists(caminho):
    print("\033[91m Caminho não encontrado: " + caminho + "\033[0m")
    exit(1)

# Preparar comando
comando = ["python3", caminho]
entradas = args.args if args.args else solicitar_argumentos(chave)
comando.extend(entradas)

# Executar
print(f"\n\033[94m Executando: {chave}\033[0m")
try:
    subprocess.run(comando, check=True)
except subprocess.CalledProcessError as e:
    print("\033[91m Erro durante execução:\033[0m", e)