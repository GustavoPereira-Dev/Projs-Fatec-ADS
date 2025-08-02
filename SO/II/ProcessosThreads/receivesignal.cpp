#include <iostream>
#include <csignal>
#include <unistd.h>
#include "utils.h"

using namespace std; 

void handlerPai(int sig) {
    std::cout << "Pai recebeu sinal (" << sig << ")" << std::endl;
}

void handlerFilho(int sig) {
    std::cout << "Filho recebeu sinal (" << sig << ")" << std::endl;
    sleep(1);
    kill(getppid(), SIGUSR1);
}

void comunicarComSinal() {
    pid_t pid = fork();

    if (pid < 0) {
        std::cerr << "Erro ao criar processo." << std::endl;
        return;
    } else if (pid == 0) {
        signal(SIGUSR1, handlerFilho);
        std::cout << "Filho aguardando sinal..." << std::endl;
        pause();
    } else {
        signal(SIGUSR1, handlerPai);
        sleep(2);
        std::cout << "Pai enviando sinal ao filho..." << std::endl;
        kill(pid, SIGUSR1);
        std::cout << "Pai aguardando resposta..." << std::endl;
        pause();
    }
}

void signal_parent_handler(int signum) { 
    std::cout << "Processo (PAI) será interrompido pelo sinal: (" << signum << ")." << std::endl; 
} 

void signal_child_handler(int signum) { 
    std::cout << "Processo (FILHO) será interrompido pelo sinal: (" << signum << ")." << std::endl; 
    sleep(1); 
    kill(getppid(), SIGUSR1); 
} 

int main () { 
    pid_t pid; 
    pid = fork(); 
    if(pid < 0) { 
        std::cout << "Falha!";  
        exit(0); 
    }  else if(pid == 0) { // Processo filho o PID retornado pelo fork é
        signal(SIGUSR1, signal_child_handler); 
        std::cout << "Processo filho aguardando sinal." << std::endl; 
        pause(); 
    }  else { // é o Pai 
        signal(SIGUSR1, signal_parent_handler); 
        sleep(2); 
        std::cout << "Enviando sinal para o filho." << std::endl; 
        kill(pid, SIGUSR1); 
        std::cout << "Processo pai aguardando resposta." << std::endl; 
        pause(); 
    } 
    return 0; 
}