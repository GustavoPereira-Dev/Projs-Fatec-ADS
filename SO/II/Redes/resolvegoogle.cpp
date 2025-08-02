#include <netdb.h>
#include <arpa/inet.h>
#include <cstring>
#include <iostream>
#include "utils.h"

void resolverDNSCompleto(const std::string& hostname) {
    struct hostent* he = gethostbyname(hostname.c_str());
    struct in_addr a;

    if (he) {
        std::cout << "Nome: " << he->h_name << std::endl;
        for (char** alias = he->h_aliases; *alias != nullptr; ++alias) {
            std::cout << "Alias: " << *alias << std::endl;
        }
        for (char** addr_list = he->h_addr_list; *addr_list != nullptr; ++addr_list) {
            std::memcpy(&a, *addr_list, sizeof(a));
            std::cout << "Endereço IP: " << inet_ntoa(a) << std::endl;
        }
    } else {
        std::cerr << "Falha ao resolver DNS para " << hostname << std::endl;
    }
}

int main () {
   struct hostent *he;
   struct in_addr a;
   he = gethostbyname("www.google.com.br");
   if ( he ) {
      std::cout << "Name: " << he->h_name << std::endl;
      while ( *he->h_aliases ) {
         std::cout << "Alias: " << *he->h_aliases++ << std::endl;
      }
      while ( *he->h_addr_list ) {
         bcopy( *he->h_addr_list++, (char *) &a, sizeof(a) );
         std::cout << "Address: " << inet_ntoa(a) << std::endl;
      }
   } else {
      std::cerr << "error" << std::endl;
   }
   return 0;
}