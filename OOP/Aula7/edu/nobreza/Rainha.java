package edu.nobreza;

public class Rainha extends Nobre implements Diplomata{
    private String paisOrigem;

	public Rainha(Conselheiro conselheiro, String paisOrigem){
		this.super(conselheiro);
		this.paisOrigem = paisOrigem;
	}
    @Override
    public void fazerDiplomacia() {
        System.out.println(nome + " faz diplomacia em nome de " + paisOrigem + ".");
    }

    public void organizarBaileReal() {
        System.out.println(nome + " organiza um baile para fortalecer alianças diplomáticas.");
    }
	
	public String paisOrigem(){
		return this.paisOrigem;
	}
	
	public void setPaisOrigem(){
		this.paisOrigem = paisOrigem;
	}
	
	public String toString(){
		return "Pais de origem: " + this.paisOrigem + "; Conselheiro: " + this.conselheiro.toString() + "; Soldados: " + this.soldados.toString();
	}

}