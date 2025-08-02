#include <iostream>
#include <cstdio>
#include "utils.h"

void moverArquivo(const std::string& origem, const std::string& destino) {
    if (std::rename(origem.c_str(), destino.c_str()) != 0)
        perror("Erro ao renomear arquivo");
    else
        std::cout << "Arquivo renomeado com sucesso." << std::endl;
}

int main(int argc, char *argv[])
{
   if (argc != 3) exit(1);

   if (std::rename(argv[1], argv[2]) != 0)
      perror("Erro ao renomear arquivo");
   else
      std::cout << "Arquivo renomeado" << std::endl;
   return 0;
   
}