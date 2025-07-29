#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <map>

using namespace std;

// Assunto

class Assunto {
public:
    string descricao;

    Assunto(string desc) : descricao(desc) {}
};

// Atendimento

class Atendimento {
public:
    Assunto assunto;
    time_t inicio;
    time_t fim;

    Atendimento(Assunto a, time_t i, time_t f) : assunto(a), inicio(i), fim(f) {}
    
    double duracao() const {
        return difftime(fim, inicio) / 60.0; // duração em minutos
    }
};

// Estatistica

class Estatistica {
public:
    vector<Atendimento> atendimentos;

    void registrarAtendimento(Atendimento a) {
        atendimentos.push_back(a);
    }

    double tempoMinimo() {
        double minTempo = atendimentos.empty() ? 0 : atendimentos[0].duracao();
        for (Atendimento &a : atendimentos) {
            if (a.duracao() < minTempo) minTempo = a.duracao();
        }
        return minTempo;
    }

    double tempoMaximo() {
        double maxTempo = 0;
        for (Atendimento &a : atendimentos) {
            if (a.duracao() > maxTempo) maxTempo = a.duracao();
        }
        return maxTempo;
    }

    double tempoMedio() {
        if (atendimentos.empty()) return 0;
        double somaTempo = 0;
        for (Atendimento &a : atendimentos) {
            somaTempo += a.duracao();
        }
        return somaTempo / atendimentos.size();
    }
    
    int numeroAtendimentosPorAssunto(const string& descricaoAssunto) {
        int count = 0;
        for (Atendimento &a : atendimentos) {
            if (a.assunto.descricao == descricaoAssunto) count++;
        }
        return count;
    }
};

// Caixa

class Caixa {
public:
    int numero;
    vector<Assunto> assuntos;
    int posicao;
    Estatistica estatistica;

    Caixa(int num) : numero(num), posicao(0) {}

    void adicionarAssunto(Assunto a) {
        assuntos.push_back(a);
    }

    void registrarAtendimento(Atendimento a) {
        estatistica.registrarAtendimento(a);
    }

    void incrementarPosicao() {
        posicao++;
    }

    void decrementarPosicao() {
        if (posicao > 0) posicao--;
    }

    void imprimirEstatisticas() {
        cout << "Caixa Numero: " << numero << endl;
        cout << "Tempo Minimo de Atendimento: " << estatistica.tempoMinimo() << " minutos" << endl;
        cout << "Tempo Medio de Atendimento: " << estatistica.tempoMedio() << " minutos" << endl;
        cout << "Tempo Maximo de Atendimento: " << estatistica.tempoMaximo() << " minutos" << endl;
        for (Assunto &a : assuntos) {
            cout << "Numero de Atendimentos para " << a.descricao << ": " << estatistica.numeroAtendimentosPorAssunto(a.descricao) << endl;
        }
        cout << "Total de Atendimentos: " << estatistica.atendimentos.size() << endl;
    }
};

// SistemaFila

class SistemaFila {
public:
    vector<Caixa> caixas;
    vector<Assunto> assuntos;

    void adicionarCaixa(Caixa c) {
        caixas.push_back(c);
    }

    void adicionarAssunto(Assunto a) {
        assuntos.push_back(a);
    }

    void registrarAtendimento(int numeroCaixa, Atendimento a) {
        for (Caixa &c : caixas) {
            if (c.numero == numeroCaixa) {
                c.registrarAtendimento(a);
                return;
            }
        }
    }

    void imprimirEstatisticas(int numeroCaixa) {
        for (Caixa &c : caixas) {
            if (c.numero == numeroCaixa) {
                c.imprimirEstatisticas();
                return;
            }
        }
        cout << "Caixa não encontrado." << endl;
    }
};

// Main

void teste() {
    SistemaFila sistemaFila;

    // Adicionar assuntos
    Assunto assunto1("Reclamacao");
    Assunto assunto2("Informacao");
    sistemaFila.adicionarAssunto(assunto1);
    sistemaFila.adicionarAssunto(assunto2);

    // Adicionar caixas
    Caixa caixa1(1);
    caixa1.adicionarAssunto(assunto1);
    caixa1.adicionarAssunto(assunto2);
    sistemaFila.adicionarCaixa(caixa1);

    Caixa caixa2(2);
    caixa2.adicionarAssunto(assunto2);
    sistemaFila.adicionarCaixa(caixa2);

    // Registrar atendimentos
    time_t agora = time(0);
    time_t depois = agora + 300; // 5 minutos depois
    Atendimento atendimento1(assunto1, agora, depois);
    sistemaFila.registrarAtendimento(1, atendimento1);

    depois = agora + 450; // 7.5 minutos depois
    Atendimento atendimento2(assunto2, agora, depois);
    sistemaFila.registrarAtendimento(1, atendimento2);

    // Imprimir estatísticas
    sistemaFila.imprimirEstatisticas(1);

    return 0;
}

int main() {
    SistemaFila sistemaFila;
    int opcao;

    do {
        cout << "\n=== SISTEMA DE FILA DE ATENDIMENTO ===\n";
        cout << "1. Adicionar assunto\n";
        cout << "2. Adicionar caixa\n";
        cout << "3. Registrar atendimento\n";
        cout << "4. Consultar estatísticas de caixa\n";
        cout << "5. Teste de sistema\n";
        cout << "0. Sair\n";
        cout << "Escolha: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string descricao;
                cout << "Descrição do assunto: ";
                getline(cin, descricao);
                sistemaFila.adicionarAssunto(Assunto(descricao));
                cout << "Assunto adicionado.\n";
                break;
            }

            case 2: {
                int numero;
                cout << "Número da caixa: ";
                cin >> numero;
                cin.ignore();
                Caixa c(numero);
                cout << "Assuntos disponíveis:\n";
                for (size_t i = 0; i < sistemaFila.assuntos.size(); i++)
                    cout << i << " - " << sistemaFila.assuntos[i].descricao << "\n";
                cout << "Digite índice dos assuntos a vincular (-1 para terminar): ";
                int idx;
                while (cin >> idx && idx != -1) {
                    if (idx >= 0 && idx < sistemaFila.assuntos.size()) {
                        c.adicionarAssunto(sistemaFila.assuntos[idx]);
                    } else {
                        cout << "Índice inválido.\n";
                    }
                }
                cin.ignore();
                sistemaFila.adicionarCaixa(c);
                cout << "Caixa adicionada.\n";
                break;
            }

            case 3: {
                int numeroCaixa, idxAssunto;
                cout << "Número da caixa: "; cin >> numeroCaixa;
                cout << "Assuntos disponíveis:\n";
                for (size_t i = 0; i < sistemaFila.assuntos.size(); i++)
                    cout << i << " - " << sistemaFila.assuntos[i].descricao << "\n";
                cout << "Escolha o índice do assunto: "; cin >> idxAssunto;
                cin.ignore();
                if (idxAssunto < 0 || idxAssunto >= sistemaFila.assuntos.size()) {
                    cout << "Assunto inválido.\n";
                    break;
                }
                Assunto assunto = sistemaFila.assuntos[idxAssunto];
                time_t inicio = time(0);
                int duracaoMin;
                cout << "Duração do atendimento (minutos): "; cin >> duracaoMin;
                time_t fim = inicio + (duracaoMin * 60);
                Atendimento atendimento(assunto, inicio, fim);
                sistemaFila.registrarAtendimento(numeroCaixa, atendimento);
                cout << "Atendimento registrado.\n";
                break;
            }

            case 4: {
                int numero;
                cout << "Número da caixa: ";
                cin >> numero;
                sistemaFila.imprimirEstatisticas(numero);
                break;
            }

            case 5: {
                cout << "Executando teste de sistema...\n";
                teste();
                break;
            }
            
            case 0:
                cout << "Encerrando sistema...\n";
                break;

            default:
                cout << "Opção inválida.\n";
        }
    } while (opcao != 0);

    return 0;
}