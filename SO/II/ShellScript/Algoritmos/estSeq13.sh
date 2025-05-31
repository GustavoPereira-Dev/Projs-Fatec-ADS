#!/bin/bash
#estSeq13.sh

read -p "Digite a quantidade de kilos: " kg

duracaoDias=$(echo "($kg * 1000) / 50" | bc)

echo "O alimento durará ${duracaoDias} dias"