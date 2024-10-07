package model.lista_circular;

import model.fila_fifo.FilaFIFO;

public class No<T> {
	private No<T> proximo;
	private String type;
	private String dados;
	FilaFIFO<T> fila = new FilaFIFO<>();
	

	public No(String type, String dados) {
		this.proximo = null;
		this.type = type;
		this.dados = dados;
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

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}
	
	

	public FilaFIFO<T> getFila() {
		return fila;
	}

	public void setFila(FilaFIFO<T> fila) {
		this.fila = fila;
	}

	public String toString() {
		return  type + " " + dados;
	}
}
