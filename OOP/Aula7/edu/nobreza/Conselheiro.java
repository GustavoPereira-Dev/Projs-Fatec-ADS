package edu.nobreza;

public class Conselheiro{
	private String especialidade;
	private List<Bispo> bispos;

	public Conselheiro(String especialidade){
		this.especialidade = especialidade;
		this.bispos = null;
	}
	
	public Conselheiro(){
		super();
	}
		
	
	public void aconselhar() {
        System.out.println("O conselheiro está oferecendo sabedoria na área de " + especialidade + ".");
    }
	
	public void setEspecialidade(){
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