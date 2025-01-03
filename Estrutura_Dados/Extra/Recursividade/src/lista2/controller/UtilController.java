package lista2.controller;

public class UtilController {
	public int[] carregarVetor(int tam) {
		int[] vetor = new int[tam];
		int i;
		
		System.out.println("Números do Vetor: ");
		for(i = 0; i < tam - 1; i++) {
			vetor[i] = (int) ((Math.random() * 100) + 1);
			System.out.println(vetor[i]);
		}
		System.out.println("--------------------------------------------------");
		return vetor;
		
	}
	
	public boolean validarFatorial(int n) {
		return n % 2 == 1 ? true : false;
	}
}
