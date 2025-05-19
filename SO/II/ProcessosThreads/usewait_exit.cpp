#include <iostream>
#include <stdlib.h>
#include<sys/wait.h>
#include <signal.h>

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