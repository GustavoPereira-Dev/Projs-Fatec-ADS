#!/usr/bin/python3
#/tmp/exemplo_banner.py
#Creating a banner for a program

from pyfiglet import Figlet;

f = Figlet(font='slant');

print('\033[92m'); # GREEN COLOR
print(f.renderText('Pereira Dev')) # BANNER
print("\033[00m"); # DEFAULT TERMINAL COLOR STYLE