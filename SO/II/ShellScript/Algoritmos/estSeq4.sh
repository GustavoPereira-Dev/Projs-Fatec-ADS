#!/bin/bash
#estSeq4.sh

read -p "Digite o valor de Graus em Celsius: " celsius

fahrenheit=$(echo "(9 * $celsius + 160) / 5" | bc)

echo "O resultado em Fahrenheit é: ${fahrenheit}"