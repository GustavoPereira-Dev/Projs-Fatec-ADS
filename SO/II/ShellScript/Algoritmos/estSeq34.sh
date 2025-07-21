#!/bin/bash
#estSeq34.sh

read -p "Digite o número para tabuada: " num
		
while [ "$indx" -lt 10 ]; do
 indx=$(($indx + 1))
 mult=$(($num * $indx));
 echo "${num} x ${indx} = ${mult} "
done