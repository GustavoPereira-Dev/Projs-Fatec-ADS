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

// Main
int main() {
    InterfaceUsuario ui;
    ui.demo();
    return 0;
}
