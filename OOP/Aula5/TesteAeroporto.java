public class TesteAeroporto{
	public static void main(String[] args){
		Aeroporto ap1 = new Aeroporto("Congonhas","Washington Luis", 521342121);
		Aeroporto ap2 = new Aeroporto("Guarulhos","Natalia Zarif", 241342121);
		
		Aeronave an1 = new Aeronave("Branco", "LATAM", "Boeing", "Comercial");
		Aeronave an2 = new Aeronave("Branco", "FAB", "Antonov", "Carga");
		
		Pessoa pe1 = new Pessoa("Luciano", "Masculino", 31);
		Pessoa pe2 = new Pessoa("Geovana", "Feminino", 40);
		
		Passageiro ps1 = new Passageiro("Claudio", "Masculino", 1214, 1892, 11, 12);
		Passageiro ps2 = new Passageiro("Ana", "Feminino", 1242, 1254, 14, 15);
		
		Piloto plt1 = new Piloto("Carlos", "Masculino", 124, an1, 10, 2141);
		Piloto plt2 = new Piloto("Pedro", "Masculino", 109, an2, 23, 2190);
		
		Pessoa pe3 = new Passageiro("Claudio", "Masculino", 1214, 1892, 11, 12);
		
		pe1.caminhar();
		pe2.caminhar();
		
		ap1.informar(an1);
		
		ps1.utilizarAviao();
		ps2.utilizarAviao();

		plt1.utilizarAviao();
		
		ap2.informar(an2);
		
		plt2.utilizarAviao();
	}
}