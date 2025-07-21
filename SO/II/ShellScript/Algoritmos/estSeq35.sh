#!/bin/bash
#estSeq35.sh

read -p "Digite o primeiro número: " num1
read -p "Digite o segundo número: " num2
		
if [ "$num2" -gt "$num1" ] ; then
 indx=$num2
else
 indx=$num1
fi
		
while [ "$indx" -gte "$num1" || "$indx" -gte "$num2" ] ; then
 if [ "$indx" % 2 -eq 1 ] ; then
  res=$(( $res + $indx ))
 fi
 indx--;
done
		
echo "A somatória de números ímpares entre os dois é: ${res} "