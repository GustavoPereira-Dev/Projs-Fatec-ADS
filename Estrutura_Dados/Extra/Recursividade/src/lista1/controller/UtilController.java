package lista1.controller;

import javax.swing.JOptionPane;
public class UtilController {
	
	public int[] carregarVetor(int tam) {
		int[] vetor = new int[tam];
		int i;
		
		System.out.println("Números do Vetor: ");
		for(i = 0; i < tam - 1; i++) {
			vetor[i] = (int) ((Math.random() * 500) - 100);
			System.out.println(vetor[i]);
		}
		System.out.println("--------------------------------------------------");
		return vetor;
		
	}
	
	public boolean verificarEntradaFat(int n) {
		return n < 12 ? true : false;
	}
}
