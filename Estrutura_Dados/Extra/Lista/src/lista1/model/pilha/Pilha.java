package lista1.model.pilha;



// Pilha, estrutura de dados auxiliar para reverter uma lista

public class Pilha<T> {
    private No<T> ultimo = null;

    public void push(T elemento){
        No<T> novo = new No<>(elemento);
        if(this.ultimo != null){
            No<T> anterior = this.ultimo;
            novo.setAnterior(anterior);
        }
        this.ultimo = novo;
    }

    public No<T> pop() throws IllegalArgumentException{
        if(this.ultimo == null)
            throw new IllegalArgumentException("A pilha está vazia!");
        No<T> elemento = this.ultimo;
        this.ultimo = elemento.getAnterior();
        return elemento;
    }

    public boolean isEmpty() {
        return this.ultimo == null;
    }

    @Override
    public String toString(){
        if(this.ultimo == null) return "[]";

        StringBuilder builder = new StringBuilder("[");
        No<T> buffer = this.ultimo;
        builder.append(buffer.getValor());
        while(buffer.getAnterior() != null){
            builder.append(",");
            buffer = buffer.getAnterior();
            builder.append(buffer.getValor());
        }
        builder.append("]");
        return builder.toString();
    }
}
