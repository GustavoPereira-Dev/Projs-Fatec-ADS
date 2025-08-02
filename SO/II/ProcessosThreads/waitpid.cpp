#include <iostream>
#include <unistd.h>
#include <sys/wait.h>
#include <cstdlib>
#include "utils.h"

using namespace std;

void exemploWaitpid() {
    pid_t pid[5];
    int status;

    for (int i = 0; i < 5; i++) {
        pid[i] = fork();
        if (pid[i] == 0) {
            sleep(1);
            exit(100 + i);
        }
    }

    for (int i = 0; i < 5; i++) {
        pid_t cpid = waitpid(pid[i], &status, 0);
        if (WIFEXITED(status)) {
            std::cout << "Filho " << cpid << " saiu com código " << WEXITSTATUS(status) << std::endl;
        }
    }
}

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