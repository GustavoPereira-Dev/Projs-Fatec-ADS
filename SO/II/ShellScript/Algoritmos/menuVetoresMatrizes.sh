#!/bin/bash
# menuVetoresMatrizes.sh
# Descrição: Simula menu interativo com vetores e matrizes (adaptando auxiliarvarhomogenias de instancia classes de Java)
# Programador: Gustavo Pereira

declare -a vetor
declare -A matriz  # simulação bidimensional
lin=3
col=4

carregaVetor() {
    for ((i=0; i<${#vetor[@]}; i++)); do
        read -p "Digite o valor do índice $((i+1)) do vetor: " valor
        vetor[$i]=$valor
    done
}

classificaVetor() {
    for ((i=0; i<${#vetor[@]} - 1; i++)); do
        for ((j=i+1; j<${#vetor[@]}; j++)); do
            if [ "${vetor[$i]}" -gt "${vetor[$j]}" ]; then
                temp=${vetor[$i]}
                vetor[$i]=${vetor[$j]}
                vetor[$j]=$temp
            fi
        done
    done
}

mostraVetor() {
    echo "➡️ Vetor:"
    for ((i=0; i<${#vetor[@]}; i++)); do
        echo "vetor[$i] = ${vetor[$i]}"
    done
    echo "------------------------"
}

carregaMatriz() {
    for ((i=0; i<lin; i++)); do
        for ((j=0; j<col; j++)); do
            matriz[$i,$j]=$((RANDOM % 100))
        done
    done
}

classificaMatriz() {
    local flat=()
    for ((i=0; i<lin; i++)); do
        for ((j=0; j<col; j++)); do
            flat+=(${matriz[$i,$j]})
        done
    done

    for ((i=0; i<${#flat[@]} -1; i++)); do
        for ((j=i+1; j<${#flat[@]}; j++)); do
            if [ "${flat[$i]}" -gt "${flat[$j]}" ]; then
                temp=${flat[$i]}
                flat[$i]=${flat[$j]}
                flat[$j]=$temp
            fi
        done
    done

    idx=0
    for ((i=0; i<lin; i++)); do
        for ((j=0; j<col; j++)); do
            matriz[$i,$j]=${flat[$idx]}
            ((idx++))
        done
    done
}

mostraMatriz() {
    echo "Matriz:"
    for ((i=0; i<lin; i++)); do
        linha=""
        for ((j=0; j<col; j++)); do
            linha+="${matriz[$i,$j]} "
        done
        echo "$linha"
    done
    echo "------------------------"
}

# Menu
main() {
    read -p "Digite o tamanho do vetor: " tamV
    read -p "Digite linhas e colunas da matriz (ex: 3 4): " lin col

    vetor=()
    for ((i=0; i<tamV; i++)); do vetor[$i]=0; done

    while true; do
        echo -e "\n🧠 Menu:"
        echo "1 - Carregar vetor"
        echo "2 - Classificar vetor"
        echo "3 - Mostrar vetor"
        echo "4 - Carregar matriz"
        echo "5 - Classificar matriz"
        echo "6 - Mostrar matriz"
        echo "9 - Sair"
        read -p "Escolha uma opção: " opc

        case "$opc" in
            1) carregaVetor ;;
            2) classificaVetor; echo "✅ Vetor classificado!" ;;
            3) mostraVetor ;;
            4) carregaMatriz ;;
            5) classificaMatriz; echo "✅ Matriz classificada!" ;;
            6) mostraMatriz ;;
            9) echo "👋 Fim do programa."; break ;;
            *) echo "❌ Opção inválida." ;;
        esac
    done
}

main