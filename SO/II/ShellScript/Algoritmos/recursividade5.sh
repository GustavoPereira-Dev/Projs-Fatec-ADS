#!/bin/bash
# recursividade5.sh
# Descrição: Calcula a série (N)! + (N-1)! + ... + (1)! de forma recursiva
# Data: 19/03/2024
# Programador: Gustavo Pereira
# Versão: 0.1

fatorial() {
    local n=$1
    if [ "$n" -le 1 ]; then
        echo 1
    else
        local anterior=$(fatorial $((n - 1)))
        echo $((n * anterior))
    fi
}

somarFatoriais() {
    local n=$1
    if [ "$n" -le 1 ]; then
        echo 1
    else
        local fat=$(fatorial "$n")
        local somaResto=$(somarFatoriais $((n - 1)))
        echo $((fat + somaResto))
    fi
}

# Entrada do usuário
read -p "Digite o número para a série (N)! + (N-1)! + ... + 1!: " num
resultado=$(somarFatoriais "$num")

echo "O resultado da série até $num! é: $resultado"