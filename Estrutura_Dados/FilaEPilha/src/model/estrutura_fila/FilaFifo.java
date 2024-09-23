package model.estrutura_fila;

import java.lang.Exception;
import model.estrutura_fila.No;

public class FilaFifo<T>{
	private No<T> inicio = null;
	private No<T> ultimo = null;

	public void enqueue(T elemento){
		No<T> buffer = new No<>(elemento);
		if(this.inicio == null){
			this.inicio = buffer;
			this.ultimo = buffer;
		} else{
			this.ultimo.setProximo(buffer);
			this.ultimo = buffer;
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
