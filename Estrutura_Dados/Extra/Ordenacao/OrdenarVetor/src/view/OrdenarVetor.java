package view;

import controller.OrdenarVetorController;

public class OrdenarVetor {
	final static int num = 1499;
	public static void main(String[] args) {
		int[] vetor = new int[1500];
		int[] aux = new int[1500];
		OrdenarVetorController od = new OrdenarVetorController();
		
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
