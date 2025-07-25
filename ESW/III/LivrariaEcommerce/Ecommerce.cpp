#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <memory>
#include <algorithm>

using namespace std;

// Interface Pagável (para integração com sistemas de pagamento)
class Pagavel {
public:
    virtual void realizarPagamento(double valor) = 0;
    virtual ~Pagavel() {}
};

// Interface para cálculo de desconto
class Desconto {
public:
    virtual double calcularDesconto(double precoBase) = 0;
    virtual ~Desconto() {}
};

// Observer: Publicador (estoque notifica interessados)
class NotificacaoAssinante {
public:
    virtual void receberNotificacao(const string& mensagem) = 0;
};

class NotificacaoPublicador {
protected:
    vector<NotificacaoAssinante*> assinantes;
public:
    void adicionarAssinante(NotificacaoAssinante* a) { assinantes.push_back(a); }
    void removerAssinante(NotificacaoAssinante* a) {
        assinantes.erase(remove(assinantes.begin(), assinantes.end(), a), assinantes.end());
    }
    void avisarAssinantes(const string& mensagem) {
        for (auto* a : assinantes) a->receberNotificacao(mensagem);
    }
};

// Endereço
class Endereco {
public:
    string cep, logradouro, numero, bairro, cidade, uf;

    Endereco(string c="", string l="", string n="", string b="", string ci="", string u="") :
        cep(c), logradouro(l), numero(n), bairro(b), cidade(ci), uf(u) {}
};

// Classe base Pessoa
class Pessoa {
public:
    string nome, email, cpf, telefone;
    Pessoa(string n="", string e="", string c="", string t="") : nome(n), email(e), cpf(c), telefone(t) {}
};

// Cliente
class Cliente : public Pessoa, public NotificacaoAssinante {
public:
    vector<Endereco> enderecos;
    vector<string> pedidos; // IDs dos pedidos

    Cliente(string n="", string e="", string c="", string t="") : Pessoa(n,e,c,t) {}

    void receberNotificacao(const string& mensagem) override {
        cout << "[Notificação para Cliente] " << nome << ": " << mensagem << endl;
    }
};

// Funcionário
class Funcionario : public Pessoa, public NotificacaoAssinante {
public:
    string matricula;
    Funcionario(string n="", string e="", string c="", string t="", string m="") :
        Pessoa(n,e,c,t), matricula(m) {}

    void receberNotificacao(const string& mensagem) override {
        cout << "[Notificação para Funcionário] " << nome << ": " << mensagem << endl;
    }
};

// Classe abstrata Livro
class Livro : public Desconto {
protected:
    string isbn, titulo, autor, editora, categoria;
    int numPaginas;
    double preco;
    string status; // disponível, indisponível, em processo
public:
    Livro(string i, string t, string a, string e, string c, int n, double p, string s="disponível")
        : isbn(i), titulo(t), autor(a), editora(e), categoria(c), numPaginas(n), preco(p), status(s) {}

    virtual double calcularDesconto(double precoBase) = 0; // polimórfico
    string getTitulo() const { return titulo; }
    double getPreco() const { return preco; }
};

// Subclasses do Livro (Brochura, Digital, CapaDura)
class Brochura : public Livro {
public:
    Brochura(string i, string t, string a, string e, string c, int n, double p)
        : Livro(i,t,a,e,c,n,p) {}
    double calcularDesconto(double precoBase) override {
        return precoBase * 0.95; // 5% desconto
    }
};

class Digital : public Livro {
public:
    Digital(string i, string t, string a, string e, string c, int n, double p)
        : Livro(i,t,a,e,c,n,p) {}
    double calcularDesconto(double precoBase) override {
        return precoBase * 0.90; // 10% desconto
    }
};

class CapaDura : public Livro {
public:
    CapaDura(string i, string t, string a, string e, string c, int n, double p)
        : Livro(i,t,a,e,c,n,p) {}
    double calcularDesconto(double precoBase) override {
        return precoBase; // sem desconto
    }
};

// Pedido

class Pedido {
public:
    string id;
    map<Livro*, int> itens; // livro -> quantidade
    string status;
    double valorTotal;

    Pedido(string i="") : id(i), status("em processamento"), valorTotal(0) {}
};

class CarrinhoCompras {
public:
    map<Livro*, int> itens;

    void adicionarLivro(Livro* livro, int quantidade) {
        itens[livro] += quantidade;
    }

    void removerLivro(Livro* livro) {
        itens.erase(livro);
    }

    double calcularTotal() {
        double total = 0;
        for (auto& item : itens) {
            Livro* livro = item.first;
            int qtd = item.second;
            total += livro->calcularDesconto(livro->getPreco()) * qtd;
        }
        return total;
    }
};

// Estoque

class Estoque : public NotificacaoPublicador {
public:
    map<Livro*, int> livros;

    void adicionarLivro(Livro* livro, int qtd) {
        livros[livro] += qtd;
    }

    void removerLivro(Livro* livro, int qtd) {
        if (livros.count(livro) && livros[livro] >= qtd) {
            livros[livro] -= qtd;
            if (livros[livro] <= 2) { // RN03: estoque mínimo
                avisarAssinantes("Estoque baixo para livro: " + livro->getTitulo());
            }
        }
    }

    int consultarEstoque(Livro* livro) {
        return livros.count(livro) ? livros[livro] : 0;
    }
};

// Pagamento via Cartão
class PagamentoCartao : public Pagavel {
public:
    bool parcelado;
    int parcelas;

    PagamentoCartao(bool p = false, int parc = 1) : parcelado(p), parcelas(parc) {}

    void realizarPagamento(double valor) override {
        if (!parcelado && parcelas == 1) {
            double valorComDesconto = valor * 0.97; // 3% desconto à vista
            cout << "Pagamento via Cartao à vista: R$ " << valorComDesconto << endl;
        } else {
            cout << "Pagamento via Cartao parcelado (" << parcelas << "x): R$ " << valor << endl;
        }
    }
};

// Pagamento via Pix
class PagamentoPix : public Pagavel {
public:
    void realizarPagamento(double valor) override {
        double valorComDesconto = valor * 0.92; // 8% desconto
        cout << "Pagamento via Pix à vista: R$ " << valorComDesconto << endl;
    }
};

// Operações Extras do Pedido

class PedidoAvancado : public Pedido {
public:
    PedidoAvancado(string i="") : Pedido(i) {}

    void atualizarStatus(string novoStatus) {
        status = novoStatus;
        cout << "Status do pedido " << id << " atualizado para: " << status << endl;
    }

    void exibirItens() {
        cout << "Itens do pedido " << id << ":\n";
        for (auto& item : itens) {
            cout << " - " << item.first->getTitulo() << " x" << item.second << endl;
        }
        cout << "Total: R$ " << valorTotal << endl;
    }
};

// Operações Extras do Carrinho

class CarrinhoAvancado : public CarrinhoCompras {
public:
    void atualizarQuantidade(Livro* livro, int novaQtd) {
        if (itens.count(livro)) itens[livro] = novaQtd;
    }

    void limparCarrinho() {
        itens.clear();
        cout << "Carrinho limpo." << endl;
    }

    void exibirCarrinho() {
        cout << "Itens no carrinho:\n";
        for (auto& item : itens) {
            cout << " - " << item.first->getTitulo() << " x" << item.second
                 << " (Subtotal: R$ " << item.first->calcularDesconto(item.first->getPreco()) * item.second << ")\n";
        }
        cout << "Total: R$ " << calcularTotal() << endl;
    }
};



// Pesquisa Livro Controller

class C_PesquisaLivro {
public:
    vector<Livro*> catalogo;

    void cadastrarLivro(Livro* livro) {
        catalogo.push_back(livro);
    }

    vector<Livro*> pesquisarLivro(string tituloOuAutor) {
        vector<Livro*> resultado;
        for (auto livro : catalogo) {
            if (livro->getTitulo() == tituloOuAutor) { // simples: busca por título
                resultado.push_back(livro);
            }
        }
        return resultado;
    }
};

// Manter Cliente Controller

class C_ManterCliente {
public:
    vector<Cliente*> clientes;

    Cliente* autenticarCliente(string cpf) {
        for (auto c : clientes) {
            if (c->cpf == cpf) return c;
        }
        return nullptr;
    }

    Cliente* cadastrarCliente(string nome, string email, string cpf, string telefone) {
        Cliente* novo = new Cliente(nome, email, cpf, telefone);
        clientes.push_back(novo);
        return novo;
    }

    void atualizarDadosCliente(Cliente* cliente, string novoEmail) {
        cliente->email = novoEmail;
    }
};

// Efetuar Pedido Controller

class C_EfetuarPedido {
public:
    Estoque* estoque;

    C_EfetuarPedido(Estoque* e) : estoque(e) {}

    Pedido* confirmarPedido(Cliente* cliente, CarrinhoCompras& carrinho) {
        Pedido* pedido = new Pedido("PED123");
        pedido->itens = carrinho.itens;
        pedido->valorTotal = carrinho.calcularTotal();
        pedido->status = "pagamento pendente";
        cliente->pedidos.push_back(pedido->id);

        for (auto& item : pedido->itens) {
            estoque->removerLivro(item.first, item.second);
        }

        return pedido;
    }
};

// Controle de Pedido com Pagamento

class C_EfetuarPedidoAvancado : public C_EfetuarPedido {
public:
    C_EfetuarPedidoAvancado(Estoque* e) : C_EfetuarPedido(e) {}

    PedidoAvancado* confirmarPedidoComPagamento(Cliente* cliente, CarrinhoCompras& carrinho, Pagavel* pagamento) {
        PedidoAvancado* pedido = new PedidoAvancado("PED456");
        pedido->itens = carrinho.itens;
        pedido->valorTotal = carrinho.calcularTotal();

        for (auto& item : pedido->itens) {
            estoque->removerLivro(item.first, item.second);
        }

        pagamento->realizarPagamento(pedido->valorTotal);

        pedido->atualizarStatus("confirmado");

        cliente->pedidos.push_back(pedido->id);
        return pedido;
    }
};

// Operações do Cliente

class C_OperacoesCliente {
public:
    void exibirHistorico(Cliente* cliente) {
        cout << "Histórico de pedidos do cliente " << cliente->nome << ":\n";
        for (auto& p : cliente->pedidos) {
            cout << " - Pedido ID: " << p << endl;
        }
    }

    void atualizarContato(Cliente* cliente, string novoTelefone, string novoEmail) {
        cliente->telefone = novoTelefone;
        cliente->email = novoEmail;
        cout << "Dados de contato atualizados para " << cliente->nome << endl;
    }
};

// Teste sem uso de Pagamento

int main0() {
    Estoque estoque;

    // Funcionário (para receber notificações de estoque)
    Funcionario f1("Carlos", "carlos@livraria.com", "111.222.333-44", "11999999999", "MAT123");
    estoque.adicionarAssinante(&f1);

    Livro* l1 = new Brochura("123", "C++ Básico", "Autor X", "Editora Y", "Programação", 200, 100);
    Livro* l2 = new Digital("456", "POO Avançada", "Autor Y", "Editora Z", "Engenharia", 300, 150);

    estoque.adicionarLivro(l1, 3);
    estoque.adicionarLivro(l2, 5);

    C_PesquisaLivro pesquisa;
    pesquisa.cadastrarLivro(l1);
    pesquisa.cadastrarLivro(l2);

    // Pesquisa
    auto resultado = pesquisa.pesquisarLivro("C++ Básico");
    cout << "Resultado da pesquisa: " << resultado.size() << " livro(s)\n";

    C_ManterCliente manterCliente;
    Cliente* cli = manterCliente.cadastrarCliente("João", "joao@email.com", "123.456.789-00", "11988887777");

    CarrinhoCompras carrinho;
    carrinho.adicionarLivro(l1, 2); // 2 exemplares de C++ Básico
    carrinho.adicionarLivro(l2, 1); // 1 exemplar de POO Avançada

    cout << "Total do carrinho (com descontos de formato): R$ " << carrinho.calcularTotal() << endl;

    C_EfetuarPedido efetuar(&estoque);
    Pedido* pedido = efetuar.confirmarPedido(cli, carrinho);

    cout << "Pedido confirmado: " << pedido->id << " | Status: " << pedido->status << endl;

    // Testar RN03: estoque baixo -> notificação
    estoque.removerLivro(l1, 1); // agora estoque de l1 ficará <= 2 → notificação

    return 0;
}

// Teste com uso de Pagamento

int main() {
    Estoque estoque;
    Funcionario f1("Carlos", "carlos@livraria.com", "111.222.333-44", "11999999999", "MAT123");
    estoque.adicionarAssinante(&f1);

    Livro* l1 = new Brochura("123", "C++ Básico", "Autor X", "Editora Y", "Programação", 200, 100);
    Livro* l2 = new Digital("456", "POO Avançada", "Autor Y", "Editora Z", "Engenharia", 300, 150);
    estoque.adicionarLivro(l1, 3);
    estoque.adicionarLivro(l2, 5);

    C_ManterCliente manterCliente;
    Cliente* cli = manterCliente.cadastrarCliente("João", "joao@email.com", "123.456.789-00", "11988887777");

    CarrinhoAvancado carrinho;
    carrinho.adicionarLivro(l1, 1);
    carrinho.adicionarLivro(l2, 2);
    carrinho.exibirCarrinho();

    C_EfetuarPedidoAvancado efetuar(&estoque);
    PagamentoPix pagamentoPix;
    PedidoAvancado* pedido = efetuar.confirmarPedidoComPagamento(cli, carrinho, &pagamentoPix);

    pedido->exibirItens();

    pedido->atualizarStatus("em transporte");
    pedido->atualizarStatus("finalizado");

    C_OperacoesCliente opCliente;
    opCliente.exibirHistorico(cli);

    return 0;
}
