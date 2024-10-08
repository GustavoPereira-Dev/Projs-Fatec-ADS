package model.lista_circular;


public class ListaCircular<T> {
	private No<T> ultimo_elemento = null;
	
	public void append(String type, T dado) {
		No<T> buffer_valor = new No<>(type);
		
		if(this.ultimo_elemento == null) {
			buffer_valor.fila.enqueue(dado);
			buffer_valor.setProximo(buffer_valor);
			this.ultimo_elemento = buffer_valor;
			
		} else {
			No<T> buffer = this.ultimo_elemento;
			boolean existeProtocolo = false;
			
			if(buffer.getType() == type) existeProtocolo = true;
			
			while(buffer.getProximo() != this.ultimo_elemento) {
				if(buffer.getType() == type) {
					existeProtocolo = true;
					break;
				}
				buffer = buffer.getProximo();
			}
			
			if(buffer == null || !existeProtocolo) {
				
				if(this.ultimo_elemento == null) {
					buffer.setProximo(buffer);
					this.ultimo_elemento = buffer;
				} else {
					No<T> fim_buffer = this.ultimo_elemento;
					No<T> inicio_buffer = fim_buffer.getProximo();
					inicio_buffer.setProximo(buffer_valor);
					buffer_valor.setProximo(fim_buffer);
					buffer = buffer_valor;
				}
			}
			this.ultimo_elemento = buffer;
			buffer.fila.enqueue(dado);
		}
		
		
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
		
		buffer.fila.dequeue();
		
		this.ultimo_elemento = buffer;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		No<T> buffer = this.ultimo_elemento;
		if(buffer == null) {
			return "[]";
		}
		s.append("[");
		
		s.append(buffer.toString());
		buffer = buffer.getProximo();
		while(buffer != ultimo_elemento) {
			s.append(", ");
			s.append(buffer.toString());
			buffer = buffer.getProximo();
		}
		
		
		s.append("]");
		return s.toString();
	}
	
	
}
