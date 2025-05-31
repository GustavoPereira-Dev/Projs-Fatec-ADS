#!/bin/bash
#estSeq11.sh

read -p "Digite o valor do raio: " raio

comprimento=$(echo "(2 * $raio) * 3.14" | bc)

echo "O resultado do comprimento é: ${comprimento}"