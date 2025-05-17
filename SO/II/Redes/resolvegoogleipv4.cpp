#include <netdb.h>
#include <arpa/inet.h>
#include <iostream>

int main(){
{
   struct hostent *he = gethostbyname("www.google.com.br");
   char *ip = inet_ntoa( *(struct in_addr*)he->h_addr_list[0] );
   std::cout << ip;
   return 0;
}