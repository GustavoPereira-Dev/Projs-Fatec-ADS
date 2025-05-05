#include <fstream>
#include <string>
#include <iostream>

int main(int argc, char *argv[])
{
	exibir(argc, *argv[]);

}

void exibir(int argc, char *argv[]){
   if (argc != 2)
      exit(1);
   std::ifstream file( argv[1] );
   std::string str;
   while (std::getline(file, str))
      std::cout << str << std::endl;
}