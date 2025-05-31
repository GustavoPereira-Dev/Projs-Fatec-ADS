#!/bin/bash
#estSeq17.sh

read -p "Digite o tempo de percurso: " tempP
read -p "Digite a velocidade média: " velMed

lGast=$(echo "($tempP * $velMed) / 12" | bc)

echo "A quantidade de litros gastos na viagem foi ${lGast}"