public class Aeronave{
	String cor;
	String empresa;
	String marca;
	tipo String;
	
	public Aeronave(String cor, String empresa, String marca, tipo String){
		this.cor = cor;
		this.empresa = empresa;
		this.marca = marca;
		this.tipo = tipo;
	}
	
	public void decolar(){
		System.out.println("O avião " + empresa + " " + marca + " " + tipo + " " + cor + " esta decolando");
	}
	
	public void pousar(){
		System.out.println("O avião " + + empresa + " " + marca + " " + tipo + " " + cor + " esta pousando");
	}

	public void baliza(){
		System.out.println("O avião " + empresa + " " + marca + " " + tipo + " " + cor + " esta fazendo baliza");
	}

}