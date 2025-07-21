#!/bin/bash
# variaveisHomogeneas5.sh
# Descrição: Cria um vetor [100], calcula o maior, o menor e a média dos valores
# Data: 26/03/2024
# Programador: Gustavo Pereira
# Versão: 0.1

declare -a vt
soma=0
maior=0
menor=0

for ((i=0; i<100; i++)); do
    read -p "Digite o valor do $((i+1))º índice: " valor
    vt[$i]=$valor
    soma=$((soma + valor))

    if [ "$i" -eq 0 ]; then
        maior=$valor
        menor=$valor
    else
        if [ "$valor" -gt "$maior" ]; then
            maior=$valor
        fi
        if [ "$valor" -lt "$menor" ]; then
            menor=$valor
        fi
    fi
done

media=$(echo "scale=2; $soma / 100" | bc)

echo ""
echo "🔢 Maior valor: $maior"
echo "🔢 Menor valor: $menor"
echo "📊 Média dos valores: $media"