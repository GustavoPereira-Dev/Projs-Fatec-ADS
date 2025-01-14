package lista2.model.lista;

import java.lang.Exception;
import lista2.model.lista.No;

public class ListaDupla<T extends Comparable>{
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
	
	public No<T> get(int index) throws IllegalArgumentException {
	    if (index < 0 || index >= total) {
	        throw new IllegalArgumentException("O índice informado não existe");
	    }

	    No<T> buffer = this.inicio;
	    for (int i = 0; i < index; i++) {
	        buffer = buffer.getProximo();
	    }
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
					this.inicio.setProximo(null);
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
	
	// Exercicio 2 pt 1
	public void mesclar(ListaDupla<T> l1, ListaDupla<T> l2) {
	    if (l1.inicio == null && l2.inicio == null) {
	        this.inicio = null;
	        this.fim = null;
	        return;
	    } else if (l1.inicio == null) {
	        this.inicio = l2.inicio;
	        this.fim = l2.fim;
	    } else if (l2.inicio == null) {
	        this.inicio = l1.inicio;
	        this.fim = l1.fim;
	    } else {
	        l1.fim.setProximo(l2.inicio);
	        l2.inicio.setAnterior(l1.fim);
	        
	        this.inicio = l1.inicio;
	        this.fim = l2.fim;
	    }
	    total = l1.total + l2.total;
	}

	
	// Exercicio 2 pt 2
	public void ordenar() {
        boolean trocado;
        for (int i = 0; i < total - 1; i++) {
            trocado = false;
            for (int j = 0; j < total - i - 1; j++) {
                No<T> noAtual = this.get(j);
                No<T> noProximo = noAtual.getProximo();
                if (noAtual.getValor().compareTo(noProximo.getValor()) > 0) {
                    T temp = noAtual.getValor();
                    noAtual.setValor(noProximo.getValor());
                    noProximo.setValor(temp);
                    trocado = true;
                }
            }
            if (!trocado)
                break;
        }
    }
	
	// Exercicio 3
	public String doisMaiores() {
	    if (this.inicio == null) {
	        return "A lista está vazia.";
	    }
	    
	    No<T> buffer = this.inicio;
	    int segundoMaior = Integer.MIN_VALUE;
	    int primeiroMaior = Integer.MIN_VALUE;
	    
	    while (buffer != null) {
	        int valor = (int) buffer.getValor();
	        
	        if (valor > primeiroMaior) {
	            segundoMaior = primeiroMaior;
	            primeiroMaior = valor;
	        } else if (valor > segundoMaior && valor < primeiroMaior) {
	            segundoMaior = valor;
	        }
	        
	        buffer = buffer.getProximo();
	    }
	    
	    return "Primeiro número maior: " + primeiroMaior + "; " + "Segundo número maior: " + segundoMaior;
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