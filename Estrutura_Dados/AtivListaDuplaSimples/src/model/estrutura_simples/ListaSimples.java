// Lista encadeada simples genérica
package model.estrutura_simples;

public class ListaSimples<T> {
    private NoSimples<T> inicio;

    // Adiciona um elemento de forma ordenada (neste caso específico para String)
    public void adicionarOrdenado(T valor) {
        NoSimples<T> novoNo = new NoSimples<>(valor);

        if (inicio == null || compararStrings(valor, inicio.getValor()) < 0) {
            novoNo.setProximo(inicio);
            inicio = novoNo;
        } else {
            NoSimples<T> atual = inicio;
            while (atual.getProximo() != null && compararStrings(atual.getProximo().getValor(), valor) < 0) {
                atual = atual.getProximo();
            }
            novoNo.setProximo(atual.getProximo());
            atual.setProximo(novoNo);
        }
    }

    // Remove um elemento e retorna se a lista está vazia
    public boolean remover(T valor) {
        if (inicio == null) return false;

        if (compararStrings(inicio.getValor(), valor) == 0) {
            inicio = inicio.getProximo();
        } else {
            NoSimples<T> atual = inicio;
            while (atual.getProximo() != null && compararStrings(atual.getProximo().getValor(), valor) != 0) {
                atual = atual.getProximo();
            }
            if (atual.getProximo() != null) {
                atual.setProximo(atual.getProximo().getProximo());
            }
        }
        return inicio == null;
    }

    // Localiza um elemento
    public T localizar(T valor) {
        NoSimples<T> atual = inicio;
        while (atual != null) {
            if (compararStrings(atual.getValor(), valor) == 0) {
                return atual.getValor();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    // Método de comparação específico para strings (sem usar Comparable)
    private int compararStrings(T valor1, T valor2) {
        String str1 = valor1.toString();
        String str2 = valor2.toString();
        return str1.compareTo(str2);
    }

    // Retorna todos os elementos da lista como uma string concatenada
    public String listarNomes() {
        StringBuilder listaNomes = new StringBuilder();
        NoSimples<T> atual = inicio;
        while (atual != null) {
            listaNomes.append(atual.getValor().toString()).append(" ");
            atual = atual.getProximo();
        }
        return listaNomes.toString().trim(); // Remove espaços no final
    }

    // Exibe os elementos da lista
    public void exibir() {
        NoSimples<T> atual = inicio;
        while (atual != null) {
            System.out.print(atual.getValor() + " ");
            atual = atual.getProximo();
        }
        System.out.println();
    }
}
