package edu.nobreza;

public class Principe extends Nobre implements Cavaleiro{
    private String reino;

	public Principe(Conselheiro conselheiro, String reino){
		super(conselheiro);
		this.reino = reino;
	}
	
	@Override
	public void gonvernar() {
		System.out.println("Principe esta governando");
	}
	
    @Override
    public void duelar() {
        System.out.println("Principe está duelando bravamente pelo reino " + reino + "!");
    }

    public void liderarGuerra() {
        System.out.println("Principe lidera os exércitos em batalha!");
    }
	
	public String getReino(){
		return this.reino;
	}
	
	public void setReino(String reino){
		this.reino = reino;
	}
	
	public String toString(){
		return "Reino: " + this.reino + "; Conselheiro: " + this.conselheiro.toString() + "; Soldados: " + this.soldados.toString();
	}
}