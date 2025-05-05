#include <iostream>
#include <filesystem>

namespace fs = std::filesystem;


void listarEspecial(std::string){
   for (const auto & entry : fs::directory_iterator(path))
   {
   
      if (fs::is_block_file( entry.path() ))
      {   
		 std::cout << entry.path() << ": arquivo especial de bloco" << std::endl;
         continue;
      }		 

      if (fs::is_character_file( entry.path() ))
      {
         std::cout << entry.path() << ": arquivo especial de carácter" << std::endl;
         continue;
      }
   }
}


int main()
{
   listarEspecial("/dev");
}