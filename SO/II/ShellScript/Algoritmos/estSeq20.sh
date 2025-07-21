#!/bin/bash
#estSeq20.sh

read -p "Digite o valor de a: " a
read -p "Digite o valor de b: " b
read -p "Digite o valor de c: " c


delta =$(echo "($b * $b) - (4 * $a * $c)" | bc)
x0 =$(echo "($b + sqrt($delta)) / (2 * $a)" | bc)
x1 =$(echo "($b - sqrt($delta)) / (2 * $a)
" | bc)

if[[ "$delta" > 0 ]] then
   echo "O resultado de x0 é ${x0} e de x1 ${x1}"
else
   echo "Raiz inexistente e negativa"
fi