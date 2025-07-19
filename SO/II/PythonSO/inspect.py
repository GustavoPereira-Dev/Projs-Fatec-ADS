#!/usr/bin/python3
# Displays the script path
#/tmp/inspect.py

import os, inspect;

SCRIPT_PATH = os.path.abspath( inspect.getfile(inspect.currentframe()));
SCRIPT_DIR = os.path.dirname(SCRIPT_PATH );
SEGUNDO_SCRIPT_PATH = os.path.join( SCRIPT_DIR, "segundo.py");
print("The path of the second script is:", SEGUNDO_SCRIPT_PATH);

if os.path.exists( SEGUNDO_SCRIPT_PATH ):
    print("file exists");
else:
    print("the file does NOT exist");

