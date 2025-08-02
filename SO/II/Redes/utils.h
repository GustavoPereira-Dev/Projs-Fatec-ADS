#ifndef UTILS_H
#define UTILS_H

#include <string>

// DNS
void resolverDNSCompleto(const std::string& hostname);
void resolverIPv4Simples(const std::string& hostname);

// Port scanner
bool portaAberta(const std::string& hostname, int port);
void testarPorta(const std::string& hostname, int port);

// cURL
void fazerPostJson(const std::string& url, const std::string& json);
void fazerDownloadArquivo(const std::string& url, const std::string& destino);

#endif