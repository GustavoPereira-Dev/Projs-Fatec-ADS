package edu.nobreza;

public class Principe extends Nobre implements Cavaleiro{
    private String reino;

	public Principe(Conselheiro conselheiro, String reino){
		this.super(Conselheiro conselheiro);
		this.reino = reino;
	}
	
    @Override
    void duelar() {
        System.out.println(nome + " está duelando bravamente pelo reino " + reino + "!");
    }

    void liderarGuerra() {
        System.out.println(nome + " lidera os exércitos em batalha!");
    }
	
	public String getReino(){
		return this.reino;
	}
	
	public void setReino(String reino){
		this.reino = reino;
	}

	public String toString(){
		return "Reino: " + this.reino;
	}
	
	public String toString(){
		return "Reino: " + this.reino + "; Conselheiro: " + this.conselheiro.toString() + "; Soldados: " + this.soldados.toString();
	}
}