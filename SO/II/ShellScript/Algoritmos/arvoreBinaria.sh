#!/bin/bash
declare -A arvore

inserir() {
    read -p "Nó pai (ou root): " pai
    read -p "Lado (left/right): " lado
    read -p "Valor: " valor
    arvore[$pai.$lado]=$valor
}

mostrar() {
    echo "Árvore simulada:"
    for chave in "${!arvore[@]}"; do
        echo "$chave → ${arvore[$chave]}"
    done
}

while true; do
    echo -e "\nÁRVORE BINÁRIA"
    echo "1. Inserir nó filho"
    echo "2. Mostrar árvore"
    echo "9. Sair"
    read -p "Escolha: " opc
    case $opc in
        1) inserir ;;
        2) mostrar ;;
        9) break ;;
        *) echo "Opção inválida" ;;
    esac
done