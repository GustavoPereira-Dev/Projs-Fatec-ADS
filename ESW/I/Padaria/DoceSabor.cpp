#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Produto {
public:
    string nome;
    double precoUnitario;

    Produto(string n, double p) : nome(n), precoUnitario(p) {}
};

class Comanda {
public:
    int numero;
    vector<pair<Produto, int>> itens; // Par de Produto e quantidade

    Comanda(int num) : numero(num) {}

    void adicionarItem(Produto produto, int quantidade) {
        itens.push_back(make_pair(produto, quantidade));
    }

    double calcularTotal() {
        double total = 0;
        for (auto &item : itens) {
            total += item.first.precoUnitario * item.second;
        }
        return total;
    }

    void imprimirComanda() {
        cout << "Comanda Numero: " << numero << endl;
        for (auto &item : itens) {
            cout << "Produto: " << item.first.nome << " - Quantidade: " << item.second << " - Preco Unitario: " << item.first.precoUnitario << endl;
        }
        cout << "Total: " << calcularTotal() << endl;
    }
};

class Padaria {
public:
    vector<Produto> produtos;
    vector<Comanda> comandas;

    void cadastrarProduto(Produto p) {
        produtos.push_back(p);
    }

    void registrarComanda(Comanda c) {
        comandas.push_back(c);
    }

    void imprimirComandas() {
        for (Comanda &c : comandas) {
            c.imprimirComanda();
            cout << "---------------------------" << endl;
        }
    }
};

void teste() {
    Padaria padaria;

    // Cadastrar produtos
    Produto produto1("Pão Francês", 0.50);
    Produto produto2("Café", 3.00);
    Produto produto3("Bolo de Chocolate", 15.00);
    padaria.cadastrarProduto(produto1);
    padaria.cadastrarProduto(produto2);
    padaria.cadastrarProduto(produto3);

    // Registrar comandas
    Comanda comanda1(1);
    comanda1.adicionarItem(produto1, 10); // 10 Pães Franceses
    comanda1.adicionarItem(produto2, 1);  // 1 Café
    padaria.registrarComanda(comanda1);

    Comanda comanda2(2);
    comanda2.adicionarItem(produto3, 1); // 1 Bolo de Chocolate
    padaria.registrarComanda(comanda2);

    // Imprimir comandas
    padaria.imprimirComandas();

    return 0;
}

int main() {
    Padaria padaria;
    int opcao;

    do {
        cout << "\n=== PADARIA DOCE SABOR ===\n";
        cout << "1. Cadastrar produto\n";
        cout << "2. Registrar comanda\n";
        cout << "3. Adicionar item à comanda\n";
        cout << "4. Imprimir comandas\n";
        cout << "5. Teste de sistema\n";
        cout << "0. Sair\n";
        cout << "Escolha: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string nome;
                double preco;
                cout << "Nome do produto: "; getline(cin, nome);
                cout << "Preço unitário: R$ "; cin >> preco;
                padaria.cadastrarProduto(Produto(nome, preco));
                cout << "Produto cadastrado.\n";
                break;
            }

            case 2: {
                int numero;
                cout << "Número da comanda: "; cin >> numero;
                padaria.registrarComanda(Comanda(numero));
                cout << "Comanda registrada.\n";
                break;
            }

            case 3: {
                int comandaIdx, produtoIdx, quantidade;
                if (padaria.comandas.empty() || padaria.produtos.empty()) {
                    cout << "Nenhuma comanda ou produto cadastrado.\n";
                    break;
                }

                cout << "\nComandas disponíveis:\n";
                for (size_t i = 0; i < padaria.comandas.size(); i++)
                    cout << i << " - Comanda " << padaria.comandas[i].numero << endl;

                cout << "Escolha o índice da comanda: "; cin >> comandaIdx;

                cout << "\nProdutos disponíveis:\n";
                for (size_t i = 0; i < padaria.produtos.size(); i++)
                    cout << i << " - " << padaria.produtos[i].nome << " (R$ " << padaria.produtos[i].precoUnitario << ")\n";

                cout << "Escolha o índice do produto: "; cin >> produtoIdx;
                cout << "Quantidade: "; cin >> quantidade;

                if (comandaIdx < padaria.comandas.size() && produtoIdx < padaria.produtos.size()) {
                    padaria.comandas[comandaIdx].adicionarItem(padaria.produtos[produtoIdx], quantidade);
                    cout << "Item adicionado à comanda.\n";
                } else {
                    cout << "Índice inválido.\n";
                }
                break;
            }

            case 4:
                padaria.imprimirComandas();
                break;

            case 5:
                cout << "Executando teste de sistema...\n";
                teste();
                break;
                
            case 0:
                cout << "Encerrando sistema...\n";
                break;

            default:
                cout << "Opção inválida.\n";
        }

    } while (opcao != 0);

    return 0;
}