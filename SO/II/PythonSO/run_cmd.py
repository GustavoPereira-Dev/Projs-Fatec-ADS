#!/usr/bin/python3
#/tmp/run_cmd.py

import subprocess;

process = subprocess.run(['ls', '/','-lha'], stdout=subprocess.PIPE, universal_newlines=True);

# aqui tem o output capturado

output = process.stdout;

for line in output.splitlines(): 
    print("Linha: ", line);

print("fim");