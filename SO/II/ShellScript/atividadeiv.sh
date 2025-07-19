#!/bin/bash

# Recebe o primeiro parâmetro
numero=$1

# Verifica se é um número
if ! [[ "$numero" =~ ^[0-9]+$ ]]; then
    echo "Por favor, insira um número inteiro."
    exit 1
fi

# Verifica se é par ou ímpar
if [ $((numero % 2)) -eq 0 ]; then
    echo "par"
else
    echo "impar"
fi