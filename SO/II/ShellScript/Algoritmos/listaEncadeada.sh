#!/bin/bash
# listaEncadeada.sh

declare -A lista
head=""
contador=0

inserir() {
    read -p "Valor a ser inserido: " valor
    id="n$contador"
    lista[$id.value]="$valor"
    lista[$id.next]=""

    if [ -z "$head" ]; then
        head="$id"
    else
        atual="$head"
        while [ -n "${lista[$atual.next]}" ]; do
            atual="${lista[$atual.next]}"
        done
        lista[$atual.next]="$id"
    fi
    ((contador++))
    echo "Inserido: $valor"
}

mostrar() {
    echo "Lista encadeada:"
    atual="$head"
    while [ -n "$atual" ]; do
        echo "- ${lista[$atual.value]}"
        atual="${lista[$atual.next]}"
    done
}

remover() {
    if [ -z "$head" ]; then
        echo "Lista vazia!"
        return
    fi
    removido="${lista[$head.value]}"
    head="${lista[$head.next]}"
    echo "Removido: $removido"
}

while true; do
    echo -e "\nLista Encadeada"
    echo "1. Inserir elemento"
    echo "2. Remover primeiro elemento"
    echo "3. Mostrar lista"
    echo "9. Sair"
    read -p "Escolha: " opc

    case $opc in
        1) inserir ;;
        2) remover ;;
        3) mostrar ;;
        9) echo "Encerrando..."; break ;;
        *) echo "Opção inválida" ;;
    esac
done