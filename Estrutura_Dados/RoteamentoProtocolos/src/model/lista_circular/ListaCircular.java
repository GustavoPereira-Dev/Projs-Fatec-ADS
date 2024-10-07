package model.lista_circular;


public class ListaCircular<T> {
	private No<T> ultimo_elemento = null;
	
	public void append(String type, String dados) {
		No<T> buffer = new No<>(type,dados);
		
		if(this.ultimo_elemento == null) {
			this.ultimo_elemento = buffer; 
			buffer.setProximo(buffer);
		} else {
			No<T> inicio_buffer = this.ultimo_elemento.getProximo();
			inicio_buffer.setProximo(buffer);
			buffer.setProximo(inicio_buffer);
			this.ultimo_elemento = buffer;
		}
	}
	
	public void enqueueFila(T elemento, String type) {
		No<T> buffer = this.ultimo_elemento;
		boolean existeProtocolo = false;
		
		if(buffer.getType() == type) existeProtocolo = true;
		
		while(buffer.getProximo() != ultimo_elemento) {
			buffer = buffer.getProximo();
			if(buffer.getType() == type) {
				existeProtocolo = true;
				break;
			}
		}
		
		if(buffer == null || !existeProtocolo) {
			this.append(type,((int)(Math.random() * 2000) + 1000) + "");
			buffer = this.ultimo_elemento;
		}
		
		System.out.println("A");
		
		buffer.fila.enqueue(elemento);
		
		this.ultimo_elemento = buffer;
		
		
	}
	
	public void dequeueFila(String type) throws IllegalArgumentException {
		No<T> buffer = this.ultimo_elemento;
		boolean existeProtocolo = false;
		
		if(buffer.getType() == type) existeProtocolo = true;
		
		while(buffer.getProximo() != ultimo_elemento) {
			buffer = buffer.getProximo();
			if(buffer.getType() == type) {
				existeProtocolo = true;
				break;
			}
			
			
		}
		
		if(buffer == null || !existeProtocolo) {
			throw new IllegalArgumentException("Não existe um protocolo com esse nome");
		}
		
		System.out.println("A");
		
		buffer.fila.dequeue();
		
		this.ultimo_elemento = buffer;
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
		
		No<T> buffer = this.ultimo_elemento;
		for(i = 0; i < index; i++){
			if(buffer.getProximo() == this.ultimo_elemento)
				break;
			buffer = buffer.getProximo();
		}
		
		if(i < index)
			throw new IllegalArgumentException("O indice informado nao existe");
		return buffer;
	}
	
	public void remove(int index){
		if(index == 0){
			this.ultimo_elemento.setFila(null);
			if(this.ultimo_elemento.getProximo() == null){
				this.ultimo_elemento = null;
			} else{
				No<T> primeiro = this.ultimo_elemento.getProximo();
				No<T> buffer = primeiro.getProximo();
				primeiro.setProximo(null);
				primeiro.setFila(null);
				this.ultimo_elemento.setProximo(buffer);
				this.ultimo_elemento = buffer;
			}
			return;
		}
		No<T> buffer_anterior = this.get(--index);
		No<T> item = buffer_anterior.getProximo();
		No<T> buffer_proximo = item.getProximo();
		buffer_anterior.setProximo(buffer_proximo);
		buffer_proximo.setProximo(buffer_anterior);
		if(total() == index) this.ultimo_elemento = buffer_anterior; 
		item.setProximo(null);
		item.setFila(null);

	}
	
	public int total(){
		if(this.ultimo_elemento == null)
			return 0;
		No<T> buffer = this.ultimo_elemento;
		int total_elementos = 0;
		do{
			total_elementos++;
			buffer = buffer.getProximo();
		} while(buffer != this.ultimo_elemento);
		return total_elementos;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		No<T> buffer = this.ultimo_elemento;
				
		if(buffer == null) {
			return "[]";
		}
		
		s.append("[");
		
		s.append(buffer.toString());
		s.append(buffer.fila.toString());
		while(buffer.getProximo() != ultimo_elemento) {
			s.append(", ");
			buffer = buffer.getProximo();
			s.append(buffer.toString());
			s.append(buffer.fila.toString());
			
			
		}
		
		
		s.append("]");
		return s.toString();
	}
	
	
}
