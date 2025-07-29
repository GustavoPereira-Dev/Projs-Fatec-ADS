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

void teste() {
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

int main() {
    Hotel hotel;
    int opcao;

    // Quartos padrão
    hotel.quartos.push_back(Quarto(101, 200.0));
    hotel.quartos.push_back(Quarto(102, 250.0));

    do {
        cout << "\n=== HOTELARIA ===\n";
        cout << "1. Cadastrar hóspede\n";
        cout << "2. Alugar quarto\n";
        cout << "3. Adicionar serviço\n";
        cout << "4. Registrar consumo frigobar\n";
        cout << "5. Imprimir fatura\n";
        cout << "6. Teste de sistema\n";
        cout << "0. Sair\n";
        cout << "Escolha: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string nome, cpf, endereco, telefone;
                cout << "Nome: "; getline(cin, nome);
                cout << "CPF: "; getline(cin, cpf);
                cout << "Endereço: "; getline(cin, endereco);
                cout << "Telefone: "; getline(cin, telefone);
                hotel.cadastrarHospede(Hospede(nome, cpf, endereco, telefone));
                cout << "Hóspede cadastrado!\n";
                break;
            }

            case 2: {
                if (hotel.hospedes.empty()) {
                    cout << "Cadastre um hóspede primeiro.\n";
                    break;
                }
                int idx;
                cout << "Hóspedes:\n";
                for (size_t i = 0; i < hotel.hospedes.size(); i++)
                    cout << i << " - " << hotel.hospedes[i].nome << "\n";
                cout << "Escolha o índice do hóspede: ";
                cin >> idx;
                int num;
                cout << "Número do quarto: "; cin >> num;
                Fatura f;
                hotel.alugarQuarto(num, hotel.hospedes[idx], f);
                break;
            }

            case 3: {
                int faturaIdx;
                string desc;
                double preco;
                cout << "Número da fatura: "; cin >> faturaIdx;
                cin.ignore();
                cout << "Descrição do serviço: "; getline(cin, desc);
                cout << "Preço: "; cin >> preco;
                hotel.adicionarServico(faturaIdx, Servico(desc, preco));
                break;
            }

            case 4: {
                int faturaIdx;
                double valor;
                cout << "Número da fatura: "; cin >> faturaIdx;
                cout << "Valor do consumo: "; cin >> valor;
                hotel.consumoFrigobar(faturaIdx, valor);
                break;
            }

            case 5: {
                int faturaIdx;
                cout << "Número da fatura: "; cin >> faturaIdx;
                hotel.imprimirFatura(faturaIdx);
                break;
            }

            case 6:{
                cout << "Executando teste de sistema...\n";
                teste();
                break;
            }
            case 0:
                cout << "Até logo!\n";
                break;

            default:
                cout << "Opção inválida.\n";
        }
    } while (opcao != 0);

    return 0;
}