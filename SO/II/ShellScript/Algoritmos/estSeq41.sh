#!/bin/bash
# estSeq41.sh

pos=0

for ((num1=1; num1<=6; num1++)); do
    for ((num2=1; num2<=6; num2++)); do
        if [ $((num1 + num2)) -eq 7 ]; then
            echo "(${num1}, ${num2})"
            pos=$((pos + 1))
        fi
    done
done

echo "Total de possibilidades cuja soma é 7: $pos"