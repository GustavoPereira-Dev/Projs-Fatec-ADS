package model.estrutura.lista;

public class ListaEncadeadaSimples<T> {
    private No<T> inicio;

    public ListaEncadeadaSimples() {
        this.inicio = null;
    }

    public void append(T elemento) {
        No<T> novoNo = new No<>(elemento);
        if (inicio == null) {
            inicio = novoNo;
        } else {
            No<T> atual = inicio;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNo);
        }
    }

    public int total() {
        int count = 0;
        No<T> atual = inicio;
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }

    public No<T> get(int index) {
        int count = 0;
        No<T> atual = inicio;
        while (atual != null) {
            if (count == index) {
                return atual;
            }
            count++;
            atual = atual.getProximo();
        }
        return null;
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        No<T> atual = inicio;
        while (atual != null) {
            builder.append(atual.getValor()).append(" ");
            atual = atual.getProximo();
        }
        return builder.toString().trim();
    }
}
