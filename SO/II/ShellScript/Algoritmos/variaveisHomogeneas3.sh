#!/bin/bash
# variaveisHomogeneas3.sh
# Descrição: Coleta valores dos vetores VT1[3] e VT2[3], concatena em VT3[6] e exibe

declare -a vt1 vt2 vt3

# Coleta dos dados para VT1
for ((i=0; i<3; i++)); do
    read -p "Digite o valor do ${i}º índice do vetor VT1: " valor
    vt1[$i]=$valor
done

# Coleta dos dados para VT2
for ((i=0; i<3; i++)); do
    read -p "Digite o valor do ${i}º índice do vetor VT2: " valor
    vt2[$i]=$valor
done

# Concatena VT1 e VT2 em VT3
for ((i=0; i<3; i++)); do
    vt3[$i]=${vt1[$i]}
    vt3[$((i+3))]=${vt2[$i]}
done

echo ""
echo "📦 Conteúdo do vetor VT3 (concatenado):"
for ((i=0; i<6; i++)); do
    echo "$((i+1))ª posição: ${vt3[$i]}"
done