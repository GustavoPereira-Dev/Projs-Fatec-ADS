#!/bin/bash
# recursividade1.sh
# Descrição: Faz a soma de 1 até N usando recursão
# Data: 19/03/2024
# Programador: Gustavo Pereira
# Versão: 0.1

somaNumeros() {
    local n=$1
    if [ "$n" -le 1 ]; then
        echo 1
    else
        local anterior=$(somaNumeros $((n - 1)))
        echo $((n + anterior))
    fi
}

num=100
resultado=$(somaNumeros $num)

echo "O resultado da soma de 1 a $num é: $resultado"