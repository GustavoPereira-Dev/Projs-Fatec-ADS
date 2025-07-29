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

// Função teste

void teste() {
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

int main() {
    Buffet buffet(0.1); // 10% de desconto para clientes antigos
    int opcao;

    do {
        cout << "\n=== MENU BUFFET INFANTIL ===\n";
        cout << "1. Cadastrar cliente\n";
        cout << "2. Adicionar tema\n";
        cout << "3. Registrar festa\n";
        cout << "4. Listar festas alugadas\n";
        cout << "5. Função de Teste\n";
        cout << "0. Sair\n";
        cout << "Escolha: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string nome, telefone;
                char antigo;
                bool clienteAntigo = false;

                cout << "Nome do cliente: "; getline(cin, nome);
                cout << "Telefone: "; getline(cin, telefone);
                cout << "É cliente antigo (s/n)? "; cin >> antigo; cin.ignore();
                clienteAntigo = (antigo == 's' || antigo == 'S');

                Cliente c(nome, telefone, clienteAntigo);
                buffet.cadastrarCliente(c);
                cout << "Cliente cadastrado.\n";
                break;
            }

            case 2: {
                string nome;
                double preco;
                cout << "Nome do tema: "; getline(cin, nome);
                cout << "Preço (R$): "; cin >> preco; cin.ignore();
                buffet.adicionarTema(Tema(nome, preco));
                cout << "Tema adicionado.\n";
                break;
            }

            case 3: {
                if (buffet.clientes.empty() || buffet.temas.empty()) {
                    cout << "É necessário cadastrar ao menos um cliente e um tema antes de alugar.\n";
                    break;
                }

                int clienteIndex, temaIndex;
                string endereco, data, inicio, fim;

                cout << "\nClientes disponíveis:\n";
                for (size_t i = 0; i < buffet.clientes.size(); i++)
                    cout << i << " - " << buffet.clientes[i].nome << "\n";
                cout << "Escolha o cliente: "; cin >> clienteIndex; cin.ignore();

                cout << "\nTemas disponíveis:\n";
                for (size_t i = 0; i < buffet.temas.size(); i++)
                    cout << i << " - " << buffet.temas[i].nome << " (R$ " << buffet.temas[i].preco << ")\n";
                cout << "Escolha o tema: "; cin >> temaIndex; cin.ignore();

                cout << "Endereço da festa: "; getline(cin, endereco);
                cout << "Data da festa: "; getline(cin, data);
                cout << "Hora de início: "; getline(cin, inicio);
                cout << "Hora de término: "; getline(cin, fim);

                Festa festa(endereco, data, inicio, fim);
                buffet.cadastrarAluguel(buffet.clientes[clienteIndex], festa, buffet.temas[temaIndex]);
                cout << "Festa registrada com sucesso!\n";
                break;
            }

            case 4:
                cout << "\nFestas registradas:\n";
                buffet.listarAluguels();
                break;
            
            case 5:
                cout << "Executando função de teste...\n";
                teste();
                break;

            case 0:
                cout << "Encerrando sistema do buffet...\n";
                break;

            default:
                cout << "Opção inválida.\n";
        }

    } while (opcao != 0);

    return 0;
}
