#!/bin/bash
fila=()

enqueue() {
    read -p "Valor para enfileirar: " valor
    fila+=("$valor")
}

dequeue() {
    if [ ${#fila[@]} -eq 0 ]; then
        echo "Fila vazia"
    else
        echo "Removido: ${fila[0]}"
        fila=("${fila[@]:1}")
    fi
}

mostrar() {
    echo "🚶Fila:"
    for i in "${fila[@]}"; do echo "- $i"; done
}

while true; do
    echo -e "\nFILA"
    echo "1. Enfileirar (enqueue)"
    echo "2. Retirar (dequeue)"
    echo "3. Mostrar fila"
    echo "9. Sair"
    read -p "Escolha: " opc
    case $opc in
        1) enqueue ;;
        2) dequeue ;;
        3) mostrar ;;
        9) break ;;
        *) echo "Opção inválida" ;;
    esac
done