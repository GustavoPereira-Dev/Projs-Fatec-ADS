#!/bin/bash
#estSeq2.sh
read -p "Digite o valor do Salário: " salario

reajuste =$(echo "salario + (salario * 0.15)" | bc)

echo "O resultado do Reajuste é: ${reajuste}"