#!/bin/bash
# estSeq39.sh

graos=1

for ((casa=2; casa<=64; casa++)); do
    graos=$(echo "$graos * 2" | bc)
done

echo "O total de grãos no tabuleiro é: $graos"