public class Principal{
	
	public static void main(String[] args){
		// Cachorro
		
		Cachorro dudu = new Cachorro();
		dudu.altura = 1.21;
		dudu.nome = "Dudu";
		dudu.raca = "Pug";
		dudu.peso = 30f;
		dudu.latido = "au...";
		dudu.andar();
		dudu.comendo();
		dudu.latir();
		
		Cachorro prisca = new Cachorro();
		prisca.altura = 1.42;
		prisca.nome = "Priscilla";
		prisca.raca = "Irlandês";
		prisca.peso = 50f;
		prisca.latido = "Au...auauau";
		prisca.andar();
		prisca.comendo();
		prisca.latir();
		
		// Carro
		
		Carro cerato = new Carro();
		cerato.portas = 4;
		cerato.marca = "Kia";
		cerato.modelo = "Cerato";
		cerato.cor = "Azul";
		cerato.som = "Vrumrumrum...";
		cerato.mover();
		cerato.estacionar();
		System.out.println(cerato.status("Imóvel"));
		
		Carro ka = new Carro();
		ka.portas = 4;
		ka.marca = "Ford";
		ka.modelo = "Ka";
		ka.cor = "Branco";
		ka.som = "Vrumr...";
		ka.mover();
		ka.estacionar();
		System.out.println(ka.status("Na mecânica"));	
		
		// Ser Vivo
		
		SerVivo fungo = new SerVivo();
		fungo.reino = "Fungi";
		fungo.filo = "Basidiomycota";
		fungo.familia = "Psathyllaceae";
		fungo.nome = "Fungo Cogumelo";
		fungo.vertebrado = false;
		System.out.println(fungo.eVertebrado());
		fungo.caracteristicas();
		fungo.reino();
		
		SerVivo baleia = new SerVivo();
		baleia.reino = "Animalia";
		baleia.filo = "Chordata";
		baleia.familia = "Mammalia";
		baleia.nome = "Baleia Jubarte";
		baleia.vertebrado = true;
		System.out.println(baleia.eVertebrado());
		baleia.caracteristicas();
		baleia.reino();

	}
}