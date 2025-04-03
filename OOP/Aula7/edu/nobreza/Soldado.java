package edu.nobreza;

public class Soldado{
    private String patente; 
    private int experiencia;

    public void proteger() {
        System.out.println("O soldado de patente " + patente + " está protegendo o reino.");
    }

	public void setPatente(String patente){
		this.patente = patente;
	}
	
	public String getPatente(){
		return this.patente;
	}
	
	public void setExperiencia(String experiencia){
		this.experiencia = experiencia;
	}
	
	public String getPatente(){
		return this.experiencia;
	}
	
	public String toString(){
		return "Patente: " + patente + "; Experiencia: " + experiencia; 
	}
	
}