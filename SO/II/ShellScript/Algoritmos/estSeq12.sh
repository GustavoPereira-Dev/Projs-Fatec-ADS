#!/bin/bash
#estSeq12.sh

read -p "Digite o ano de nascimento: " anoNasc
read -p "Digite o ano atual: " anoNasc

resltd =$(( ($anoAtual - anoNasc) + 17 ))

echo "Daqui a 17 anos você terá ${resltd}"
