#!/bin/bash
#estSeq22.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2


if [[ "$num1" -gt "$num2" ]]; then
    echo "${num1} e ${num2}"
else
    echo "${num2} e ${num1}"
fi