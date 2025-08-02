#ifndef UTILS_H
#define UTILS_H

#include <string>

void copiarArquivo(const std::string &origem, const std::string &destino);
void listarDiretorio(const std::string &path);
void listarRecursivo(const std::string &path);
void listarEspecial(const std::string &path);
void mostrarDiretorioAtual();
void moverArquivo(const std::string& origem, const std::string& destino);
void exibirPermissoes(const std::string &path);
void obterUUIDDispositivo(const std::string& caminhoDispositivo);
void mostrarInfoStat(const std::string& caminho);
void mostrarDispositivo(const std::string& path);
void exibirPermissoes(const std::string& caminho);

/*#ifndef UTILS_H
#define UTILS_H

#include <string>

void copiarArquivo(const std::string& origem, const std::string& destino);
void listarDiretorio(const std::string& path);
void listarRecursivo(const std::string& path);
void exibirPermissoes(const std::string& path);
void mostrarDiretorioAtual();
void moverArquivo(const std::string& origem, const std::string& destino);
void obterUUIDDispositivo(const std::string& caminhoDispositivo);
void mostrarInfoStat(const std::string& caminho);

#endif
 */
#endif