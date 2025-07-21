#!/bin/bash
#estSeq26.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2
		
if [ "$num1" -gt "$num2" && "$num1" % "$num2" -eq 0 || "$num2" -gt "$num1" && "$num2" % "$num1" -eq 0 ] ; then
 echo "O maior número é múltiplo do menor"
else
 echo "O maior número não é múltiplo do menor"
fi