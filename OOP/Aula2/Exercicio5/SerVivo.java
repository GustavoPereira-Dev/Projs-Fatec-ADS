public class SerVivo{
	public String reino;
	public String filo;
	public String familia;
	public String nome;
	public boolean vertebrado;
	
	public String eVertebrado(){
		return vertebrado ? "É vertebrado" : "Não é Vertebrado";
	}
	
	public void caracteristicas(){
		System.out.println("O ser vivo " + nome + "  é do reino " + reino + ", filo " + filo + " e familia " + familia);
	}
	
	public void reino(){

		System.out.print("O ser vivo é um(a) ");
		switch reino{
			case "Fungi":
				System.out.println("Fungo");
			case "Plantae":
				System.out.println("Planta");
			case "Vegetal":
				System.out.println("Planta");
			case "Monera":
				System.out.println("Bactéria");
			case "Protista":
				System.out.println("Protozoário");
			case "Animalia":
				System.out.println("Animal");
			default:
				System.out.println(reino);
		}
	}