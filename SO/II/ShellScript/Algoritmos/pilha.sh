#!/bin/bash
pilha=()

push() {
    read -p "Valor para empilhar: " valor
    pilha+=("$valor")
}

pop() {
    if [ ${#pilha[@]} -eq 0 ]; then
        echo "Pilha vazia"
    else
        echo "Removido: ${pilha[-1]}"
        unset 'pilha[-1]'
    fi
}

mostrar() {
    echo "Pilha:"
    for ((i=${#pilha[@]}-1; i>=0; i--)); do echo "- ${pilha[$i]}"; done
}

while true; do
    echo -e "\nMPILHA"
    echo "1. Empilhar (push)"
    echo "2. Desempilhar (pop)"
    echo "3. Mostrar pilha"
    echo "9. Sair"
    read -p "Escolha: " opc
    case $opc in
        1) push ;;
        2) pop ;;
        3) mostrar ;;
        9) break ;;
        *) echo "Opção inválida" ;;
    esac
done