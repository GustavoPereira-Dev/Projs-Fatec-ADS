#!/bin/bash
#estSeq23.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2
read -p "Digite o terceiro número: " num3
read -p "Digite o quarto número: " num4


if [ "$num4" -gt "$num3" ] then
  echo "A sequência dos números é na seguinte forma: ${num1} ${num2} ${num3} ${num4} "
elif [ "$num4" -gt "$num2" && "$num4" -lt "$num3" ] then
   echo "A sequência dos números é na seguinte forma: ${num1} ${num2} ${num4} ${num3} "
elif [ "$num4" -gt "$num1" && "$num4" -lt "$num2" ] then
    echo "A sequência dos números é na seguinte forma: ${num1} ${num4} ${num2} ${num3}"
else 
    echo "A sequência dos números é na seguinte forma: ${num4} ${num1} ${num2} ${num3}"
fi