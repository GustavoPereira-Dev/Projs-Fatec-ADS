#!/bin/bash
# temporario.sh
# Criar diretório

if [ -d ./temporario ] ; then
  echo “Diretorio existe”
else
  mkdir temporario
fi