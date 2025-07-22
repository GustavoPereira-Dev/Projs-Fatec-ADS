#!/bin/bash
# estSeq33.sh

read -p "Digite o número limite da sequência harmônica: " num
indx=1
seq=0

while [ "$indx" -le "$num" ]; do
    seq=$(echo "scale=5; $seq + (1 / $indx)" | bc -l)
    indx=$((indx + 1))
done

echo "O resultado da sequência harmônica com ${num} termos é: ${seq}"