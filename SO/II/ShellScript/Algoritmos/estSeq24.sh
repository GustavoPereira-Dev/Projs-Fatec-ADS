#!/bin/bash
#estSeq24.sh

read -p "Digite o número: " num

if [ "$num" % 2 -eq 0 && "$num" % 3 -eq 0] ; then
 echo "O valor é divisível por 2 e 3"
else
 echo "O valor não é divisível por 2 e 3"
fi