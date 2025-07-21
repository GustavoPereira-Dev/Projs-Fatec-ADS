#!/bin/bash
# aluno.sh

declare -A alunos_pnome
declare -A alunos_snome
declare -A alunos_pontos
indice=1

criarAluno() {
    echo "Criando aluno $indice"
    read -p "Primeiro nome: " pnome
    read -p "Sobrenome: " snome
    read -p "Pontos: " pontos

    alunos_pnome[$indice]="$pnome"
    alunos_snome[$indice]="$snome"
    alunos_pontos[$indice]=$pontos

    echo "Aluno cadastrado com ID: $indice"
    ((indice++))
}

mostrarAluno() {
    read -p "Digite o ID do aluno a exibir: " id
    if [ -z "${alunos_pnome[$id]}" ]; then
        echo "Aluno com ID $id não encontrado"
    else
        echo "Aluno $id:"
        echo "Nome: ${alunos_pnome[$id]} ${alunos_snome[$id]}"
        echo "Pontos: ${alunos_pontos[$id]}"
    fi
}

listarTodos() {
    echo "Lista de alunos cadastrados:"
    for id in "${!alunos_pnome[@]}"; do
        echo "- [$id] ${alunos_pnome[$id]} ${alunos_snome[$id]} | Pontos: ${alunos_pontos[$id]}"
    done
}

while true; do
    echo -e "\nAluno"
    echo "1. Criar aluno"
    echo "2. Mostrar aluno por ID"
    echo "3. Listar todos os alunos"
    echo "9. Sair"
    read -p "Escolha uma opção: " opc

    case $opc in
        1) criarAluno ;;
        2) mostrarAluno ;;
        3) listarTodos ;;
        9) echo "Encerrando..."; break ;;
        *) echo "Opção inválida" ;;
    esac
done