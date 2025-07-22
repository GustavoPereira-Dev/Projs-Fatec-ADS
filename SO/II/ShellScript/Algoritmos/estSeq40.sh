#!/bin/bash
# estSeq40.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2

if [ "$num1" -gt "$num2" ]; then
    ini=$num2
    fim=$num1
else
    ini=$num1
    fim=$num2
fi

echo "Números primos entre $ini e $fim:"

for ((i=ini; i<=fim; i++)); do
    if [ "$i" -lt 2 ]; then
        continue
    fi

    primo=1
    for ((j=2; j<=i/2; j++)); do
        if [ $((i % j)) -eq 0 ]; then
            primo=0
            break
        fi
    done

    if [ "$primo" -eq 1 ]; then
        echo "$i"
    fi
done