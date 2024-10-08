package model.lista_circular;

import model.fila_fifo.FilaFIFO;

public class No<T> {
	private No<T> proximo;
	private String type;
	FilaFIFO<T> fila = new FilaFIFO<>();
	

	public No(String type) {
		this.proximo = null;
		this.type = type;
	}

	public No<T> getProximo() {
		return proximo;
	}

	public void setProximo(No<T> proximo) {
		this.proximo = proximo;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public FilaFIFO<T> getFila() {
		return fila;
	}

	public void setFila(FilaFIFO<T> fila) {
		this.fila = fila;
	}

	public String toString() {
		return  type + " " + fila.toString();
	}
}
