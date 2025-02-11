#include <iostream>
#include <string>
#include <vector>

using namespace std;

// Cliente

class Cliente {
public:
    string nome;
    string telefone;
    bool clienteAntigo;

    Cliente(string n, string t, bool ca) : nome(n), telefone(t), clienteAntigo(ca) {}
};

// Tema

class Tema {
public:
    string nome;
    double preco;

    Tema(string n, double p) : nome(n), preco(p) {}
};

// Festa

class Festa {
public:
    string endereco;
    string data;
    string horaInicio;
    string horaTermino;

    Festa(string e, string d, string hi, string ht) : endereco(e), data(d), horaInicio(hi), horaTermino(ht) {}
};

// Aluguel

class Aluguel {
public:
    Cliente cliente;
    Festa festa;
    Tema tema;
    double valorCobrado;

    Aluguel(Cliente c, Festa f, Tema t, double desconto) : cliente(c), festa(f), tema(t) {
        valorCobrado = t.preco;
        if (cliente.clienteAntigo) {
            valorCobrado *= (1 - desconto);
        }
    }

    void imprimirDetalhes() {
        cout << "Cliente: " << cliente.nome << endl;
        cout << "Telefone: " << cliente.telefone << endl;
        cout << "Endereco: " << festa.endereco << endl;
        cout << "Data da Festa: " << festa.data << endl;
        cout << "Hora de Inicio: " << festa.horaInicio << endl;
        cout << "Hora de Termino: " << festa.horaTermino << endl;
        cout << "Tema: " << tema.nome << endl;
        cout << "Valor Cobrado: " << valorCobrado << endl;
    }
};

// Buffet

class Buffet {
public:
    vector<Cliente> clientes;
    vector<Tema> temas;
    vector<Aluguel> aluguels;
    double desconto;

    Buffet(double d) : desconto(d) {}

    void cadastrarCliente(Cliente c) {
        clientes.push_back(c);
    }

    void adicionarTema(Tema t) {
        temas.push_back(t);
    }

    void cadastrarAluguel(Cliente c, Festa f, Tema t) {
        Aluguel aluguel(c, f, t, desconto);
        aluguels.push_back(aluguel);
    }

    void listarAluguels() {
        for (Aluguel a : aluguels) {
            a.imprimirDetalhes();
            cout << "---------------------------" << endl;
        }
    }
};

// Main

int main() {
    Buffet buffet(0.1); // 10% de desconto para clientes antigos

    // Cadastrar clientes
    Cliente cliente1("Rafaela", "1122334455", true);
    Cliente cliente2("Joao", "2233445566", false);
    buffet.cadastrarCliente(cliente1);
    buffet.cadastrarCliente(cliente2);

    // Adicionar temas
    Tema tema1("Festa Princesa", 1500.0);
    Tema tema2("Festa Super Heroi", 2000.0);
    buffet.adicionarTema(tema1);
    buffet.adicionarTema(tema2);

    // Cadastrar aluguels
    Festa festa1("Rua A, 123", "12/03/2025", "15:00", "19:00");
    buffet.cadastrarAluguel(cliente1, festa1, tema1);

    Festa festa2("Rua B, 456", "20/03/2025", "14:00", "18:00");
    buffet.cadastrarAluguel(cliente2, festa2, tema2);

    // Listar aluguels
    buffet.listarAluguels();

    return 0;
}
