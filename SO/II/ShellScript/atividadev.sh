#!/bin/bash

# Recebe o primeiro parâmetro
numero=$1

# Verifica se é um número
if ! [[ "$numero" =~ ^[0-9]+$ ]]; then
    echo "Por favor, insira um número inteiro."
    exit 1
fi

# Verifica se é maior que 10
if [ "$numero" -gt 10 ]; then
    echo "maior"
else
    echo "menor ou igual"
fi