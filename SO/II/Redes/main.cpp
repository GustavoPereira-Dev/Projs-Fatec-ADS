#include <iostream>
#include "utils.h"

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Uso: ./programa <opção> [parâmetros]" << std::endl;
        std::cerr << "Opções:" << std::endl;
        std::cerr << "  -dns <host>                : Resolver DNS completo" << std::endl;
        std::cerr << "  -ipv4 <host>               : Resolver IP IPv4 simples" << std::endl;
        std::cerr << "  -port <host> <porta>       : Testar porta TCP" << std::endl;
        std::cerr << "  -post <url> <json>         : Enviar POST com JSON" << std::endl;
        std::cerr << "  -get <url> <arquivo>       : Fazer download via GET" << std::endl;
        return 1;
    }

    std::string opcao = argv[1];
    if (opcao == "-dns" && argc == 3) {
        resolverDNSCompleto(argv[2]);
    } else if (opcao == "-ipv4" && argc == 3) {
        resolverIPv4Simples(argv[2]);
    } else if (opcao == "-port" && argc == 4) {
        int porta = std::stoi(argv[3]);
        testarPorta(argv[2], porta);
    } else if (opcao == "-post" && argc == 4) {
        fazerPostJson(argv[2], argv[3]);
    } else if (opcao == "-get" && argc == 4) {
        fazerDownloadArquivo(argv[2], argv[3]);
    } else {
        std::cerr << "Opção inválida ou parâmetros incorretos." << std::endl;
        return 1;
    }

    return 0;
}