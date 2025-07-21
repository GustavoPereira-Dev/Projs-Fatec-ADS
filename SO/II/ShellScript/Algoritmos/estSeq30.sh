#!/bin/bash
#estSeq30.sh

read -p "Digite o ano de nascimento: " anoNasc
read -p "Digite o mês de nascimento: " mesNasc
read -p "Digite o dia de nascimento: " diaNasc
read -p "Digite o ano atual: " anoAt
read -p "Digite o mês atual: " mesAt
read -p "Digite o dia atual: " diaAt
		
if [ $mesAt - $mesNasc -lt 0 ] ; then
 mesRes=$(( (mesAt - mesNasc) + 12 ))
 anoRes=$(( (anoAt - anoNasc) - 1 ))
else 
 mesRes=$(( (mesAt - mesNasc) ))
 anoRes=$(( (anoAt - anoNasc) ))
fi	
		
if [ "$diaAt" - "$diaNasc" -lt 0 ] ; then
 if [ "$anoAt" % 4 -eq 0 && "$anoAt" % 100 -ne 0 && "$mesAt" - 1 -eq 2 || "$anoAt" % 400 -eq 0 && "$anoAt" % 100 -eq 0 && "$mesAt" - 1 -eq 2 ] ; then
  diaRes=$(( ($diaAt - $diaNasc) + 29 ))
 elif [ "$anoAt" % 4 -eq 0 && "$anoAt" % 100 -eq 0 && "$mesAt" - 1 -eq 2 || "$anoAt" % 400 -ne 0 && "$anoAt" % 100 -ne 0 && "$mesAt" - 1 -eq 2 ] ; then
  diaRes=$(( ($diaAt - $diaNasc) + 28 ))
 elif [ "$mesAt" % 2 -eq 1 && "$mesAt" -lt 8 || "$mesAt" % 2 -eq 0 && "$mesAt" > 7 ] ; then
  diaRes=$(( ($diaAt - $diaNasc) + 30 ))
 else
  diaRes=$(( ($diaAt - $diaNasc) + 31 ))
 fi			
else
 diaRes=$(( $diaAt - $diaNasc ))
echo "O resultado de sua idade é de ${anoRes} anos, ${mesRes} mes(es) e ${diaRes} dia(s)"