#!/bin/bash
#estSeq31.sh

num=10
		
for i in {$num..150}; do 
 quadrado=$(( (num * num) ))
 echo "O quadrado de ${i} ao quadro é: ${quadrado} "
done