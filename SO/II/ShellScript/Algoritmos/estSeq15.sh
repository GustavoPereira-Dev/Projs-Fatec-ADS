#!/bin/bash
#estSeq15.sh

read -p "Digite o valor do 1° Cateto: " cat1
read -p "Digite o valor do 2° Cateto: " cat2

hip=$(echo "sqrt(cat1 * cat1) + (cat2 * cat2)" | bc)

echo "O resultado da hipotenusa é ${hip}"