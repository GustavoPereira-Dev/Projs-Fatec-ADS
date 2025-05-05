#include <iostream>
#include "utils.h"

int main(int argc, char *argv[]) {
    if (argc < 2) {
        std::cerr << "Uso: ./programa <opção> [parâmetros]" << std::endl;
        std::cerr << "Opções disponíveis:" << std::endl;
        std::cerr << "  -c <origem> <destino> : Copiar arquivo" << std::endl;
        std::cerr << "  -l <diretório>       : Listar diretório" << std::endl;
        std::cerr << "  -r <diretório>       : Listar diretório recursivamente" << std::endl;
        std::cerr << "  -p <arquivo>         : Exibir permissões do arquivo" << std::endl;
        return 1;
    }

    std::string opcao = argv[1];
    if (opcao == "-c" && argc == 4) {
        copiarArquivo(argv[2], argv[3]);
    } else if (opcao == "-l" && argc == 3) {
        listarDiretorio(argv[2]);
    } else if (opcao == "-r" && argc == 3) {
        listarRecursivo(argv[2]);
    } else if (opcao == "-p" && argc == 3) {
        exibirPermissoes(argv[2]);
    } else if(){
		
	} else if(){
		
	} else if(){
		
	} else {
        std::cerr << "Opção inválida ou parâmetros insuficientes." << std::endl;
        return 1;
    }

    return 0;
}

