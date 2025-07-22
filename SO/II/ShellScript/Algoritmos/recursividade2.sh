#!/bin/bash
# recursividade2.sh
# Descrição: Faz a soma de N até 1 usando recursão

somarNumeros() {
    local n=$1

    if [ "$n" -le 1 ]; then
        echo 1
    else
        local anterior=$(somarNumeros $((n - 1)))
        echo $((n + anterior))
    fi
}

read -p "Digite o número para a série (N) + (N-1) + ... + 1: " num
resultado=$(somarNumeros $num)

echo "O resultado da série até $num é: $resultado"