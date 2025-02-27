#include <iostream>
#include <filesystem>

namespace fs = std::filesystem;


int main()
{
   std::string path = "/dev";
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
