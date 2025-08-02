#!/usr/bin/python3
#/tmp/pai.py

import subprocess, json;

filho = subprocess.Popen(["python3", "/tmp/filho.py"], stdout=subprocess.PIPE, stderr=subprocess.STDOUT, stdin=subprocess.PIPE);

input = json.dumps({"message" : "hello"});
output_err = filho.communicate(input=input.encode('utf-8'));
print(output_err[0].decode());
