public class Piloto extends Pessoa{
	
	Aeronave aeronave;
	int quantidadeVoos;
	int numeroPiloto;
	
	public Piloto(String nome, String sexo, int identificacao, Aeronave aeronave, int quantidadeVoos, int numeroPiloto){
		super(nome, sexo, identificacao);
		this.aeronave = aeronave;
		this.quantidadeVoos = quantidadeVoos;
		this.numeroPiloto = numeroPiloto;
	}
	
	public void utilizarAviao(){
		System.out.println("O piloto " + this.nome + " está levando o avião " + this.aeronave.empresa + " " + this.aeronave.marca + " " + this.aeronave.tipo + " " + this.aeronave.cor + " para o local y");
	}
}