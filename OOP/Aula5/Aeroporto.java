public class Aeroporto{
	String nome;
	String local;
	int capacidade;
	
	public Aeroporto(String nome, String local, int capacidade){
		this.nome = nome;
		this.local = local;
		this.capacidade = capacidade;
	}
	
	public void informar(Aeronave aeronave){
		System.out.println("O avião " + aeronave.empresa + " " + aeronave.marca + " " + aeronave.tipo + " " + aeronave.cor + " está para sair do aeroporto");
	}

}