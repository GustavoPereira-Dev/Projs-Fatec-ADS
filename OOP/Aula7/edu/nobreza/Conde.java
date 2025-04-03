package edu.nobreza;

public class Conde extends Nobre{
	private String dominio;

	public Conde(Conselheiro conselheiro, String dominio){
		this.super(conselheiro);
		this.dominio = dominio;
	}
	
	public Conde(){
		super();
	}
	
    public void administrarDominio() {
        System.out.println(nome + " administra seu domínio de " + dominio + " com eficiência.");
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