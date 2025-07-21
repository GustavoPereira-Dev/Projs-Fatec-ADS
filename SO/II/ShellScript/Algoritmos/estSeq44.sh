#!/bin/bash
# estSeq44.sh

read -p "Digite o valor da base: " base
read -p "Digite o valor do expoente: " exp

pot=1
for ((i=1; i<=exp; i++)); do
    pot=$((pot * base))
done

echo "O resultado de ${base} elevado à ${exp} é: ${pot}"