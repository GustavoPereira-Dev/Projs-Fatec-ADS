package model.estrutura.arvore;

public class No<T>{
	private T valor;
	private No<T> maior;
	private No<T> menor;
	
	public No(T valor){
		this.maior = null;
		this.menor = null;
		this.valor = valor;
	}
	
	public T getValor(){
		return this.valor;
	}
	
	public void setValor(T valor){
		this.valor = valor;
	}
	
	public No<T> getMaior(){
		return this.maior;
	}
	
	public void setMaior(No<T> maior){
		this.maior = maior;
	}
	
	public No<T> getMenor(){
		return this.menor;
	}
	
	public void setMenor(No<T> menor){
		this.menor = menor;
	}
	
	public String toString(){
		return valor.toString();
	}
	
}