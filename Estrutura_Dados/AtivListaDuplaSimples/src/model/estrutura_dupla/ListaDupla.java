package model.estrutura_dupla;

import model.Pessoa;

public class ListaDupla {
    private NoDuplo inicio;

    // Adiciona uma pessoa na lista dupla e na lista simples interna
    public void adicionarPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo atual = encontrarOuCriarNo(letraInicial);
        Pessoa novaPessoa = new Pessoa(nome);
        atual.getListaPessoas().adicionarOrdenado(novaPessoa);
    }

    // Localiza e retorna uma pessoa
    public Pessoa localizarPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo no = encontrarNo(letraInicial);
        if (no != null) {
            return no.getListaPessoas().localizar(new Pessoa(nome));
        }
        return null;
    }

    // Remove uma pessoa
    public void removerPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo no = encontrarNo(letraInicial);
        if (no != null) {
            Pessoa pessoa = new Pessoa(nome);
            boolean listaVazia = no.getListaPessoas().remover(pessoa);
            if (listaVazia) {
                removerNo(no);
            }
        }
    }

    // Método para listar os nomes de uma letra específica
    public String listarNomesPorLetra(char letra) {
        NoDuplo no = encontrarNo(letra);
        if (no != null) {
            return no.getListaPessoas().listarNomes();
        } else {
            return "Nenhum nome encontrado para a letra " + letra;
        }
    }

    // Encontra o nó com a letra correspondente ou cria um novo
    private NoDuplo encontrarOuCriarNo(char letra) {
        NoDuplo atual = inicio;
        NoDuplo anterior = null;

        // Percorre a lista até encontrar a posição correta ou a letra correspondente
        while (atual != null && atual.getLetra() < letra) {
            anterior = atual;
            atual = atual.getProximo();
        }

        // Se o nó já existe, retorna
        if (atual != null && atual.getLetra() == letra) {
            return atual;
        }

        // Cria um novo nó
        NoDuplo novoNo = new NoDuplo(letra);

        if (anterior == null) {
            // Inserção no início
            novoNo.setProximo(inicio);
            if (inicio != null) {
                inicio.setAnterior(novoNo);
            }
            inicio = novoNo;
        } else {
            // Inserção no meio ou no fim
            novoNo.setProximo(anterior.getProximo());
            novoNo.setAnterior(anterior);
            anterior.setProximo(novoNo);
            if (novoNo.getProximo() != null) {
                (novoNo.getProximo()).setAnterior(novoNo);
            }
        }

        return novoNo;
    }

    // Encontra o nó pela letra
    private NoDuplo encontrarNo(char letra) {
        NoDuplo atual = inicio;
        while (atual != null) {
            if (atual.getLetra() == letra) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Remove um nó da lista duplamente encadeada
    private void removerNo(NoDuplo no) {
        if (no.getAnterior() != null) {
        	no.getAnterior().setProximo(no.getProximo());
        } else {
            inicio = no.getProximo(); // Caso seja o primeiro nó
        }

        if (no.getProximo() != null) {
            no.getProximo().setAnterior(no.getAnterior());
        }
    }

    // Exibe a lista completa
    public void exibirLista() {
        NoDuplo atual = inicio;
        while (atual != null) {
            System.out.print(atual.getLetra() + " -> ");
            atual.getListaPessoas().exibir();
            atual = atual.getProximo();
        }
    }

	public NoDuplo getInicio() {
		return inicio;
	}

	public void setInicio(NoDuplo inicio) {
		this.inicio = inicio;
	}
    
    
}