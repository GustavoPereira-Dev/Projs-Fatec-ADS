#!/bin/bash
# recursividade3.sh
# Descrição: Calcula a soma da série harmônica (1/1 + 1/2 + ... + 1/N) de forma recursiva

somarHarmonica() {
    local n=$1
    if [ "$n" -le 1 ]; then
        echo "1"
    else
        anterior=$(somarHarmonica $((n - 1)))
        termo=$(echo "scale=10; 1 / $n" | bc -l)
        echo $(echo "scale=10; $termo + $anterior" | bc -l)
    fi
}

read -p "Digite o número para a série harmônica: " num
resultado=$(somarHarmonica $num)

echo "O resultado da série (1/1 + 1/2 + ... + 1/$num) é: $resultado"