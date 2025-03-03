#include <iostream>
#include <cstdio>

int main(int argc, char *argv[])
{
   if (argc != 3) exit(1);

   if (std::rename(argv[1], argv[2]) != 0)
      perror("Erro ao renomear arquivo");
   else
      std::cout << "Arquivo renomeado" << std::endl;
   return 0;
   
}