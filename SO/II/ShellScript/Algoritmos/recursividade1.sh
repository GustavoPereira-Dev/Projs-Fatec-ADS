#!/bin/bash
# recursividade1.sh
# Descrição: Faz a soma de 1 até N usando recursão

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