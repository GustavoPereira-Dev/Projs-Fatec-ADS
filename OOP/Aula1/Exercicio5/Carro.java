public class Carro{
	public int portas;
	public String marca;
	public String modelo;
	public String cor;
	public String som;
	
	public void mover(){
		System.out.println("O " + marca + " " + modelo + " " + cor + " está se movendo: " + som + " " + som + " " + som + " ");
	}
	
	public void estacionar(){
		System.out.println("O " + marca + " " + modelo + " " + cor + " está se estacionando: " + som + "....");
	}
	
	public String status(String st){
		return "O carro " + marca + " " + modelo + " " + cor + " com " + portas + " portas " + " está " + st;
	}
	
}