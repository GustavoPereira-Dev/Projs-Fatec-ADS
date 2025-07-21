#!/bin/bash
# estSeq42.sh

num1=1
num2=1
res=0

while [ "$num1" -le 50 ]; do
    termo=$(echo "scale=8; $num1 / $num2" | bc -l)
    res=$(echo "scale=8; $res + $termo" | bc -l)

    num1=$((num1 + 1))
    num2=$((num2 + 2))
done

echo "O resultado da sequência até 50 é: $res"