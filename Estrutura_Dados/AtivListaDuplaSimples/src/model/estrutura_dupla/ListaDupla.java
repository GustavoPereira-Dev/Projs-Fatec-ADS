package model.estrutura_dupla;

import model.Pessoa;

public class ListaDupla {
    NoDuplo inicio;

    // Adiciona uma pessoa na lista dupla e na lista simples interna
    public void adicionarPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo atual = encontrarOuCriarNo(letraInicial);
        Pessoa novaPessoa = new Pessoa(nome);
        atual.listaPessoas.adicionarOrdenado(novaPessoa);
    }

    // Localiza e retorna uma pessoa
    public Pessoa localizarPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo no = encontrarNo(letraInicial);
        if (no != null) {
            return no.listaPessoas.localizar(new Pessoa(nome));
        }
        return null;
    }

    // Remove uma pessoa
    public void removerPessoa(String nome) {
        char letraInicial = nome.charAt(0);
        NoDuplo no = encontrarNo(letraInicial);
        if (no != null) {
            Pessoa pessoa = new Pessoa(nome);
            boolean listaVazia = no.listaPessoas.remover(pessoa);
            if (listaVazia) {
                removerNo(no);
            }
        }
    }

    // Método para listar os nomes de uma letra específica
    public String listarNomesPorLetra(char letra) {
        NoDuplo no = encontrarNo(letra);
        if (no != null) {
            return no.listaPessoas.listarNomes();
        } else {
            return "Nenhum nome encontrado para a letra " + letra;
        }
    }

    // Encontra o nó com a letra correspondente ou cria um novo
    private NoDuplo encontrarOuCriarNo(char letra) {
        NoDuplo atual = inicio;
        NoDuplo anterior = null;

        // Percorre a lista até encontrar a posição correta ou a letra correspondente
        while (atual != null && atual.letra < letra) {
            anterior = atual;
            atual = atual.proximo;
        }

        // Se o nó já existe, retorna
        if (atual != null && atual.letra == letra) {
            return atual;
        }

        // Cria um novo nó
        NoDuplo novoNo = new NoDuplo(letra);

        if (anterior == null) {
            // Inserção no início
            novoNo.proximo = inicio;
            if (inicio != null) {
                inicio.anterior = novoNo;
            }
            inicio = novoNo;
        } else {
            // Inserção no meio ou no fim
            novoNo.proximo = anterior.proximo;
            novoNo.anterior = anterior;
            anterior.proximo = novoNo;
            if (novoNo.proximo != null) {
                novoNo.proximo.anterior = novoNo;
            }
        }

        return novoNo;
    }

    // Encontra o nó pela letra
    private NoDuplo encontrarNo(char letra) {
        NoDuplo atual = inicio;
        while (atual != null) {
            if (atual.letra == letra) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // Remove um nó da lista duplamente encadeada
    private void removerNo(NoDuplo no) {
        if (no.anterior != null) {
            no.anterior.proximo = no.proximo;
        } else {
            inicio = no.proximo; // Caso seja o primeiro nó
        }

        if (no.proximo != null) {
            no.proximo.anterior = no.anterior;
        }
    }

    // Exibe a lista completa
    public void exibirLista() {
        NoDuplo atual = inicio;
        while (atual != null) {
            System.out.print(atual.letra + " -> ");
            atual.listaPessoas.exibir();
            atual = atual.proximo;
        }
    }
}