#!/bin/bash 
# /tmp/array.sh 
# Exemplo de uso de array 

# Declarando um array 

declare -a produto[0]="Baiao de 2" produto[1]="Suco de coco com Leite 750ml" produto[2]="Acarajé 4 unidades" 

# Adicionando mais um elemento 
produto[3]="Chopp de vinho 700ml" 

# Imprimindo a posição 1 e a 2 
printf "Elemento na posição 1: %s\n" "${produto[0]}" 
printf "Elemento na posição 2: %s\n" "${produto[1]}" 

# imprimindo todas as posições 
printf "Todos os elementos: %s\n" "${produto[*]}" 
printf "Todos os elementos: %s\n" "${produto[@]}"