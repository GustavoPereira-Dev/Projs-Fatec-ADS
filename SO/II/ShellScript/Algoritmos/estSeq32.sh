#!/bin/bash
#estSeq32.sh

read -p "Digite o número de fatorial: " num
		
for i in {$num..1..-2}; do
 fat=$(( $fat * (num * (num - 1)) ))
done

echo "O resultado do fatorial ${num}  é: ${fat} "