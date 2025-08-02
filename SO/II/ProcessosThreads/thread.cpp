#include <iostream>
#include <thread>
#include "utils.h"

using namespace std;

void threadInterna(std::string msg) {
    std::cout << "A thread está falando: " << msg << std::endl;
}

void executarThreadSimples(const std::string& mensagem) {
    std::thread t1(threadInterna, mensagem);

    // Aqui você pode adicionar tarefas paralelas simulando trabalho pesado
    std::cout << "Thread iniciada em paralelo..." << std::endl;

    t1.join(); // Aguarda finalização da thread para liberar recursos
    std::cout << "Thread finalizada." << std::endl;
}

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