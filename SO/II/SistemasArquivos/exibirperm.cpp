#include <filesystem>
#include <fstream>
#include <iostream>

void demo_perms(std::filesystem::perms p)
{
   using std::filesystem::perms;
   auto show = [=](char op, perms perm)
   {
      std::cout << (perms::none == (perm & p) ? '-' : op);
   };

   show('r', perms::owner_read);
   show('w', perms::owner_write);
   show('x', perms::owner_exec);
   show('r', perms::group_read);
   show('w', perms::group_write);
   show('x', perms::group_exec);
   show('r', perms::others_read);
   show('w', perms::others_write);
   show('x', perms::others_exec);
   std::cout << '\n';
}

int main(int argc, char *argv[])
{
   if (argc != 2) exit(1);
   std::cout << "Permissão do arquivo: ";
   demo_perms(std::filesystem::status( argv[1] ).permissions());
}