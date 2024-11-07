package model.estrutura.pilha;

public class No<T> {
	
	private String valor;
	private No<String> anterior;

	public No(String valor){
		this.anterior = null;
		this.valor = valor;
	}

	public String getValor(){
		return valor;
	}

	public void setValor(String valor){
		this.valor = valor;
	}

	public No<String> getAnterior(){
		return anterior;
	}

	public void setAnterior(No<String> anterior){
		this.anterior = anterior;
	}

	@Override
	public String toString(){
		return valor.toString();
	}

}