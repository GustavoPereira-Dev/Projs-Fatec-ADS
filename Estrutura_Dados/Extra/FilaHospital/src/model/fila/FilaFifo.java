package model.fila;

import model.fila.No;
import java.util.Comparator;

public class FilaFifo<T> {
    private No<T> inicio = null;
    private No<T> ultimo = null;
    private Comparator<T> comparator;

    public FilaFifo(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void enqueue(T elemento){
        No<T> novoNo = new No<>(elemento);
        if (this.inicio == null) {
            this.inicio = novoNo;
            this.ultimo = novoNo;
        } else {
            if (comparator.compare(elemento, this.inicio.getValor()) < 0) {
                novoNo.setProximo(this.inicio);
                this.inicio = novoNo;
            } else {
                No<T> atual = this.inicio;
                while (atual.getProximo() != null && comparator.compare(elemento, atual.getProximo().getValor()) >= 0) {
                    atual = atual.getProximo();
                }
                novoNo.setProximo(atual.getProximo());
                atual.setProximo(novoNo);
                if (novoNo.getProximo() == null) {
                    this.ultimo = novoNo;
                }
            }
        }
    }

    public No<T> dequeue(){
        if(this.inicio == null){
            return null;
        }
        No<T> primeiro = this.inicio;
        this.inicio = primeiro.getProximo();
        return primeiro;
    }

    @Override
    public String toString(){
        if(this.inicio == null) return "[]";

        StringBuilder builder = new StringBuilder("[");
        No<T> buffer = this.inicio;
        builder.append(buffer.getValor());
        while(buffer.getProximo() != null){
            builder.append(",");
            buffer = buffer.getProximo();
            builder.append(buffer.getValor());
        }
        builder.append("]");
        return builder.toString();
    }
}

