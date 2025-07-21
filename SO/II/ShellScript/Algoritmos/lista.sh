#!/bin/bash
declare -a lista

adicionar() {
    read -p "Digite um valor para adicionar: " valor
    lista+=("$valor")
}

mostrar() {
    echo "Conteúdo da lista:"
    for i in "${lista[@]}"; do echo "- $i"; done
}

while true; do
    echo -e "\n LISTA"
    echo "1. Adicionar elemento"
    echo "2. Mostrar lista"
    echo "9. Sair"
    read -p "Escolha: " opc

    case $opc in
        1) adicionar ;;
        2) mostrar ;;
        9) break ;;
        *) echo "Opção inválida" ;;
    esac
done