public class Porteiro{
	String numero;
	String nome;
	int horaEntrada;
	private static Porteiro instancia;
	
	private static Porteiro(String numero, String nome, int horaEntrada){
		this.numero = numero;
		this.nome = nome;
		this.horaEntrada = horaEntrada;
	}
	
	public void abrirPortao(){
		System.out.println("O porteiro " + nome + " está abrindo o portão");
	}

	public void interfonar(){
		System.out.println("O porteiro " + nome + " está interfonando");
	}
	
	public void atenderTelefone(){
		System.out.println("O porteiro " + nome + " está atendendo o telefone");
	}
	
	public String toString(){
		return "Numero: " + numero + "; Nome: " + nome + "; hora de entrada: " + horaEntrada;
	}
	
	
	public boolean equals(Porteiro outro){
		retutn numero.equals(outro.numero) && nome.equals(outro.nome);
	}
	public Porteiro getInstancia(){
		if(instancia == null)
			instancia = new Porteiro(222, "Maria", 9);
		return instancia;
	}
	
}