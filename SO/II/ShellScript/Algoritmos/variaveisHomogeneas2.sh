#!/bin/bash
# variaveisHomogeneas2.sh
# Descrição: Coleta quantidade e preço de 5 produtos, calcula o total de vendas e a comissão de 5%
# Data: 26/03/2024
# Programador: Gustavo Pereira
# Versão: 0.1

declare -a qtd preco
totGeral=0

for ((i=0; i<5; i++)); do
    read -p "Digite a quantidade vendida do produto $((i+1)): " quantidade
    read -p "Digite o preço do produto $((i+1)): " precoProduto

    qtd[$i]=$quantidade
    preco[$i]=$precoProduto
done

echo ""
for ((i=0; i<5; i++)); do
    totalProduto=$(echo "${qtd[$i]} * ${preco[$i]}" | bc)
    totGeral=$(echo "$totGeral + $totalProduto" | bc)
    echo "Venda total do produto $((i+1)): R$ $totalProduto"
done

comissao=$(echo "scale=2; $totGeral * 0.05" | bc)

echo ""
echo "Valor total das vendas: R$ $totGeral"
echo "Comissão sobre as vendas (5%): R$ $comissao"