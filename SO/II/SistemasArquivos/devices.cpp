#include <iostream>
#include <fstream>
#include <optional>

std::optional<std::string> get_device_of_mount_point(std::string_view path)
{

   std::ifstream mounts{"/proc/mounts"};
   std::string mountPoint;
   std::string device;

   while (mounts >> device >> mountPoint) // mounts >> device >> mountPoint equivale a 3 whiles acoplados (while mounts, while device e while mountPoint)
   { // onde o último while entra nesse bloco/escopo de código abaixo
      if (mountPoint == path)
      {
         return device;
      }
   }

   return std::nullopt;
}

int main()
{
   if (const auto device = get_device_of_mount_point("/"))
      std::cout << *device << "\n";
   else
      std::cout << "Not found\n";
}