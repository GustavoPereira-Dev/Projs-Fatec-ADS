#include <cstdlib>
#include <iostream>
#include "utils.h"

void executarComSystem(const std::string& comando) {
    system(comando.c_str());
    std::cout << "Comando executado com system()" << std::endl;
}

int main()
{
   system("ls -l");
   std::cout << "Executado" << std::endl;
   return 0;
}