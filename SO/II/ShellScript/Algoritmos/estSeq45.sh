#!/bin/bash
# estSeq45.sh

res=0
num2=0

for ((num1=1; num1<=15; num1++)); do
    num2=$((num2 + 1))

    num2_plus_1=$(echo "$num2 + 1" | bc)
    num2_squared=$(echo "$num2 * $num2" | bc)
    num1_squared=$(echo "$num1 * $num1" | bc)

    termo=$(echo "scale=8; e(($num2_plus_1 / $num2_squared) * (l($num1) / $num1_squared))" | bc -l)
    res=$(echo "scale=8; $res + $termo" | bc -l)
done

echo "O resultado da sequência até 15 é: $res"