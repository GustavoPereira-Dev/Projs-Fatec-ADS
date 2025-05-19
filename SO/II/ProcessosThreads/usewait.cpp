#include <iostream>
#include <unistd.h>
#include<sys/wait.h>

using namespace std;

int main() {
   // O ID retornado pelo fork() é zero quando o processo filho é criado
   pid_t pID = fork();
   pid_t cpid;
   
   if ( pID == 0 ) {
      std::cout << "Saindo do processo filho. " << std::endl;
	  return 0;
   } else {
      cpid = wait(NULL);
   }
   
   std::cout << "PID do pai: " << getpid() << std::endl;
   std::cout << "PID do filho: " << cpid << std::endl;
   return 0;
}