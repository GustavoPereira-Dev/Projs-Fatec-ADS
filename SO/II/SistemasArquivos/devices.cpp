#include <iostream>
#include <fstream>
#include <optional>
#include <string>
#include "utils.h"

std::optional<std::string> buscarDispositivoMontado(const std::string& path) {
    std::ifstream mounts{"/proc/mounts"};
    std::string mountPoint;
    std::string device;

    while (mounts >> device >> mountPoint) {
        if (mountPoint == path) {
            return device;
        }
    }

    return std::nullopt;
}

void mostrarDispositivo(const std::string& path) {
    auto resultado = buscarDispositivoMontado(path);
    if (resultado)
        std::cout << "Dispositivo montado em \"" << path << "\": " << *resultado << std::endl;
    else
        std::cout << "Nenhum dispositivo encontrado em \"" << path << "\"\n";
}

int main()
{
   if (const auto device = devices("/"))
      std::cout << *device << "\n";
   else
      std::cout << "Not found\n";
}