package edu.nobreza;

public class Padre{
    private String ordemReligiosa;

	public Padre(String ordemReligiosa){
		this.ordemReligiosa = ordemReligiosa;
	}
	
    public void celebrarMissa() {
        System.out.println("O padre está celebrando uma missa.");
    }
	
	public String getOrdemReligiosa(){
		return this.ordemReligiosa;
	}
	
	public void setOrdemReligiosa(String ordemReligiosa){
		this.ordemReligiosa = ordemReligiosa;
	}
	
	public String toString(){
		return "Ordem Religiosa: " + this.ordemReligiosa;
	}
}
