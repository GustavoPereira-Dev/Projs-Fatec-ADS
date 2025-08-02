#ifndef UTILS_H
#define UTILS_H

#include <string>

// Threads
void executarThreadSimples(const std::string& mensagem);

// Processos com fork
void exemploFork();
void exemploWait();
void exemploWaitExit();
void exemploWaitpid();
void exemploVariaveisComFork();

// Sinais entre pai e filho
void comunicarComSinal();

// Pipes e comandos externos
void executarComSystem(const std::string& comando);
void executarComPopen(const std::string& comando);

// UUID de dispositivo
void mostrarUUID(const std::string& caminhoParticao);

#endif