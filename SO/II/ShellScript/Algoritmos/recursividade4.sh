#!/bin/bash
# recursividade4.sh
# Descrição: Calcula a série (N/1) + (N-1/2) + ... + (1/N) de forma recursiva
# Data: 19/03/2024
# Programador: Gustavo Pereira
# Versão: 0.1

somarSerie() {
    local n=$1
    local i=$2

    if [ "$i" -gt "$n" ]; then
        echo "0"
    else
        numerador=$((n - i + 1))
        termo=$(echo "scale=10; $numerador / $i" | bc -l)
        restante=$(somarSerie "$n" $((i + 1)))
        echo $(echo "scale=10; $termo + $restante" | bc -l)
    fi
}

read -p "Digite o valor de N para a série: " num
resultado=$(somarSerie "$num" 1)

echo "O resultado da série até $num é: $resultado"