package model.estrutura.pilha;

public class Pilha<T>{
	public No<String> ultimo = null;

	// push: Insere um elemento em cima da pilha
	// pop: Remove o elemento que esta sobre a pilha

	public void push(String elemento){
		No<String> novo = new No<>(elemento);
		if(this.ultimo != null){
			No<String> anterior = this.ultimo;
			novo.setAnterior(anterior);
		}
		this.ultimo = novo;
	}

	public No<String> pop() throws IllegalArgumentException{
		if(this.ultimo == null)
			throw new IllegalArgumentException("A pilha está vazia!");
		No<String> elemento = this.ultimo;
		this.ultimo = elemento.getAnterior();
		return elemento;
	}

	@Override
	public String toString(){
		if(this.ultimo == null) return "[]";

		StringBuilder builder = new StringBuilder("[");
		No<String> buffer = this.ultimo;
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