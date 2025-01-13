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
	
	public void mesclar(ListaDupla<T> l1, ListaDupla<T> l2) {
		No<T> lista1 = l1.fim;
		No<T> lista2 = l2.inicio;
		No<T> inicioLista1 = lista1;
		No<T> fimLista2 = lista2;
	
		lista1.setProximo(lista2);
		lista2.setAnterior(lista1);
		
		while(inicioLista1.getAnterior() != null) {
			inicioLista1 = inicioLista1.getAnterior();
		}
		
		while(fimLista2.getProximo() != null) {
			fimLista2 = fimLista2.getProximo();
		}
		
		fimLista2.setProximo(inicioLista1);
		inicioLista1.setAnterior(fimLista2);
		
		this.inicio = inicioLista1;
		this.fim = fimLista2;
		total = l1.total + l2.total;
	}
	
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
	
	public String doisMaiores() {
		No<T> buffer = this.inicio;
		int segundoMaior = 0;
		int primeiroMaior = 0;
		boolean primeiroNumero = true;
		
		while(buffer.getValor() == null) {
			
			if(primeiroNumero) {
				primeiroMaior = (int) buffer.getValor();
				segundoMaior = (int) buffer.getValor();
			} else if(buffer.getValor().compareTo(primeiroMaior) > 0) {
				segundoMaior = primeiroMaior;
				primeiroMaior = (int) buffer.getValor();
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