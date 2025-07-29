#include <iostream>
#include <string>
#include <vector>
#include <memory>
using namespace std;

// Marca
class Marca {
    string nome;
public:
    Marca(string nome) : nome(nome) {}
    string getNome() const { return nome; }
};

// Modelo
class Modelo {
    string nome;
    Marca* marca;
public:
    Modelo(string nome, Marca* marca) : nome(nome), marca(marca) {}
    string getNome() const { return nome; }
    Marca* getMarca() const { return marca; }
};

// Automóvel
class Automovel {
protected:
    string placa, cor, tipoCombustivel, chassi;
    int ano, portas;
    double quilometragem, valorDiaria;
    bool disponivel;
    Modelo* modelo;
public:
    Automovel(string placa, string cor, int ano, string tipoCombustivel,
              int portas, double quilometragem, string chassi, double valorDiaria, Modelo* modelo)
        : placa(placa), cor(cor), ano(ano), tipoCombustivel(tipoCombustivel),
          portas(portas), quilometragem(quilometragem), chassi(chassi),
          valorDiaria(valorDiaria), disponivel(true), modelo(modelo) {}

    virtual double getValorDiaria() const = 0;
    bool isDisponivel() const { return disponivel; }
    void setDisponivel(bool d) { disponivel = d; }
    string getPlaca() const { return placa; }
    string getModeloNome() const { return modelo->getNome(); }
};

// Passeio
class Passeio : public Automovel {
    int qtdeLugares;
public:
    Passeio(string placa, string cor, int ano, string tipoCombustivel,
            int portas, double quilometragem, string chassi, double valorDiaria,
            Modelo* modelo, int qtdeLugares)
        : Automovel(placa, cor, ano, tipoCombustivel, portas, quilometragem, chassi, valorDiaria, modelo),
          qtdeLugares(qtdeLugares) {}

    double getValorDiaria() const override {
        return valorDiaria; // Diária base
    }
};

// Utilitário
class Utilitario : public Automovel {
    double cargaMaxima;
public:
    Utilitario(string placa, string cor, int ano, string tipoCombustivel,
               int portas, double quilometragem, string chassi, double valorDiaria,
               Modelo* modelo, double cargaMaxima)
        : Automovel(placa, cor, ano, tipoCombustivel, portas, quilometragem, chassi, valorDiaria, modelo),
          cargaMaxima(cargaMaxima) {}

    double getValorDiaria() const override {
        return valorDiaria * 1.2; // 20% adicional
    }
};

// Cliente
class Cliente {
    string cpf, nome, endereco, telefone, email;
public:
    Cliente(string cpf, string nome, string endereco, string telefone, string email)
        : cpf(cpf), nome(nome), endereco(endereco), telefone(telefone), email(email) {}

    string getNome() const { return nome; }
    string getCpf() const { return cpf; }
};

// Locação
class Locacao {
    string dataRetirada, horaRetirada, dataDevolucao, horaDevolucao;
    Automovel* autoLocado;
    Cliente* cliente;
    double valorLocacao;
public:
    Locacao(string dRet, string hRet, string dDev, string hDev, Automovel* autoLocado, Cliente* cliente)
        : dataRetirada(dRet), horaRetirada(hRet), dataDevolucao(dDev), horaDevolucao(hDev),
          autoLocado(autoLocado), cliente(cliente), valorLocacao(0) {}

    void calcularLocacao(int dias) {
        valorLocacao = autoLocado->getValorDiaria() * dias;
    }

    double getValorLocacao() const { return valorLocacao; }
    Cliente* getCliente() const { return cliente; }
    Automovel* getAutomovel() const { return autoLocado; }
};

// GerenciadorLocacao (Controller) 
class GerenciadorLocacao {
    vector<unique_ptr<Automovel>> frota;
    vector<unique_ptr<Cliente>> clientes;
    vector<Locacao> locacoes;

public:
    void adicionarAutomovel(unique_ptr<Automovel> autoPtr) {
        frota.push_back(move(autoPtr));
    }

    void adicionarCliente(unique_ptr<Cliente> clientePtr) {
        clientes.push_back(move(clientePtr));
    }

    Automovel* buscarAutomovelPorPlaca(string placa) {
        for (auto &autoPtr : frota) {
            if (autoPtr->getPlaca() == placa) return autoPtr.get();
        }
        return nullptr;
    }

    Cliente* buscarClientePorCpf(string cpf) {
        for (auto &cliPtr : clientes) {
            if (cliPtr->getCpf() == cpf) return cliPtr.get();
        }
        return nullptr;
    }

    void alugar(string cpf, string placa, string dRet, string hRet, string dDev, string hDev, int dias) {
        Cliente* cli = buscarClientePorCpf(cpf);
        Automovel* autoLoc = buscarAutomovelPorPlaca(placa);

        if (cli && autoLoc && autoLoc->isDisponivel()) {
            Locacao loc(dRet, hRet, dDev, hDev, autoLoc, cli);
            loc.calcularLocacao(dias);
            locacoes.push_back(loc);
            autoLoc->setDisponivel(false);

            cout << "Locação registrada para " << cli->getNome()
                 << " com valor R$ " << loc.getValorLocacao() << endl;
        } else {
            cout << "Erro na locação (cliente ou carro indisponível)!" << endl;
        }
    }
};

// InterfaceUsuario (Boundar)
class InterfaceUsuario {
    GerenciadorLocacao gerenciador;
public:
    void demo() {
        Marca marca("Toyota");
        Modelo modelo("Hilux", &marca);

        auto carro1 = make_unique<Utilitario>("ABC-1234", "Branco", 2021, "Diesel",
                                              4, 20000, "CHS123", 300.0, &modelo, 1000);
        gerenciador.adicionarAutomovel(move(carro1));

        auto cliente1 = make_unique<Cliente>("12345678900", "Maria", "Rua X", "9999-8888", "maria@email.com");
        gerenciador.adicionarCliente(move(cliente1));

        gerenciador.alugar("12345678900", "ABC-1234", "01/08/2025", "10:00", "05/08/2025", "09:00", 4);
    }
};

int main() {
    GerenciadorLocacao gerenciador;
    Marca marcaPadrao("Toyota");
    Modelo modeloPadrao("Corolla", &marcaPadrao);
    InterfaceUsuario ui;

    int opcao;
    do {
        cout << "\n=== MENU LOCADORA ===\n";
        cout << "1. Cadastrar cliente\n";
        cout << "2. Cadastrar automóvel (passeio ou utilitário)\n";
        cout << "3. Mostrar clientes\n";
        cout << "4. Mostrar frota\n";
        cout << "5. Realizar locação\n";
        cout << "6. Teste de interface\n";
        cout << "0. Sair\nEscolha: ";
        cin >> opcao;

        switch (opcao) {
            case 1: {
                cin.ignore();
                string cpf, nome, endereco, telefone, email;
                cout << "CPF: "; getline(cin, cpf);
                cout << "Nome: "; getline(cin, nome);
                cout << "Endereço: "; getline(cin, endereco);
                cout << "Telefone: "; getline(cin, telefone);
                cout << "Email: "; getline(cin, email);
                gerenciador.adicionarCliente(make_unique<Cliente>(cpf, nome, endereco, telefone, email));
                cout << "Cliente adicionado.\n";
                break;
            }

            case 2: {
                cin.ignore();
                string placa, cor, tipoCombustivel, chassi;
                int ano, portas, tipo;
                double km, diaria;

                cout << "Placa: "; getline(cin, placa);
                cout << "Cor: "; getline(cin, cor);
                cout << "Ano: "; cin >> ano;
                cin.ignore();
                cout << "Combustível: "; getline(cin, tipoCombustivel);
                cout << "Portas: "; cin >> portas;
                cout << "Quilometragem: "; cin >> km;
                cin.ignore();
                cout << "Chassi: "; getline(cin, chassi);
                cout << "Diária (R$): "; cin >> diaria;
                cout << "Tipo (1-Passeio, 2-Utilitário): "; cin >> tipo;

                if (tipo == 1) {
                    int lugares;
                    cout << "Qtd. Lugares: "; cin >> lugares;
                    gerenciador.adicionarAutomovel(make_unique<Passeio>(
                        placa, cor, ano, tipoCombustivel, portas, km, chassi, diaria, &modeloPadrao, lugares));
                } else {
                    double carga;
                    cout << "Carga Máx (kg): "; cin >> carga;
                    gerenciador.adicionarAutomovel(make_unique<Utilitario>(
                        placa, cor, ano, tipoCombustivel, portas, km, chassi, diaria, &modeloPadrao, carga));
                }
                cout << "Veículo cadastrado.\n";
                break;
            }

            case 3: {
                cout << "\nClientes cadastrados:\n";
                for (const auto& c : gerenciador.clientes)
                    cout << "- " << c->getNome() << " (CPF: " << c->getCpf() << ")\n";
                break;
            }

            case 4: {
                cout << "\nFrota disponível:\n";
                for (const auto& a : gerenciador.frota)
                    cout << "- " << a->getPlaca()
                         << " | Modelo: " << a->getModeloNome()
                         << " | Disponível: " << (a->isDisponivel() ? "Sim" : "Não") << "\n";
                break;
            }

            case 5: {
                cin.ignore();
                string cpf, placa, dRet, hRet, dDev, hDev;
                int dias;
                cout << "CPF do cliente: "; getline(cin, cpf);
                cout << "Placa do automóvel: "; getline(cin, placa);
                cout << "Data retirada: "; getline(cin, dRet);
                cout << "Hora retirada: "; getline(cin, hRet);
                cout << "Data devolução: "; getline(cin, dDev);
                cout << "Hora devolução: "; getline(cin, hDev);
                cout << "Qtd dias: "; cin >> dias;
                gerenciador.alugar(cpf, placa, dRet, hRet, dDev, hDev, dias);
                break;
            }

            case 6: {
                cout << "Iniciando interface de usuário...\n";
                ui.demo();
                break;
            }

            case 0:
                cout << "Encerrando...\n";
                break;

            default:
                cout << "Opção inválida.\n";
        }
    } while (opcao != 0);

    return 0;
}
