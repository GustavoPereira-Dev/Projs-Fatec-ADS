#include <iostream>
#include <unistd.h>
#include "utils.h"

void mostrarDiretorioAtual() {
    char tmp[256];
    if (getcwd(tmp, sizeof(tmp)))
        std::cout << "Diretório atual: " << tmp << std::endl;
    else
        perror("Erro ao obter diretório atual");
}

int main() {
    char tmp[256];
    getcwd(tmp, 256);
	
    std::cout << "Directory: " << tmp << std::endl;
    return 0;
}
