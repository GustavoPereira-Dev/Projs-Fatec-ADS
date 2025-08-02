#include <iostream>
#include <cstdio>
#include "utils.h"

void executarComPopen(const std::string& comando) {
    FILE* fpipe = popen(comando.c_str(), "r");
    if (!fpipe) {
        perror("Falha ao abrir pipe");
        return;
    }

    char linha[256];
    while (fgets(linha, sizeof linha, fpipe)) {
        std::cout << "Linha: " << linha;
    }

    pclose(fpipe);
}

int main() {
   FILE *fpipe;
   char *command = (char *)"ls -l";
   char line[256];
   
   if ( !(fpipe = (FILE*)popen(command,"r")) ) {
      perror("Falha ao abrir um pipe");
	  exit(1);
   }
   
   while ( fgets( line, sizeof line, fpipe)) {
      std::cout << "Linha: " << line;
   }
   
   pclose(fpipe);
   return 0;
}