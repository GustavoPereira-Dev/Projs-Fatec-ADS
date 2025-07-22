#!/bin/bash
# variaveisHomogeneas1.sh
# Descrição: Coleta valores de 5 índices de um vetor e retorna a soma total

declare -a vet
soma=0

for ((i=0; i<5; i++)); do
    read -p "Digite o valor do índice $((i+1)): " valor
    vet[$i]=$valor
    soma=$((soma + valor))
done

echo "O resultado da soma dos vetores é: $soma"