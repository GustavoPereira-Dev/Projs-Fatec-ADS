#include <blkid/blkid.h>
#include <iostream>
#include "utils.h"

void mostrarUUID(const std::string& caminhoParticao) {
    blkid_probe pr = blkid_new_probe_from_filename(caminhoParticao.c_str());
    if (!pr) {
        std::cerr << "Erro ao abrir: " << caminhoParticao << std::endl;
        return;
    }

    const char* uuid = nullptr;
    blkid_do_probe(pr);
    blkid_probe_lookup_value(pr, "UUID", &uuid, nullptr);

    if (uuid)
        std::cout << "UUID = " << uuid << std::endl;
    else
        std::cout << "UUID não encontrado." << std::endl;

    blkid_free_probe(pr);
}

int main (int argc, char *argv[]) {
   blkid_probe pr;
   const char *uuid;
   std::string partition = "/dev/sda1";
   
   pr = blkid_new_probe_from_filename( partition.c_str() );
   if (!pr) {
      err(2, "Failed to open %s", partition);
   }
   
   blkid_do_probe(pr);
   blkid_probe_lookup_value(pr, "UUID", &uuid, NULL);
   
   printf("UUID=%s\n", uuid);
   
   blkid_free_probe(pr);
   
   return 0;
}