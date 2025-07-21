#!/bin/bash
#estSeq36.sh

echo "Digite o número para sequência de fatorial: " num
		
if [ "$num" -lt 2 ] ; then
 fat=1
fi
		
while [ "$indx" -lt "$num" ] ; then
 indx=$(($indx + 1))
			
 for indxFat in $(seq $indx 1 -1); do
  fat=$(($fat + ($num * $num) ))
 done
 seq=$(echo "$seq + (1 / $fat) " | bc)
 fat=0
done
		
echo "O resultado da sequência de fatorial é: ${seq} "