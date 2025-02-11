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

int main() {
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

