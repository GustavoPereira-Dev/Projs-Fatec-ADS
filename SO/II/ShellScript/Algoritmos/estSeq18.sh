#!/bin/bash
#estSeq18.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2

dValor = 0

if [[ "$num1" -gt "$num2" ]]; then
    dValor = $(($num1 - $num2))
else
    dValor = $(($num2 - $num1))
fi

echo "A diferença entre os dois números é de ${dValor}"