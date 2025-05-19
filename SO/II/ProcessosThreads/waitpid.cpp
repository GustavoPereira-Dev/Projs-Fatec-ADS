#include <iostream>
#include<sys/wait.h>

using namespace std;

int main() {
   pid_t pid[5];
   int i, stat;
   
   for (i = 0; i < 5; i++) {
      pid[i] = fork();
	  if( pid[i] == 0) {
	     sleep(1);
		 exit(100 + i);
	  }
   }
   
   for (i = 0; i < 5; i++) {
      pid_t cpid = waitpid(pid[i], &stat, 0);
	  if (WIFEXITED(stat)) {
	     std::cout << "O filho " << cpid << " terminou com o status: " << WEXITSTATUS(stat) << std::endl;
	  }
	}
	return 0;
}