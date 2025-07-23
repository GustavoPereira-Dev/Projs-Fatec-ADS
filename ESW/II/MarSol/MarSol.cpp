#include <iostream>
#include <string>
#include <vector>
#include <ctime>

using namespace std;

// StatusCorrida
enum StatusCorrida {
    AGUARDANDO_VR,
    AGUARDANDO_AVISO,
    AVISO_EFETUADO,
    TRIPULADO,
    CANCELADO_PASSAGEIRO,
    CANCELADO_COOPERATIVA
};

string statusToString(StatusCorrida s) {
    switch(s) {
        case AGUARDANDO_VR: return "aguardando VR";
        case AGUARDANDO_AVISO: return "aguardando aviso";
        case AVISO_EFETUADO: return "aviso efetuado";
        case TRIPULADO: return "tripulado";
        case CANCELADO_PASSAGEIRO: return "cancelado pelo passageiro";
        case CANCELADO_COOPERATIVA: return "cancelado pela cooperativa por falta de carro";
    }
    return "desconhecido";
}

// Cliente
class Cliente {
private:
    static int nextCodigo;
public:
    int codigo;
    string nome;
    string logradouro, numero, complemento, bairro, municipio, estado;
    string telefone1, telefone2;

    Cliente(string nome) : nome(nome) {
        codigo = nextCodigo++;
    }

    void atualizarEndereco() {
        cout << "Logradouro: "; getline(cin, logradouro);
        cout << "Numero: "; getline(cin, numero);
        cout << "Complemento: "; getline(cin, complemento);
        cout << "Bairro: "; getline(cin, bairro);
        cout << "Municipio: "; getline(cin, municipio);
        cout << "Estado: "; getline(cin, estado);
        cout << "Telefone 1: "; getline(cin, telefone1);
        cout << "Telefone 2: "; getline(cin, telefone2);
    }

    void exibir() {
        cout << "Codigo: " << codigo << " - Nome: " << nome << endl;
        if (!logradouro.empty()) {
            cout << "Endereco: " << logradouro << ", " << numero << " - " << bairro << ", " << municipio << "-" << estado << endl;
            cout << "Telefones: " << telefone1 << ", " << telefone2 << endl;
        } else {
            cout << "Endereco nao cadastrado." << endl;
        }
    }
};
int Cliente::nextCodigo = 1;

// Cooperado
class Cooperado {
public:
    string nome, cpf, cnh, categoria, validadeCNH;
    string numeroVR, placa, modelo, fabricante, cor;
    string logradouro, numero, complemento, bairro, municipio, estado;
    string telefoneResidencial, telefoneCelular;
    string dataEntrada, dataDesligamento;

    void exibir() {
        cout << "Cooperado VR " << numeroVR << " - Nome: " << nome << endl;
    }
};

// Corrida
class Corrida {
private:
    static int nextID;
public:
    int id;
    Cliente* cliente;
    Cooperado* cooperado;
    string enderecoSaida, bairroDestino, dataHora, telefoneContato;
    StatusCorrida status;

    Corrida(Cliente* c) {
        id = nextID++;
        cliente = c;
        cooperado = nullptr;
        status = AGUARDANDO_VR;
    }

    void atribuirCooperado(Cooperado* coop) {
        cooperado = coop;
        status = AGUARDANDO_AVISO;
    }

    void alterarStatus(StatusCorrida novo) {
        status = novo;
    }

    void exibir() {
        cout << "Corrida " << id << " - Cliente: " << cliente->nome
             << " - Status: " << statusToString(status) << endl;
    }
};
int Corrida::nextID = 1;

// VETORES GLOBAIS
vector<Cliente> clientes;
vector<Cooperado> cooperados;
vector<Corrida> corridas;

// FUNÇÕES AUXILIARES
Cliente* buscarClientePorCodigo(int codigo) {
    for (auto &c : clientes) {
        if (c.codigo == codigo) return &c;
    }
    return nullptr;
}

// Menu
void menu() {
    int opc;
    do {
        cout << "\n===== SISTEMA RADIO TAXI MAR & SOL =====\n";
        cout << "1. Cadastrar Cliente\n";
        cout << "2. Cadastrar Cooperado\n";
        cout << "3. Solicitar Corrida\n";
        cout << "4. Listar Corridas\n";
        cout << "0. Sair\n";
        cout << "Opcao: ";
        cin >> opc;
        cin.ignore();

        switch(opc) {
            case 1: {
                string nome;
                cout << "Nome do cliente: ";
                getline(cin, nome);
                clientes.push_back(Cliente(nome));
                cout << "Cliente cadastrado com codigo: " << clientes.back().codigo << endl;
                break;
            }
            case 2: {
                Cooperado coop;
                cout << "Nome: "; getline(cin, coop.nome);
                cout << "CPF: "; getline(cin, coop.cpf);
                cout << "Numero VR: "; getline(cin, coop.numeroVR);
                cout << "Placa: "; getline(cin, coop.placa);
                cooperados.push_back(coop);
                cout << "Cooperado cadastrado." << endl;
                break;
            }
            case 3: {
                int codigo;
                cout << "Codigo do cliente (0 para novo): ";
                cin >> codigo;
                cin.ignore();
                Cliente* c = nullptr;
                if (codigo == 0) {
                    string nome;
                    cout << "Nome do cliente: ";
                    getline(cin, nome);
                    clientes.push_back(Cliente(nome));
                    c = &clientes.back();
                    cout << "Cliente cadastrado com codigo: " << c->codigo << endl;
                } else {
                    c = buscarClientePorCodigo(codigo);
                    if (!c) {
                        cout << "Cliente nao encontrado!" << endl;
                        break;
                    }
                }
                corridas.push_back(Corrida(c));
                cout << "Corrida cadastrada com status 'aguardando VR'." << endl;
                break;
            }
            case 4: {
                for (auto &corr : corridas) {
                    corr.exibir();
                }
                break;
            }
        }
    } while(opc != 0);
}

// Main
int main() {
    menu();
    return 0;
}
