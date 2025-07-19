#!/usr/bin/python3
#/tmp/exemplo.py
# An example of using the argparse module to handle command line arguments.

import argparse

parser = argparse.ArgumentParser (description='An example of using the argparse module.');

parser.add_argument('-e', '--example', nargs='?', const='constant value', default='default value');
args = parser.parse_args();

print("0 value is:", args.example);