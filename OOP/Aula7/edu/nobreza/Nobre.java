package edu.nobreza;

public abstract class Nobre{
	private Conselheiro conselheiro;
	private List<Soldado> soldados;
	
	public void Nobre(Conselheiro conselheiro){
		this.conselheiro = conselheiro;
	}
	
	abstract public void gonvernar();
	
	public setSoldado(String soldado){
		soldados.add(soldado);
	}
	
	public String getSoldado(){
		return soldados.toString();
	}
	
	public setConselheiro(String conselheiro){
		this.conselheiro = conselheiro;
	}
	
	public String getSoldado(){
		return conselheiro.toString();
	}
	
	public String toString(){
		return "Conselheiro: " + this.conselheiro + " Soldados: " + soldados.toString();
	}
	
}