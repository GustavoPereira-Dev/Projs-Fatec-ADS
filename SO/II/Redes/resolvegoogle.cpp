#include <netdb.h>
#include <string.h>
#include <arpa/inet.h>
#include <iostream>

struct hostent *he;
struct in_addr a;

int main () {
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