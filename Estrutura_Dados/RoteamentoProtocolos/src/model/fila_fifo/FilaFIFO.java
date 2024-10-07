package model.fila_fifo;
import java.lang.Exception;

public class FilaFIFO<T> {
	public No<T> inicio = null;
	private No<T> fim = null;
	
	
	public void enqueue(T elemento) {
		No<T> buffer = new No<>(elemento);
		if(this.inicio == null) {
			this.inicio = buffer;
			this.fim = buffer;
		} else {
			this.fim.setProximo(buffer);
			this.fim = buffer;
		}
		
	}
	
	public No<T> dequeue(){
		if(this.inicio == null) {
			return null;
		} else {
			No<T> primeiro = this.inicio;
			this.inicio = primeiro.getProximo();
			return primeiro;	
		}
	}
	
	public String toString() {
		if(this.inicio == null) {
			return "[]";
		}
		
		No<T> buffer = this.inicio;
		StringBuilder s = new StringBuilder();
		
		s.append("[" + buffer.getValor());
		
		while(buffer.getProximo() != null) {
			s.append(", ");
			buffer = buffer.getProximo();
			s.append(buffer.getValor());
		}
		s.append("]");
		return s.toString();
	}
}
