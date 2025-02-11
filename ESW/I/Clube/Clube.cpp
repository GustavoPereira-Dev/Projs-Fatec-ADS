#include <iostream>
#include <string>
#include <vector>
#include <ctime>

using namespace std;

// Revista

class Revista {
public:
    string tipoColecao;
    int numeroEdicao;
    int ano;
    int numeroCaixa;

    Revista(string tc, int ne, int a, int nc) : tipoColecao(tc), numeroEdicao(ne), ano(a), numeroCaixa(nc) {}
};

// Caixa

class Caixa {
public:
    int numero;
    string cor;
    string etiqueta;

    Caixa(int num, string c, string e) : numero(num), cor(c), etiqueta(e) {}
};

// Amiguinho

class Amiguinho {
public:
    string nome;
    string nomeMae;
    string telefone;
    string origem;

    Amiguinho(string n, string nm, string t, string o) : nome(n), nomeMae(nm), telefone(t), origem(o) {}
};

// Emprestimo

class Emprestimo {
public:
    Amiguinho amiguinho;
    Revista revista;
    time_t dataEmprestimo;
    time_t dataDevolucao;

    Emprestimo(Amiguinho a, Revista r, time_t de, time_t dd) : amiguinho(a), revista(r), dataEmprestimo(de), dataDevolucao(dd) {}
    
    void imprimirDetalhes() {
        cout << "Amiguinho: " << amiguinho.nome << endl;
        cout << "Nome da Mae: " << amiguinho.nomeMae << endl;
        cout << "Telefone: " << amiguinho.telefone << endl;
        cout << "Origem: " << amiguinho.origem << endl;
        cout << "Revista: " << revista.tipoColecao << " - Edicao: " << revista.numeroEdicao << " - Ano: " << revista.ano << endl;
        cout << "Caixa Numero: " << revista.numeroCaixa << endl;
        cout << "Data de Emprestimo: " << ctime(&dataEmprestimo);
        cout << "Data de Devolucao: " << ctime(&dataDevolucao) << endl;
    }
};

// Clube

class Clube {
public:
    vector<Revista> revistas;
    vector<Caixa> caixas;
    vector<Amiguinho> amiguinhos;
    vector<Emprestimo> emprestimos;

    void cadastrarRevista(Revista r) {
        revistas.push_back(r);
    }

    void cadastrarCaixa(Caixa c) {
        caixas.push_back(c);
    }

    void cadastrarAmiguinho(Amiguinho a) {
        amiguinhos.push_back(a);
    }

    void registrarEmprestimo(Emprestimo e) {
        emprestimos.push_back(e);
    }

    void listarEmprestimos() {
        for (Emprestimo e : emprestimos) {
            e.imprimirDetalhes();
            cout << "---------------------------" << endl;
        }
    }
};

// Main

int main() {
    Clube clube;

    // Cadastrar revistas
    Revista revista1("Cebolinha", 1, 2020, 1);
    Revista revista2("Batman", 2, 2019, 2);
    clube.cadastrarRevista(revista1);
    clube.cadastrarRevista(revista2);

    // Cadastrar caixas
    Caixa caixa1(1, "Azul", "Cebolinha e outros");
    Caixa caixa2(2, "Verde", "Batman e outros");
    clube.cadastrarCaixa(caixa1);
    clube.cadastrarCaixa(caixa2);

    // Cadastrar amiguinhos
    Amiguinho amiguinho1("Pedro", "Maria", "11987654321", "Escola");
    Amiguinho amiguinho2("Ana", "Joana", "11912345678", "Predio");
    clube.cadastrarAmiguinho(amiguinho1);
    clube.cadastrarAmiguinho(amiguinho2);

    // Registrar emprestimos
    time_t agora = time(0);
    time_t devolucao = agora + (7 * 24 * 3600); // 7 dias depois
    Emprestimo emprestimo1(amiguinho1, revista1, agora, devolucao);
    Emprestimo emprestimo2(amiguinho2, revista2, agora, devolucao);
    clube.registrarEmprestimo(emprestimo1);
    clube.registrarEmprestimo(emprestimo2);

    // Listar emprestimos
    clube.listarEmprestimos();

    return 0;
}

