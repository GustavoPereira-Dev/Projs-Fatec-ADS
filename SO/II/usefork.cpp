#include <iostream>
#include <unistd.h>

using namespace std;
int variavelGlobal = 2;

int main() {
   string identidade;
   int variavelFuncao = 20;

    // O ID retornado pelo fork() é zero quando o processo filho é criado
    pid_t pID = fork();

    // Se é zero, então é um processo filho
    if (pID == 0) {
      identidade = "Processo filho: ";
      variavelGlobal++;
      variavelFuncao++;
   }
   
   // Se o pID retornado pelo fork for menor que zero, então houve falhas
   else if (pID < 0) {
      std::cerr << "Failed to fork" << std::endl;
      exit(1);
   }
   
   // Caso não seja nenhum dos dois, então é o processo pai
   else {
      identidade = "Processo pai:";
   }
   
   // O código abaixo por não estar na sequência de IFs acima (desvio de bloco de código condicional) então é executado por ambos os processos, o pai e o filho.
   std::cout << identidade;
   std::cout << " Variavel Global: " << variavelGlobal ;
   std::cout << " Variável Funcao: " << variavelFuncao << std::endl;
   return 0;
}
