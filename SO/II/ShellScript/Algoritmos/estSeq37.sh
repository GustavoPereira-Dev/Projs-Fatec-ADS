#!/bin/bash
# estSeq37.sh

read -p "Digite o número para Fibonacci: " num

ant1=1
ant2=0
indx=1
fib=0

if [ "$num" -gt 1 ]; then
    while [ "$indx" -lt "$num" ]; do
        fib=$((ant1 + ant2))
        ant2=$ant1
        ant1=$fib
        indx=$((indx + 1))
    done
else
    fib=$num
fi

echo "O resultado da Fibonacci é: ${fib}"