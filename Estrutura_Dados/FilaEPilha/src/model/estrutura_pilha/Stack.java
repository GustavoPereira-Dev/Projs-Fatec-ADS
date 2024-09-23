package model.estrutura_pilha;

import java.lang.Exception;
import model.estrutura_pilha.No;

public class Stack<T>{
	private No<T> ultimo = null;

	// push: Insere um elemento em cima da pilha
	// pop: Remove o elemento que esta sobre a pilha

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