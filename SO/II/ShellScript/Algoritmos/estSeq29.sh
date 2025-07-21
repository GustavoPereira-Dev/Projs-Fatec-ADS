#!/bin/bash
#estSeq29.sh

read -p "Digite o tipo de investimento (1 = poupança e 2 = renda fixa): " tipo
read -p "Digite o valor do investimento: " valor
		
case $tipo in
 1) investimento=$(echo "$valor + ($valor * 0.03)" | bc) ;;
 2) investimento=$(echo "$valor + ($valor * 0.05)" | bc) ;;
 *) investimento=$valor ;;
esac 
		
echo "O valor de investimento é ${investimento} reais "
