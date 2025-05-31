#!/bin/bash
#estSeq16.sh

read -p "Digite a quantidade de horas: " qntH
read -p Digite o valor por hora: " vPHr
read -p "Digite o valor do percentual: " perc 
read -p "Digite a quantidade de dependentes: " dep

saBrut = $((qntH * vPHr))
saLiq =$(echo "($saBrut - ($saBrut * ($perc / 100))) + ($dep * 100) | bc)

echo "O valor do Salário Líquido é de ${saLiq} reais"
