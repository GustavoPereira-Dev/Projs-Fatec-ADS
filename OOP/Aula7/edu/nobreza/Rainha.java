package edu.nobreza;

public class Rainha extends Nobre implements Diplomata{
    private String paisOrigem;

	public Rainha(Conselheiro conselheiro, String paisOrigem){
		super(conselheiro);
		this.paisOrigem = paisOrigem;
	}
	
	@Override
	public void gonvernar() {
		System.out.println("Rainha esta governando o reino");
	}
	
    @Override
    public void fazerDiplomacia() {
        System.out.println("Rainha faz diplomacia em nome de " + paisOrigem + ".");
    }

    public void organizarBaileReal() {
        System.out.println("Rainha organiza um baile para fortalecer alianças diplomáticas.");
    }
	
	public String paisOrigem(){
		return this.paisOrigem;
	}
	
	public void setPaisOrigem(String paisOrigem){
		this.paisOrigem = paisOrigem;
	}
	
	public String toString(){
		return "Pais de origem: " + this.paisOrigem + "; Conselheiro: " + this.conselheiro.toString() + "; Soldados: " + this.soldados.toString();
	}

}