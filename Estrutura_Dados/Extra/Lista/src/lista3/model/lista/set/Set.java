package lista3.model.lista.set;

import java.lang.Exception;

// Exercicio 4

public class Set<T> {
    private No<T> inicio = null;
    private int total = 0;

    // Método para adicionar elemento ao Set
    public void adicionar(T valor) {
        if (contains(valor)) {
            return;
        }
        
        No<T> novoNo = new No<>(valor);
        if (inicio == null) {
            inicio = novoNo;
        } else {
            No<T> atual = inicio;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novoNo);
            novoNo.setAnterior(atual);
        }
        total++;
    }

    // Método para verificar se o Set contém um elemento
    public boolean contains(T valor) {
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.getValor().equals(valor)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    // Método para remover um elemento do Set
    public void remover(T valor) {
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.getValor().equals(valor)) {
                No<T> anterior = atual.getAnterior();
                No<T> proximo = atual.getProximo();
                if (anterior != null) {
                    anterior.setProximo(proximo);
                } else {
                    inicio = proximo;
                }
                if (proximo != null) {
                    proximo.setAnterior(anterior);
                }
                total--;
                return;
            }
            atual = atual.getProximo();
        }
    }

    // Método para obter o tamanho do Set
    public int tamanho() {
        return total;
    }

    // Método toString para exibir o Set
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        No<T> atual = inicio;
        while (atual != null) {
            sb.append(atual.getValor());
            atual = atual.getProximo();
            if (atual != null) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
