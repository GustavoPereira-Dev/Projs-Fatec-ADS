#!/bin/bash
#estSeq7.sh

read -p "Digite o Comprimento: " comprimento
read -p "Digite a Largura: " largura
read -p "Digite a Altura: " altura

volume =$(echo "($comprimento * $altura * $largura)" | bc)

echo "O resultado do volume é: ${volume}"
