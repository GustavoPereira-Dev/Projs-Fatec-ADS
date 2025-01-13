package lista1.model.pilha;

public class No<T> {
	
	private T valor;
	private No<T> anterior;

	public No(T valor){
		this.anterior = null;
		this.valor = valor;
	}

	public T getValor(){
		return valor;
	}

	public void setValor(T valor){
		this.valor = valor;
	}

	public No<T> getAnterior(){
		return anterior;
	}

	public void setAnterior(No<T> anterior){
		this.anterior = anterior;
	}

	@Override
	public String toString(){
		return valor.toString();
	}

}