package model.pilha;

import java.util.EmptyStackException;

public class Stack<T> {
    private No<T> ultimo = null;

    public void push(T elemento) {
        No<T> novo = new No<>(elemento);
        if (this.ultimo != null) {
            No<T> anterior = this.ultimo;
            novo.setAnterior(anterior);
        }
        this.ultimo = novo;
    }

    public No<T> pop() throws EmptyStackException {
        if (this.ultimo == null)
            throw new EmptyStackException();
        No<T> elemento = this.ultimo;
        this.ultimo = elemento.getAnterior();
        return elemento;
    }

    @Override
    public String toString() {
        if (this.ultimo == null) return "[]";

        StringBuilder builder = new StringBuilder("[");
        No<T> buffer = this.ultimo;
        builder.append(buffer.getValor());
        while (buffer.getAnterior() != null) {
            builder.append(",");
            buffer = buffer.getAnterior();
            builder.append(buffer.getValor());
        }
        builder.append("]");
        return builder.toString();
    }
}

