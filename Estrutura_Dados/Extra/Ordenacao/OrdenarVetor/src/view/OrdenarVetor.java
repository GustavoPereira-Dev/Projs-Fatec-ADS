package view;

import controller.OrdenarVetorController;

public class OrdenarVetor {
	final static int num = 1499;
	public static void main(String[] args) {
		OrdenarVetorController od = new OrdenarVetorController();
		
		System.out.println("--------------------Exercício 7--------------------");
		
		System.out.println("------------Exercício 1------------");
		
		int[] vetor1 = {74, 20, 74, 87, 81, 16, 25, 99, 44, 58};
		vetor1 = od.bubbleSortCrescente(vetor1);
		
		System.out.println("Vetor Crescente Bubble Sort: ");
		for(int valor: vetor1) {
			System.out.print(valor + " ");
		}
		
		System.out.println("");
		
		vetor1 = od.bubbleSortDecrescente(vetor1);
		System.out.println("\nVetor Decrescente Bubble Sort: ");
		for(int valor: vetor1) {
			System.out.print(valor + " ");
		}
		
		System.out.println("");
		
		vetor1 = od.mergeSort(vetor1, 0, vetor1.length - 1);
		
		System.out.println("\nVetor Crescente Merge Sort: ");
		for(int valor: vetor1) {
			System.out.print(valor + " ");
		}
		
		System.out.println("\n------------Exercício 2------------");
		int[] vetor2 = {44, 43, 42, 41, 40, 39, 38};
		
		vetor2 = od.bubbleSortCrescente(vetor1);
		
		System.out.println("Vetor Crescente Bubble Sort: ");
		for(int valor: vetor2) {
			System.out.print(valor + " ");
		}
		
		System.out.println("");
		
		vetor2 = od.mergeSort(vetor1, 0, vetor2.length - 1);
		
		System.out.println("\nVetor Crescente Bubble Sort: ");
		for(int valor: vetor2) {
			System.out.print(valor + " ");
		}
		
		System.out.println("\n------------Exercício 3------------");
		int[] vetor3 = {74, 20, 74, 87, 81, 16, 25, 99, 44, 58};
		
		vetor3 = od.quickSort(vetor1, 0, vetor2.length - 1);
		
		System.out.println("Vetor Crescente Quick Sort: ");
		for(int valor: vetor3) {
			System.out.print(valor + " ");
		}
		
		System.out.println("\n------------Exercício 4------------");
		int[] vetor4 = {44, 43, 42, 41, 40, 39, 38};
		
		vetor4 = od.quickSort(vetor1, 0, vetor2.length - 1);
		
		System.out.println("Vetor Crescente Quick Sort: ");
		for(int valor: vetor4) {
			System.out.print(valor + " ");
		}
		
		System.out.println("\n------------Exercício 5------------");
		int[] vetor5 = {31, 32, 33, 34, 99, 98, 97, 96};
		
		vetor5 = od.quickSort(vetor1, 0, vetor2.length - 1);
		
		System.out.println("Vetor Crescente Quick Sort: ");
		for(int valor: vetor5) {
			System.out.print(valor + " ");
		}
		
		System.out.println("\n--------------------Exercício 8--------------------");
		int[] vetor = new int[1500];
		int[] aux = new int[1500];
		
		double tempoInicial, tempoFinal, tempoBubble, tempoMerge, tempoQuick;
		int i;
		
		for(i = 0; i < vetor.length; i++) {
			vetor[i] = num - i;
		}
		
		aux = vetor;
		
		tempoInicial = System.nanoTime();
		od.bubbleSortCrescente(vetor);
		tempoFinal = System.nanoTime();
		tempoBubble = tempoFinal - tempoInicial;
		tempoBubble /= Math.pow(10, 9);
		
		tempoInicial = System.nanoTime();
		aux = od.mergeSort(vetor, 0, 1499);
		tempoFinal = System.nanoTime();
		tempoMerge = tempoFinal - tempoInicial;
		tempoMerge /= Math.pow(10, 9);
		
		aux = vetor;
		
		tempoInicial = System.nanoTime();
		aux = od.quickSort(vetor, 0, 1499);
		tempoFinal = System.nanoTime();
		tempoQuick = tempoFinal - tempoInicial;
		tempoQuick /= Math.pow(10, 9);
		
		System.out.println("Tempos de Execução: ");
		System.out.println("Bubble Sort: " + tempoBubble + "; Merge Sort " + tempoMerge + "; Quick Sort: " + tempoQuick);
		
		
	}
}
