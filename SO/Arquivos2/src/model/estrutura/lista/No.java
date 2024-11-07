package model.estrutura.lista;

public class No<T> {
	
	private String valor;
	private No<String> proximo;
	private No<String> anterior;
	
	public No(String valor){
		this.proximo = null;
		this.valor = valor;
	}
	
	public No(String valor, No<String> proximo){
		this.valor = valor;
		this.proximo = proximo;
	}
	
	public String getValor(){
		return valor;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
	
	public No<String> getProximo(){
		return proximo;
	}
	
	public void setProximo(No<String> proximo){
		this.proximo = proximo;
	}
	
	
	
	public No<String> getAnterior() {
		return anterior;
	}

	public void setAnterior(No<String> anterior) {
		this.anterior = anterior;
	}

	@Override
	public String toString(){
		return valor.toString();
	}
}