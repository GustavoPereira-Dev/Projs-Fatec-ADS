package edu.nobreza;

public class Bispo extends Padre implements Fiel{
    private String diocese;

	public Bispo(String ordemReligiosa, String diocese){
		super(ordemReligiosa);
		this.diocese = diocese;
	}
	
    @Override
    public void rezar() {
        System.out.println("O bispo da diocese " + diocese + " está rezando.");
    }
    
    @Override
    public void celebrarMissa() {
        System.out.println("O bispo está celebrando uma missa.");
    }

    public void liderarCerimonia() {
        System.out.println("O bispo lidera uma cerimônia religiosa.");
    }

	public String getDiocese(){
		return this.diocese;
	}
	
	public void setDiocese(String diocese){
		this.diocese = diocese;
	}
	
	public String toString(){
		return "Diocese: " + this.diocese + "; Ordem Religiosa: " + this.getOrdemReligiosa();
	}

}