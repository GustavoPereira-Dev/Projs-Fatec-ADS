#!/bin/bash
#estSeq19.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2


if [[ "$num1" -gt "$num2" ]]; then
    echo "o maior número é o primeiro com ${num1}"
else
    echo "o maior número é o segunfo com ${num2}"
fi