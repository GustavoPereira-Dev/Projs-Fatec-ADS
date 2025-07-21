#!/bin/bash
#estSeq28.sh

read -p "Digite o preço atual do produto: " prAtual
read -p "Digite a venda mensal do produto: " vendMen
		
if [ "$vendMen" -lt 500 && "$prAtual" -lt 30 ] ; then
 prNovo=$(echo "$prAtual + ($prAtual * 0.10" | bc)
elif [ "$vendMen" -gte 500 && "$vendMen" -lt 1000 && "$prAtual" -gt 30 && "$prAtual" -lt 80 ] ; then
 prNovo=$(echo "$prAtual + ($prAtual * 0.15)" | bc)
elif [ "$vendMen" -gte 1000 && "$prAtual" -gte 80 ] ; then
 prNovo=$(echo "$prAtual - ($prAtual * 0.05)" | bc)
else 
 prNovo=$prAtual;
fi

echo "O preço novo do produto é ${prNovo} reais "