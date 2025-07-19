#!/bin/bash
# numero.sh
# Verificar se número é par ou ímpar

declare N=$(( $1 ))
declare R=$(( $N % 2 ))

if [ $R –eq 0 ] ; then
  echo “Numero par”
else
  echo “Numero impar”
fi