#include <iostream>
#include "utils.h"

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cout << "Uso: ./programa <opção> [argumento]" << std::endl;
        std::cout << "Opções:" << std::endl;
        std::cout << "  -thread <msg>             : Executar mensagem em thread" << std::endl;
        std::cout << "  -fork                     : Exemplo com fork e variáveis" << std::endl;
        std::cout << "  -wait                     : Exemplo com wait()" << std::endl;
        std::cout << "  -waitexit                 : Exemplo com wait + exit/signal" << std::endl;
        std::cout << "  -waitpid                  : Aguardar múltiplos filhos com waitpid()" << std::endl;
        std::cout << "  -sinal                    : Comunicação entre pai e filho com sinal" << std::endl;
        std::cout << "  -system <comando>         : Executar comando com system()" << std::endl;
        std::cout << "  -pipe <comando>           : Executar comando com popen()" << std::endl;
        std::cout << "  -uuid <partição>          : Mostrar UUID da partição" << std::endl;
        return 1;
    }

    std::string opcao = argv[1];

    if (opcao == "-thread" && argc == 3) {
        executarThreadSimples(argv[2]);
    } else if (opcao == "-fork") {
        exemploVariaveisComFork();
    } else if (opcao == "-wait") {
        exemploWait();
    } else if (opcao == "-waitexit") {
        exemploWaitExit();
    } else if (opcao == "-waitpid") {
        exemploWaitpid();
    } else if (opcao == "-sinal") {
        comunicarComSinal();
    } else if (opcao == "-system" && argc == 3) {
        executarComSystem(argv[2]);
    } else if (opcao == "-pipe" && argc == 3) {
        executarComPopen(argv[2]);
    } else if (opcao == "-uuid" && argc == 3) {
        mostrarUUID(argv[2]);
    } else {
        std::cerr << "Parâmetros inválidos ou incompletos." << std::endl;
        return 1;
    }

    return 0;
}