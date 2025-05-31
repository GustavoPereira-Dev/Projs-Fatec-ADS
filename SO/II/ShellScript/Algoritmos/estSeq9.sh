#!/bin/bash
#estSeq9.sh

read -p "Digite o valor do primeiro número inteiro: " num1
read -p "Digite o valor do segundo número inteiro: " num2

soma = $(($num1 + $num2))

echo "a soma dos dois é: ${soma}"
