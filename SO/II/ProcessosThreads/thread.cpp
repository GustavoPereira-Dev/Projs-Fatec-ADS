#include <iostream>
#include <thread>

using namespace std;

// Uma função que sera executada como uma Thread
void task1(std::string msg)
{
   std::cout << "A thread está falando: " << msg;
}

int main()
{
   //Invocando uma thread não bloqueante para ser executada em paralelo.
   thread t1(task1, "Olá");
   
   //Aqui você tem seu programa pesado, que ocorre em paralelo com a thread
   
   // O método JOIN aguarda a finalização da Thread para liberar recursos juntos
   
   t1.join();
}