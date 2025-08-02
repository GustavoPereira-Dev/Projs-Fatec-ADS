#include <iostream>
#include "utils.h"

// g++ -o programa main.cpp utils.cpp mypwd.cpp mover.cpp getuuid.cpp listar.cpp listarrecursivo.cpp exibirperm.cpp exemplostat.cpp devices.cpp -lblkid

int main(int argc, char *argv[]) {
    if (argc < 2) {
        std::cerr << "Uso: ./programa <opção> [parâmetros]" << std::endl;
        std::cerr << "Opções disponíveis:" << std::endl;
        std::cerr << "  -c <origem> <destino>       : Copiar arquivo" << std::endl;
        std::cerr << "  -l <diretório>              : Listar diretório" << std::endl;
        std::cerr << "  -r <diretório>              : Listar diretório recursivamente" << std::endl;
        std::cerr << "  -p <arquivo>                : Exibir permissões do arquivo" << std::endl;
        std::cerr << "  -mv <origem> <destino>      : Mover/Renomear arquivo" << std::endl;
        std::cerr << "  -pwd                        : Mostrar diretório atual" << std::endl;
        std::cerr << "  -stat <arquivo>             : Exibir informações via stat" << std::endl;
        std::cerr << "  -uuid <dispositivo>         : Obter UUID do dispositivo" << std::endl;
        std::cerr << "  -device <ponto de montagem> : Mostrar qual dispositivo está montado" << std::endl;
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
    } else if (opcao == "-mv" && argc == 4) {
        moverArquivo(argv[2], argv[3]);
    } else if (opcao == "-pwd" && argc == 2) {
        mostrarDiretorioAtual();
    } else if (opcao == "-stat" && argc == 3) {
        mostrarInfoStat(argv[2]);
    } else if (opcao == "-uuid" && argc == 3) {
        obterUUIDDispositivo(argv[2]);
    } else if (opcao == "-device" && argc == 3) {
        mostrarDispositivo(argv[2]);
    } else {
        std::cerr << "Opção inválida ou parâmetros insuficientes." << std::endl;
        return 1;
    }

    return 0;
}