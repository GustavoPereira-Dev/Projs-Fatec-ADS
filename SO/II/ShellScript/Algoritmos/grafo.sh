#!/bin/bash
declare -A grafo

adicionar() {
    read -p "Vértice de origem: " origem
    read -p "Conectado a (espaço entre múltiplos): " destinos
    grafo[$origem]="$destinos"
}

mostrar() {
    echo "Grafo - Lista de adjacência:"
    for vertice in "${!grafo[@]}"; do
        echo "$vertice → ${grafo[$vertice]}"
    done
}

while true; do
    echo -e "\nGRAFO"
    echo "1. Adicionar conexão"
    echo "2. Mostrar grafo"
    echo "9. Sair"
    read -p "Escolha: " opc
    case $opc in
        1) adicionar ;;
        2) mostrar ;;
        9) break ;;
        *) echo "Opção inválida" ;;
    esac
done