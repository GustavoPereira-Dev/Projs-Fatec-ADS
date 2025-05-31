#!/bin/bash
#estSeq8.sh

read -p "Digite o valor do depósito: " deposito
valor=$(echo "$deposito * 0.013" | bc)

echo "O valor é: ${valor}"