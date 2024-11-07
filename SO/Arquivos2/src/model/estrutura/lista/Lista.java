package model.estrutura.lista;

public class Lista<T>{
	private No<String> inicio = null;
	public No<String> fim = null;
	private int total = 0;
	
	// append	Adicionar elemento
	// get		Retornar um no dado no index
	// index	Retornar índice
	// insert	Inserir elemento
	// last		Último elemento
	// prepend  Inserir como primeiro elemento
	// remove	Remover
	// total	Total de elementos
	
	public void append(String elemento){
		try{
			No<String> buffer = new No<>(elemento);
			if(this.fim == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else{
				No <String> ex_ultimo = this.fim;
				ex_ultimo.setProximo(buffer);
				buffer.setAnterior(ex_ultimo);
				this.fim = buffer;
			}
			total++;
		} catch(Exception e){
			throw e;
		}
	}
	
	public No<String> get(int index) throws IllegalArgumentException{
		int i = 0;
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		No<String> buffer = this.inicio;
		for(i = 0; i < index; i++){
			if(buffer.getProximo() == null)
				break;
			buffer = buffer.getProximo();
		}
		
		if(i < index)
			throw new IllegalArgumentException("O indice informado nao existe");
		return buffer;
	}
	
	public int index(String elemento) throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		int index = 0;
		if(this.inicio.getValor() == elemento)
			return index;
		
		No<String> buffer = this.inicio;
		do{
			if(buffer.getValor() == elemento)
				return index;
				
			buffer = buffer.getProximo();
			index++;
			
		} while(buffer != null);
		throw new IllegalArgumentException("Item não encontrado.");
		
	}
	
	public void insert(int index, String elemento) 
	throws IllegalArgumentException{
		try{
			if(index == 0){
			No<String> buffer_novo = new No<>(elemento);
				if(this.inicio == null){
					this.inicio = buffer_novo;
					this.fim = buffer_novo;
				} else{
					No<String> ex_inicio = this.inicio;
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
	
	public void insert(No<String> item, String elemento) throws IllegalArgumentException{
		No<String> buffer_novo = new No<>(elemento);
		No<String> buffer_proximo = item.getProximo();
		item.setProximo(buffer_novo);
		buffer_novo.setProximo(buffer_proximo);
		buffer_proximo.setAnterior(buffer_novo);
		buffer_novo.setAnterior(item);
	}
	
	public void prepend(String elemento){
		try{
			No<String> buffer = new No<>(elemento);
			if(this.fim == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else{
				No<String> ex_primeiro = this.inicio;
				this.inicio = buffer;
				buffer.setProximo(ex_primeiro);
				ex_primeiro.setAnterior(buffer);
			}
			
			total++;
		} catch(Exception e){
			throw e;
		}
	
	}

	public No<String> last() throws IllegalArgumentException{
		if(this.inicio == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		No<String> buffer = this.inicio;
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
					No<String> buffer = this.inicio.getProximo();
					this.inicio.setProximo(null);
					this.inicio = buffer;
				}
			} else {
				No<String> buffer_anterior = this.get(--index);
				No<String> item = buffer_anterior.getProximo();
				No<String> buffer_proximo = item.getProximo();
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
		No<String> buffer = this.inicio;
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