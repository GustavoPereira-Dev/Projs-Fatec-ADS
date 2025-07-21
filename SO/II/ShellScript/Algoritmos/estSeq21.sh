#!/bin/bash
#estSeq21.sh

read -p "Digite o valor da primeira nota: "  nota1
read -p "Digite o valor da segunda nota: "  nota2
read -p "Digite o valor da terceira nota: "  nota3
read -p "Digite o valor da quarta nota: "  nota4

media=$(echo "($nota1 + $nota2 + $nota3 + $nota4) / 4" | bc)

if [[ "$media" -gte 6.0 ]] then
   echo "APROVADO"
elif [[ "$media" -lt 3 ]] then
   echo "REPROVADO"
else
   echo "EXAME"
