#include <iostream>
#include <vector>
#include <string>
using namespace std;

// TipoAssociado
enum TipoAssociado { STANDARD, GOLD, BLACK };

// Dependente
struct Dependente {
    string nomeCompleto;
    string RG;
};

// Associado
struct Associado {
    string nome, RG, CPF, endereco, CEP, bairro, cidade, estado;
    string telefoneResidencial, telefoneComercial, celular, dataCadastro;
    TipoAssociado tipo;
    vector<Dependente> dependentes;
    int mesesInadimplentes = 0;
};

// Reserva
struct Reserva {
    string area;
    string data;
    int quantidade;
};

// Pagamento
struct Pagamento {
    float valor;
    string formaPagamento, data;
};

// AreaReservavel
struct AreaReservavel {
    string nome;
    int quantidadeDisponivel;
};

// Clube Controle
class ClubeControl {
private:
    vector<Associado> associados;
    vector<AreaReservavel> areas;
    vector<Reserva> reservas;

public:
    void cadastrarAssociado(const Associado& a) {
        associados.push_back(a);
        cout << "Associado cadastrado: " << a.nome << "\n";
    }

    void mostrarAssociados() const {
        for (size_t i = 0; i < associados.size(); ++i)
            cout << i << " - " << associados[i].nome << " (" << associados[i].CPF << ")\n";
    }

    void adicionarDependente(int idx, const Dependente& d) {
        if (idx >= 0 && idx < associados.size()) {
            associados[idx].dependentes.push_back(d);
            cout << "Dependente adicionado.\n";
        }
    }

    void cadastrarAreaReservavel(string nome, int qtde) {
        areas.push_back({nome, qtde});
        cout << "Área cadastrada: " << nome << "\n";
    }

    void realizarReserva(int idx, string area, string data, int qtde) {
        if (idx < 0 || idx >= associados.size()) return;
        Associado& a = associados[idx];
        if (a.mesesInadimplentes >= 4) { cout << "Bloqueado.\n"; return; }
        if (a.mesesInadimplentes == 3 && area != "quadra") { cout << "Apenas quadras permitidas.\n"; return; }
        if (a.mesesInadimplentes == 2 && (area == "haras" || area == "campo de golfe" || area == "piscina")) {
            cout << "Área restrita por inadimplência.\n"; return;
        }
        reservas.push_back({area, data, qtde});
        cout << "Reserva realizada.\n";
    }

    void registrarPagamento(int idx, float valor, string forma, string data) {
        if (idx >= 0 && idx < associados.size()) {
            associados[idx].mesesInadimplentes = 0;
            cout << "Pagamento registrado: R$ " << valor << " - " << forma << " - " << data << "\n";
        }
    }

    void gerarCobranca(int idx) {
        if (idx >= 0 && idx < associados.size()) {
            float valor = 100.0;
            if (associados[idx].mesesInadimplentes > 0) valor *= 1.05;
            cout << "Valor da cobrança: R$ " << valor << "\n";
            associados[idx].mesesInadimplentes++;
        }
    }

    int totalAssociados() const { return associados.size(); }
};

// Main
int main() {
    ClubeControl clube;
    int opcao;

    do {
        cout << "\n=== CLUBE DE CAMPO ===\n";
        cout << "1. Cadastrar associado\n";
        cout << "2. Mostrar associados\n";
        cout << "3. Adicionar dependente\n";
        cout << "4. Realizar reserva\n";
        cout << "5. Registrar pagamento\n";
        cout << "6. Gerar cobrança\n";
        cout << "0. Sair\n";
        cout << "Escolha: ";
        cin >> opcao;

        switch (opcao) {
            case 1: {
                Associado a;
                cin.ignore();
                cout << "Nome: "; getline(cin, a.nome);
                cout << "CPF: "; getline(cin, a.CPF);
                cout << "Data de cadastro: "; getline(cin, a.dataCadastro);
                a.tipo = GOLD;
                clube.cadastrarAssociado(a);
                break;
            }
            case 2:
                clube.mostrarAssociados();
                break;
            case 3: {
                int idx;
                cout << "Índice do associado: "; cin >> idx;
                cin.ignore();
                Dependente d;
                cout << "Nome dependente: "; getline(cin, d.nomeCompleto);
                cout << "RG dependente: "; getline(cin, d.RG);
                clube.adicionarDependente(idx, d);
                break;
            }
            case 4: {
                int idx, qtde;
                string area, data;
                cout << "Índice do associado: "; cin >> idx;
                cin.ignore();
                cout << "Área: "; getline(cin, area);
                cout << "Data: "; getline(cin, data);
                cout << "Quantidade: "; cin >> qtde;
                clube.realizarReserva(idx, area, data, qtde);
                break;
            }
            case 5: {
                int idx;
                float valor;
                string forma, data;
                cout << "Índice do associado: "; cin >> idx;
                cout << "Valor: "; cin >> valor;
                cin.ignore();
                cout << "Forma de pagamento: "; getline(cin, forma);
                cout << "Data: "; getline(cin, data);
                clube.registrarPagamento(idx, valor, forma, data);
                break;
            }
            case 6: {
                int idx;
                cout << "Índice do associado: "; cin >> idx;
                clube.gerarCobranca(idx);
                break;
            }
            case 0:
                cout << "Encerrando o sistema... Até logo!\n";
                break;
            default:
                cout << "Opção inválida.\n";
        }
    } while (opcao != 0);

    return 0;
}