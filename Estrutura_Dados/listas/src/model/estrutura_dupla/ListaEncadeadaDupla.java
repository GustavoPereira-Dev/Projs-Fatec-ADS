package model.estrutura_dupla;

import java.lang.Exception;
import model.estrutura_dupla.No;

public class ListaEncadeadaDupla<T>{
	private No<T> inicio = null;
	private No<T> fim = null;
	private int total = 0;
	
	// append	Adicionar elemento
	// get		Retornar um no dado no index
	// index	Retornar índice
	// insert	Inserir elemento
	// last		Último elemento
	// prepend  Inserir como primeiro elemento
	// remove	Remover
	// total	Total de elementos
	
	public void append(T elemento){
		try{
			No<T> buffer = new No<>(elemento);
			if(this.fim == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else{
				No <T> ex_ultimo = this.fim;
				ex_ultimo.setProximo(buffer);
				buffer.setAnterior(ex_ultimo);
				this.fim = buffer;
			}
			total++;
		} catch(Exception e){
			throw e;
		}
	}
	
	public No<T> get(int index) throws IllegalArgumentException{
		int i = 0;
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		No<T> buffer = this.inicio;
		for(i = 0; i < index; i++){
			if(buffer.getProximo() == null)
				break;
			buffer = buffer.getProximo();
		}
		
		if(i < index)
			throw new IllegalArgumentException("O indice informado nao existe");
		return buffer;
	}
	
	public int index(T elemento) throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		int index = 0;
		if(this.inicio.getValor() == elemento)
			return index;
		
		No<T> buffer = this.inicio;
		do{
			if(buffer.getValor() == elemento)
				return index;
				
			buffer = buffer.getProximo();
			index++;
			
		} while(buffer != null);
		throw new IllegalArgumentException("Item não encontrado.");
		
	}
	
	public void insert(int index, T elemento) 
	throws IllegalArgumentException{
		try{
			if(index == 0){
			No<T> buffer_novo = new No<>(elemento);
				if(this.inicio == null){
					this.inicio = buffer_novo;
					this.fim = buffer_novo;
				} else{
					No<T> ex_inicio = this.inicio;
					buffer_novo.setProximo(ex_inicio);
					this.inicio = buffer_novo;
					buffer_novo.setProximo(ex_inicio);
					ex_inicio.setAnterior(buffer_novo);
				}
			} else{
				this.insert(this.get(--index), elemento);
			}		
			total++;
		} catch(Exception e){
			throw e;
		}
		
	}
	
	public void insert(No<T> item, T elemento) throws IllegalArgumentException{
		No<T> buffer_novo = new No<>(elemento);
		No<T> buffer_proximo = item.getProximo();
		item.setProximo(buffer_novo);
		buffer_novo.setProximo(buffer_proximo);
		buffer_proximo.setAnterior(buffer_novo);
		buffer_novo.setAnterior(item);
	}
	
	public void prepend(T elemento){
		try{
			No <T> buffer = new No<>(elemento);
			if(this.fim == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else{
				No<T> ex_primeiro = this.inicio;
				this.inicio = buffer;
				buffer.setProximo(ex_primeiro);
				ex_primeiro.setAnterior(buffer);
			}
			
			total++;
		} catch(Exception e){
			throw e;
		}
	
	}

	public No<T> last() throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		No<T> buffer = this.inicio;
		while(buffer.getProximo() != null)
			buffer = buffer.getProximo();
		return buffer;
	}
	
	public void remove(int index){
		try{
			if(index == 0){
				this.inicio.setValor(null);
				if(this.inicio.getProximo() == null){
					this.inicio = null;
					this.fim = null;
				} else{
					No<T> buffer = this.inicio.getProximo();
					No<T> anterior = this.inicio.getAnterior();
					this.inicio.setProximo(null);
					this.inicio.setAnterior(null);
					this.inicio = buffer;
				}
			} else {
				No<T> buffer_anterior = this.get(--index);
				No<T> item = buffer_anterior.getProximo();
				No<T> buffer_proximo = item.getProximo();
				buffer_anterior.setProximo(buffer_proximo);
				item.setProximo(null);
				item.setValor(null);
			}
			total--;
		} catch(Exception e){
			throw e;
		}

	}
	
	public int getTotal(){
		return total;
	}
		
	@Override
	public String toString(){
		if(this.inicio == null){
			return "[]";
		}
			
		StringBuilder builder = new StringBuilder("[");
		No<T> buffer = this.inicio;
		builder.append(buffer.getValor());
		while(buffer.getProximo() != null){
			builder.append(", ");
			buffer = buffer.getProximo();
			builder.append(buffer.getValor());
		}
		builder.append("]");
		return builder.toString();
	}
		
}