#!/bin/bash 
# menu.sh 
# Um exemplo simples de menu em bash 

printf "Lista de Opcoes:\nA\tAdicionar arquivo;\nE\tExcluir arquivo;\nS\tSair;\n\n" 

echo -n "Informe a opção: " 
read OPCAO 

case $OPCAO in 
  A) 
     echo "Adicionado :)" 
     ;; 
  E) 
     echo "Excluído :(" 
     ;; 
  S) 
     echo "Sair" 
     ;; 
  *) 
     echo "Não entendi..." 
     ;; 
esac