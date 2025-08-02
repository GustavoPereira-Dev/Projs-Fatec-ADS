#include <fstream>
#include <string>
#include <iostream>
#include "utils.h"

void exibirPermissoes(const std::string& caminho) {
    std::ifstream file(caminho);
    if (!file) {
        std::cerr << "Erro ao abrir o arquivo: " << caminho << std::endl;
        return;
    }

    std::string linha;
    while (std::getline(file, linha)) {
        std::cout << linha << std::endl;
    }
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cerr << "Uso: ./programa <arquivo>" << std::endl;
        return 1;
    }

    exibirPermissoes(argv[1]);
    return 0;
}

int main(int argc, char *argv[])
{
	exibir(argc, *argv[]);

}