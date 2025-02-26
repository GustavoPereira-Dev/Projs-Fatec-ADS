public class Cachorro{
	public float altura;
	public float peso;
	public String nome;
	public String raca;
	public String latido;
	
	public void andar(){
		System.out.println(nome + " está caminhando");
	}
	
	public void comendo(){
		System.out.println(nome + " está comendo");
	}
	
	public void latir(){
		System.out.println(nome + " está latindo: " + latido);
	}

}