public class Aeronave{
	String cor;
	String empresa;
	String marca;
	String tipo;
	
	public Aeronave(String cor, String empresa, String marca, String tipo){
		this.cor = cor;
		this.empresa = empresa;
		this.marca = marca;
		this.tipo = tipo;
	}
	
	public void decolar(){
		System.out.println("O avião " + empresa + " " + marca + " " + tipo + " " + cor + " esta decolando");
	}
	
	public void pousar(){
		System.out.println("O avião " + empresa + " " + marca + " " + tipo + " " + cor + " esta pousando");
	}

	public void baliza(){
		System.out.println("O avião " + empresa + " " + marca + " " + tipo + " " + cor + " esta fazendo baliza");
	}

}