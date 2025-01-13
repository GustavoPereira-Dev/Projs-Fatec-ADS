package lista3.model.lista.circular;


import java.lang.Exception;

public class ListaCircular<T extends Comparable>{
	private No<T> ultimo_elemento = null;
	public int total;
	// append	Adicionar elemento
	// getLast	Retorna um no, o último adicionado
	// remove	Remover
	// total	Retorna o total de elementos
	
	public void append(T elemento){
		No<T> novo = new No<>(elemento);
		try{
			if(this.ultimo_elemento == null){
				this.ultimo_elemento = novo;
				novo.setProximo(novo);
				novo.setAnterior(novo);
			} else{
				No<T> buffer_ultimo = this.ultimo_elemento;
				No<T> buffer_proximo = this.ultimo_elemento.getProximo();
				novo.setAnterior(buffer_ultimo);
				novo.setProximo(buffer_proximo);
				buffer_proximo.setAnterior(novo);
				buffer_ultimo.setProximo(novo);
				this.ultimo_elemento = novo;
			}
			total++;
		} catch(Exception e){
			throw e;
		}
	}
	
	public boolean hasElement(T elemento) throws IllegalArgumentException{
		No<T> buffer = ultimo_elemento.getProximo();
		
		while(buffer.getValor() != ultimo_elemento) {
			if(buffer.getValor() == elemento) return true;
			
			buffer = buffer.getProximo();
		}
		
		return false;
	}
	
	public boolean hasElement(int elemento) throws IllegalArgumentException{
		No<T> buffer = ultimo_elemento.getProximo();
		
		while(buffer.getValor() != ultimo_elemento) {
			if(buffer.getValor().compareTo(elemento) == 0) return true;
			
			buffer = buffer.getProximo();
		}
		
		return false;
	}
	public No<T> getLast() throws IllegalArgumentException{
		if(this.ultimo_elemento == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		return this.ultimo_elemento;
	}
	
	public No<T> get(int index) throws IllegalArgumentException{
		int i = 0;
		if(this.ultimo_elemento == null)
			throw new IllegalArgumentException("Não existe item na lista.");
		
		No<T> buffer = this.ultimo_elemento.getProximo();
		for(i = 0; i < index; i++){
			if(buffer.getValor() == this.ultimo_elemento)
				break;
			buffer = buffer.getProximo();
		}
		
		if(i < index)
			throw new IllegalArgumentException("O indice informado nao existe");
		return buffer;
	}
	
	
	public void remove(No<T> remover){
		try{
			No<T> anterior = remover.getAnterior();
			No<T> proximo = remover.getProximo();
			if(this.ultimo_elemento == remover && anterior == remover && proximo == remover) {
				// somente 1 elemento na lista
				this.ultimo_elemento = null;
				return;
			}
			anterior.setProximo(proximo);
			proximo.setAnterior(anterior);
			if(this.ultimo_elemento == remover){
				this.ultimo_elemento = anterior;
			}
			remover.setProximo(null);
			remover.setAnterior(null);
			remover.setValor(null);
		} catch(Exception e){
			throw e;
		}
		total--;
	}
	
	public void remove(int index) throws IllegalArgumentException {
        if (total == 0) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
        if (index < 0 || index >= total) {
            throw new IllegalArgumentException("Índice fora do limite da lista.");
        }

        No<T> remover = get(index);
        No<T> anterior = remover.getAnterior();
        No<T> proximo = remover.getProximo();

        if (anterior == remover && proximo == remover) {
            // apenas um elemento na lista
            this.ultimo_elemento = null;
        } else {
            anterior.setProximo(proximo);
            proximo.setAnterior(anterior);

            if (remover == this.ultimo_elemento) {
                this.ultimo_elemento = anterior;
            }
        }
        total--;
    }

	
	public void insert(int index, T elemento) throws IllegalArgumentException {
        if (index < 0 || index > total) {
            throw new IllegalArgumentException("Índice fora do limite da lista.");
        }

        No<T> novo = new No<>(elemento);
        if (index == 0) {
            if (this.ultimo_elemento == null) {
                this.ultimo_elemento = novo;
                novo.setProximo(novo);
                novo.setAnterior(novo);
            } else {
                No<T> primeiro = this.ultimo_elemento.getProximo();
                novo.setProximo(primeiro);
                novo.setAnterior(this.ultimo_elemento);
                primeiro.setAnterior(novo);
                this.ultimo_elemento.setProximo(novo);
            }
            total++;
        } else {
            No<T> atual = this.get(index - 1);
            No<T> proximo = atual.getProximo();
            novo.setProximo(proximo);
            novo.setAnterior(atual);
            atual.setProximo(novo);
            proximo.setAnterior(novo);
            if (index == total) {
                this.ultimo_elemento = novo;
            }
            total++;
        }
    }
	
	public void insert(int index, No<T> novo) throws IllegalArgumentException {
        if (index < 0 || index > total) {
            throw new IllegalArgumentException("Índice fora do limite da lista.");
        }

        if (index == 0) {
            if (this.ultimo_elemento == null) {
                this.ultimo_elemento = novo;
                novo.setProximo(novo);
                novo.setAnterior(novo);
            } else {
                No<T> primeiro = this.ultimo_elemento.getProximo();
                novo.setProximo(primeiro);
                novo.setAnterior(this.ultimo_elemento);
                primeiro.setAnterior(novo);
                this.ultimo_elemento.setProximo(novo);
            }
            total++;
        } else {
            No<T> atual = this.get(index - 1);
            No<T> proximo = atual.getProximo();
            novo.setProximo(proximo);
            novo.setAnterior(atual);
            atual.setProximo(novo);
            proximo.setAnterior(novo);
            if (index == total) {
                this.ultimo_elemento = novo;
            }
            total++;
        }
    }

	
	public void remove() throws IllegalArgumentException{
		remove(getLast());
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
	
	public void uniao(ListaCircular<T> l1,ListaCircular<T> l2) {
		No<T> lista1 = l1.ultimo_elemento.getProximo();
		No<T> lista2 = l2.ultimo_elemento.getProximo();
		
		ListaCircular<T> uniao = new ListaCircular<T>();
		uniao.append(lista1.getValor());
		
		int total = 0;
		int nLista;
		T elemento;
		
		for (int i = 0; i < 2; i++) {
			total = i == 0 ? l1.total : l2.total; 
			nLista = i == 0 ? 1 : 2; 
			
            for (int j = 0; j < total; j++) {
            	elemento = nLista == 1 ? lista1.getValor() : lista2.getValor();
            	
            	if(!uniao.hasElement(elemento)) {
            		uniao.append(elemento);
            	}
            	
            	if(nLista == 1) lista1 = lista1.getProximo();
            	else lista2 = lista2.getProximo();
            	
            }
        }
		
		this.ultimo_elemento = uniao.get(total).getProximo();

	}
	
	public void interseccao(ListaCircular<T> l1,ListaCircular<T> l2) {
		No<T> lista1 = l1.ultimo_elemento.getProximo();
		No<T> lista2 = l2.ultimo_elemento.getProximo();
		T elemento;
		
		ListaCircular<T> interseccao = new ListaCircular<T>();
		
		for(int i = 0; i < l1.total; i++) {
			
			for(int j = 0; j < l1.total; j++) {
				
				if(lista1.getValor() == lista2.getValor()) {
					elemento = lista1.getValor();
					if(!interseccao.hasElement(elemento)) interseccao.append(elemento);
				}
				lista2 = lista2.getProximo();
			}
			
			lista1 = lista1.getProximo();
			lista2 = lista2.getProximo();
		}
		
		this.ultimo_elemento = interseccao.get(total).getProximo();
	}
	
	@Override
	public String toString(){
		if(this.ultimo_elemento == null){
			return "[]";
		}
			
		StringBuilder builder = new StringBuilder("[");
		No<T> buffer = this.ultimo_elemento;
		builder.append(buffer.getValor());
		while(buffer.getProximo() != this.ultimo_elemento){
			builder.append(",");
			buffer = buffer.getProximo();
			builder.append(buffer.getValor());
		}
		builder.append("]");
		return builder.toString();
	}
		
}