package edu.nobreza;

import java.util.LinkedList;
import java.util.List;

public abstract class Nobre{
	protected Conselheiro conselheiro;
	protected List<Soldado> soldados = new LinkedList<Soldado>();
	
	public Nobre(Conselheiro conselheiro){
		this.conselheiro = conselheiro;
	}
	
	abstract public void gonvernar();
	
	public void setSoldado(Soldado soldado){
		soldados.add(soldado);
	}
	
	public String getSoldado(){
		return soldados.toString();
	}
	
	public void setConselheiro(Conselheiro conselheiro){
		this.conselheiro = conselheiro;
	}
	
	public String getConselheiro(){
		return this.conselheiro.toString();
	}
	
	public String toString(){
		return "Conselheiro: " + this.conselheiro + " Soldados: " + soldados.toString();
	}
	
}