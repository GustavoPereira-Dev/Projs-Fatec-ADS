package model;

public class BaixoNivel {
	private String[][] comandos;
	private String[][] memorias;
	private String[] status; // PC (comando atual), AC (valor do Acumulador), IR (valor comando)

	public BaixoNivel(String[][] comandos, String[][] memorias, String[] status) {
		this.comandos = comandos;
		this.memorias = memorias;
		this.status = status; 
	}

	public String[][] getComandos() {
		return comandos;
	}

	public String[][] getMemorias() {
		return memorias;
	}

	public String[] getStatus() {
		return status;
	}
	
}
