package model.lista;


import model.Arquivo;

public class Lista<T extends Comparable<T>>{
	public No<Arquivo> inicio = null;
	
	// append	Adicionar elemento
	// get		Retornar um no dado no index
	// index	Retornar índice
	// insert	Inserir elemento
	// last		Último elemento
	// remove	Remover
	// total	Total de elementos
	
	private No<Arquivo> fim = null;
	public void append(Arquivo elemento){
		No<Arquivo> buffer = new No<>(elemento);
		if(this.inicio == null){
			this.inicio = buffer;
			this.fim = buffer;
		} else{
			this.fim.setProximo(buffer);
			this.fim = buffer;
		}
	}
	
	public No<Arquivo> get(int index) throws IllegalArgumentException{
		int i = 0;
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		No<Arquivo> buffer = this.inicio;
		for(i = 0; i < index; i++){
			if(buffer.getProximo() == null)
				break;
			buffer = buffer.getProximo();
		}
		
		if(i < index)
			throw new IllegalArgumentException("O indice informado nao existe");
		return buffer;
	}
	
	public int index(Arquivo elemento) throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		int index = 0;
		if(this.inicio.getValor() == elemento)
			return index;
		
		No<Arquivo> buffer = this.inicio;
		do{
			if(buffer.getValor() == elemento)
				return index;
				
			buffer = buffer.getProximo();
			index++;
			
		} while(buffer != null);
		throw new IllegalArgumentException("Item não encontrado.");
		
	}
	
	public void insert(int index, Arquivo elemento) throws IllegalArgumentException{
		if(index == 0){
			No<Arquivo> buffer_novo = new No<>(elemento);
			if(this.inicio == null){
				this.inicio = buffer_novo;
			} else{
				No<Arquivo> ex_inicio = this.inicio;
				buffer_novo.setProximo(ex_inicio);
				this.inicio = buffer_novo;
			}
		} else{
			this.insert(this.get(--index), elemento);
		}
	}
	
	public void insert(No<Arquivo> item, Arquivo elemento) throws IllegalArgumentException{
		No<Arquivo> buffer_novo = new No<>(elemento);
		No<Arquivo> buffer_proximo = item.getProximo();
		item.setProximo(buffer_novo);
		buffer_novo.setProximo(buffer_proximo);
	}

	public No<Arquivo> last() throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		No<Arquivo> buffer = this.inicio;
		while(buffer.getProximo() != null)
			buffer = buffer.getProximo();
		return buffer;
	}
	
	public void remove(int index){
		if(index == 0){
			this.inicio.setValor(null);
			if(this.inicio.getProximo() == null){
				this.inicio = null;
			} else{
				No<Arquivo> buffer = this.inicio.getProximo();
				this.inicio.setProximo(null);
				this.inicio = buffer;
			}
			return;
		}
		No<Arquivo> buffer_anterior = this.get(--index);
		No<Arquivo> item = buffer_anterior.getProximo();
		No<Arquivo> buffer_proximo = item.getProximo();
		buffer_anterior.setProximo(buffer_proximo);
		item.setProximo(null);
		item.setValor(null);

	}
		
	public int total(){
		if(this.inicio == null)
			return 0;
		No<Arquivo> buffer = this.inicio;
		int total_elementos = 0;
			
		do{
			total_elementos++;
			buffer = buffer.getProximo();
		} while(buffer != null);
		return total_elementos;
	}
		
	@Override
	public String toString(){
		if(this.inicio == null){
			return "[]";
		}
			
		StringBuilder builder = new StringBuilder("[");
		No<Arquivo> buffer = this.inicio;
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