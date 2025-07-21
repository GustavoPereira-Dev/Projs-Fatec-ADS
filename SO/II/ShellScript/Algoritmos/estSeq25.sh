#!/bin/bash
#estSeq25.sh

read -p "Digite a hora de início do jogo: " horaInicio
read -p "Digite os minutos de início do jogo: "  minInicio
read -p "Digite a hora de fim do jogo: " horaFim
read -p "Digite os minutos de fim do jogo: " minFim
		
if [ "$horaFim" - "$horaInicio" -lt 0 ] ; then
 duracaoHor=$(( ($horaFim - $horaInicio) + 24 ))
else
 duracaoHor=$(( $horaFim - $horaInicio ))
fi

if [ "$minFim" - "$minInicio" -lt 0 ] ; then
 duracaoMin=$(( ($minFim - $minInicio) + 60 ))
else 
 duracaoMin=$(($minFim - $minInicio))
fi

echo "A duração do jogo foi de ${duracaoHor} horas e ${duracaoMin} minutos"