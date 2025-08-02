#include <netdb.h>
#include <arpa/inet.h>
#include <iostream>
#include <netdb.h>
#include <arpa/inet.h>
#include <iostream>
#include "utils.h"

void resolverIPv4Simples(const std::string& hostname) {
    struct hostent* he = gethostbyname(hostname.c_str());
    if (!he || !he->h_addr_list[0]) {
        std::cerr << "Não foi possível obter IP IPv4 para " << hostname << std::endl;
        return;
    }
    char* ip = inet_ntoa(*(struct in_addr*)he->h_addr_list[0]);
    std::cout << "IP IPv4: " << ip << std::endl;
}

int main()
{
   struct hostent *he = gethostbyname("www.google.com.br");
   char *ip = inet_ntoa( *(struct in_addr*)he->h_addr_list[0] );
   std::cout << ip;
   return 0;
}