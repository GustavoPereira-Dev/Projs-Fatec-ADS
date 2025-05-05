public class Passageiro extends Pessoa{
	int numeroPassageiro;
	int entradaAeroporto;
	int horaVoo;
	
	public Passageiro(String nome, String sexo, int identificacao, int numeroPassageiro, int entradaAeroporto, int horaVoo){
		super(nome, sexo, identificacao);
		this.numeroPassageiro = numeroPassageiro;
		this.entradaAeroporto = entradaAeroporto;
		this.horaVoo = horaVoo;
	}
	
	@Override
	public void caminhar(){
		System.out.println("Passageiro " + nome + " está caminhando");
	}
	
	public void utilizarAviao(){
		System.out.println("Passageiro " + this.nome + " esta utilizando o aviao para ir para o local y as " + horaVoo);
	}
}