public class Pessoa{
	String nome;
	String sexo;
	int identificacao;
	
	public Pessoa(String nome, String sexo, int identificacao){
		this.nome = nome;
		this.sexo = sexo;
		this.identificacao = identificacao;
	}
	
	public void caminhar(){
		System.out.println(nome + " está caminhando");
	}
	
}