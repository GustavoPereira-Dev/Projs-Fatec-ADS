// Lista encadeada simples genérica
package model.estrutura_simples;

public class ListaSimples<T> {
    NoSimples<T> inicio;

    // Adiciona um elemento de forma ordenada (neste caso específico para String)
    public void adicionarOrdenado(T valor) {
        NoSimples<T> novoNo = new NoSimples<>(valor);

        if (inicio == null || compararStrings(valor, inicio.valor) < 0) {
            novoNo.proximo = inicio;
            inicio = novoNo;
        } else {
            NoSimples<T> atual = inicio;
            while (atual.proximo != null && compararStrings(atual.proximo.valor, valor) < 0) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
        }
    }

    // Remove um elemento e retorna se a lista está vazia
    public boolean remover(T valor) {
        if (inicio == null) return false;

        if (compararStrings(inicio.valor, valor) == 0) {
            inicio = inicio.proximo;
        } else {
            NoSimples<T> atual = inicio;
            while (atual.proximo != null && compararStrings(atual.proximo.valor, valor) != 0) {
                atual = atual.proximo;
            }
            if (atual.proximo != null) {
                atual.proximo = atual.proximo.proximo;
            }
        }
        return inicio == null;
    }

    // Localiza um elemento
    public T localizar(T valor) {
        NoSimples<T> atual = inicio;
        while (atual != null) {
            if (compararStrings(atual.valor, valor) == 0) {
                return atual.valor;
            }
            atual = atual.proximo;
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
            listaNomes.append(atual.valor.toString()).append(" ");
            atual = atual.proximo;
        }
        return listaNomes.toString().trim(); // Remove espaços no final
    }

    // Exibe os elementos da lista
    public void exibir() {
        NoSimples<T> atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
