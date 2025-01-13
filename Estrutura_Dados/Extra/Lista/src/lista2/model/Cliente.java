package lista2.model;

public class Cliente implements Comparable<Cliente>{
	private String CPF;
	private String nome;
	private int idade;
	private double limiteCredito;
	
	public Cliente(String CPF, String nome, int idade, double limiteCredito) {
		this.CPF = CPF;
		this.nome = nome;
		this.idade = idade;
		this.limiteCredito = limiteCredito;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public int compareTo(Cliente o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
