#include <iostream>
#include <string>
#include <vector>

// Quarto

using namespace std;

class Quarto {
public:
    int numero;
    bool reservado;
    double preco;

    Quarto(int num, double pr) : numero(num), reservado(false), preco(pr) {}
};

// Hospede

class Hospede {
public:
    string nome;
    string cpf;
    string endereco;
    string telefone;

    Hospede(string n, string c, string e, string t) : nome(n), cpf(c), endereco(e), telefone(t) {}
};

// Fatura

class Fatura {
public:
    vector<Servico> servicos;
    vector<double> consumosFrigobar;
    double total;

    Fatura() : total(0) {}

    void adicionarServico(Servico s) {
        servicos.push_back(s);
        total += s.preco;
    }

    void adicionarConsumoFrigobar(double valor) {
        consumosFrigobar.push_back(valor);
        total += valor;
    }

    void imprimirFatura() {
        cout << "Fatura:\n";
        for (Servico s : servicos) {
            cout << "Servico: " << s.descricao << " - Preco: " << s.preco << endl;
        }
        for (double consumo : consumosFrigobar) {
            cout << "Consumo Frigobar: " << consumo << endl;
        }
        cout << "Total: " << total << endl;
    }
};

// Servico

class Servico {
public:
    string descricao;
    double preco;

    Servico(string desc, double pr) : descricao(desc), preco(pr) {}
};

// Hotel

class Hotel {
public:
    vector<Quarto> quartos;
    vector<Hospede> hospedes;
    vector<Fatura> faturas;

    void cadastrarHospede(Hospede h) {
        hospedes.push_back(h);
    }

    void alugarQuarto(int numeroQuarto, Hospede h, Fatura &f) {
        for (Quarto &q : quartos) {
            if (q.numero == numeroQuarto && !q.reservado) {
                q.reservado = true;
                faturas.push_back(f);
                cout << "Quarto " << numeroQuarto << " alugado por " << h.nome << endl;
                return;
            }
        }
        cout << "Quarto nao disponivel." << endl;
    }

    void adicionarServico(int numeroFatura, Servico s) {
        faturas[numeroFatura].adicionarServico(s);
    }

    void consumoFrigobar(int numeroFatura, double valor) {
        faturas[numeroFatura].adicionarConsumoFrigobar(valor);
    }

    void imprimirFatura(int numeroFatura) {
        faturas[numeroFatura].imprimirFatura();
    }
};

// Main

int main() {
    Hotel hotel;

    // Adicionar quartos ao hotel
    hotel.quartos.push_back(Quarto(101, 200.0));
    hotel.quartos.push_back(Quarto(102, 250.0));

    // Cadastrar um hospede
    Hospede hospede1("Joao Silva", "123.456.789-00", "Rua A, 123", "11999999999");
    hotel.cadastrarHospede(hospede1);

    // Criar uma fatura para o hospede
    Fatura fatura1;

    // Alugar um quarto para o hospede
    hotel.alugarQuarto(101, hospede1, fatura1);

    // Adicionar servicos e consumo de frigobar
    hotel.adicionarServico(0, Servico("Restaurante", 50.0));
    hotel.consumoFrigobar(0, 30.0);

    // Imprimir fatura
    hotel.imprimirFatura(0);

    return 0;
}