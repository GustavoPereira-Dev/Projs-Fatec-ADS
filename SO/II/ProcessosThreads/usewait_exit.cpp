#include <iostream>
#include <sys/wait.h>
#include <unistd.h>
#include <cstdlib>
#include <csignal>
#include "utils.h"

void exemploWaitExit() {
    pid_t pid = fork();
    int status;

    if (pid == 0) {
        std::cout << "Filho saindo com exit(1)" << std::endl;
        exit(1);
    } else {
        pid_t cpid = wait(&status);

        if (WIFEXITED(status)) {
            std::cout << "Filho terminou com exit status " << WEXITSTATUS(status) << std::endl;
        } else if (WIFSIGNALED(status)) {
            psignal(WTERMSIG(status), "Filho terminado por sinal");
        }

        std::cout << "Pai: PID=" << getpid() << ", Filho PID=" << cpid << std::endl;
    }
}

int main() {

   // O ID retornado pelo fork() é zero quando o processo filho é criado
   pid_t pID = fork();
   pid_t cpid;
   int stat;
   
   if ( pID == 0 ) {
      std::cout << "Saindo do processo filho. " << std::endl;
	  exit(1);
   } else {
      cpid = wait(NULL);
   }
   
   if (WIFEXITED(stat)){
      std::cout << "WEXIT " << WEXITSTATUS(stat);
   } else if (WIFSIGNALED(stat)) {
      psignal(WTERMSIG(stat), "Sinal de saída: ");
   }
   
   std::cout << "PID do pai: " << getpid() << std::endl;
   std::cout << "PID do filho: " << cpid << std::endl;
   return 0;
}