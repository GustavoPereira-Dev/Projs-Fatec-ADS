#include <iostream>
#include <SFML/Network.hpp>
#include <string>

static bool port_is_open(const std::string& address, int port)
{
   return (sf::TcpSocket().connect(address, port) == sf::Socket::Done);
}

int main()
{
   std::cout << "Port 80 : aied.com.br" << std::endl;
   if ( port_is_open("aied.com.br", 80) )
      std::cout << "Port is OPEN" << std::endl;
   else
      std::cout << "Port is CLOSED" << std::endl;
   return 0;
}