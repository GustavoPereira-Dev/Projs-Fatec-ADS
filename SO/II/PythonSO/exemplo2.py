#!/usr/bin/python3
#/tmp/exemplo.py
#An example of using the argparse module to handle command line arguments.

import argparse;

parser = argparse.ArgumentParser (description='An example of using the argparse module.');

parser.add_argument('a', '--file', required=True, help="Enter the file path");
args = parser.parse_args();

print("0 file is:", args.file);