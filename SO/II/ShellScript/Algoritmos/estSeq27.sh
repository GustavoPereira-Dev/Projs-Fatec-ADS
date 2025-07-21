#!/bin/bash
#estSeq27.sh

read -p "Digite o número de voltas: " numVoltas
read -p "Digite a extensão do circuito: " extCirc
read -p "Digite o tempo de duração: " tempDur
		
distTotal=$(echo " ($extCirc * $numVoltas) / 1000) " | bc)
velMed=$(echo "$distTotal / $tempDur" | bc);
echo "A velocidade média pecorrida é: ${velMed} km/h"