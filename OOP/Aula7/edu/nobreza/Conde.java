package edu.nobreza;

public class Conde extends Nobre{
	private String dominio;

	public Conde(Conselheiro conselheiro, String dominio){
		super(conselheiro);
		this.dominio = dominio;
	}
	
	@Override
	public void gonvernar() {
		System.out.println("Conde esta gonvernando");
		
	}
	
    public void administrarDominio() {
        System.out.println("Conde administra seu domínio de " + dominio + " com eficiência.");
    }
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}
	
	public String getDominio(){
		return this.dominio;
	}
	
	public String toString(){
		return "Dominio: " + this.dominio + "; Conselheiro: " + this.conselheiro.toString() + "; Soldados: " + this.soldados.toString();
	}

}