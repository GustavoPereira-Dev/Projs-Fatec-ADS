#!/bin/bash
# variaveisHomogeneas4.sh
# Descrição: Cria matriz 8x8 com valores incrementais e potências de 2, somando a segunda linha de cada par

declare -A Mat
soma=0

for ((i=0; i<8; i+=2)); do
    for ((j=0; j<8; j++)); do
        if [ "$i" -eq 0 ] && [ "$j" -eq 0 ]; then
            Mat[$i,$j]=1
            Mat[$((i+1)),$j]=1
            soma=1
        else
            Mat[$i,$j]=$(( Mat[$i,$((j-1))] + 1 ))
            Mat[$((i+1)),$j]=$(( Mat[$((i+1)),$((j-1))] * 2 ))
            soma=$((soma + Mat[$((i+1)),$j]))
        fi

        if [ "$i" -ne 6 ] && [ "$j" -eq 7 ]; then
            Mat[$((i+2)),0]=$(( Mat[$i,$j] + 1 ))
            Mat[$((i+3)),0]=$(( Mat[$((i+1)),$j] * 2 ))
        fi
    done
done

echo "O resultado da soma dos valores exponenciais na matriz é: $soma"