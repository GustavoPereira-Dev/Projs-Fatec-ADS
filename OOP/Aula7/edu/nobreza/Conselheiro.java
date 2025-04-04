package edu.nobreza;

import java.util.LinkedList;
import java.util.List;

public class Conselheiro{
	private String especialidade;
	private List<Bispo> bispos = new LinkedList<Bispo>();

	public Conselheiro(String especialidade){
		this.especialidade = especialidade;
	}
	
	public Conselheiro(){
		super();
	}
		
	public void aconselhar() {
        System.out.println("O conselheiro está oferecendo sabedoria na área de " + especialidade + ".");
    }
	
	public void setEspecialidade(String especialidade){
		this.especialidade = especialidade;
	}
	
	public String getEspecialidade(){
		return this.especialidade;
	}
	
	public void setBispo(Bispo bispo){
		this.bispos.add(bispo);
	}
	
	public String getBispos(){
		return this.bispos.toString();
	}
	
	public String toString(){
		return "Especialidade: " + this.especialidade + "; Bispos: " + this.bispos.toString();
	}

}