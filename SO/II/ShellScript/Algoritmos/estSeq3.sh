#!/bin/bash
#estSeq3.sh

read -p "Digite o valor da base: " base
read -p "Digite o valor da altura: " altura

area=$(echo "$base * $altura" | bc)

echo "O resultado da area do quadrado é: ${area}"
