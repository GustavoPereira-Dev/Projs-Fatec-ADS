package lista3.controller;

import java.util.LinkedList;

public class Lista3Controller {
	
	// Exercicio 3
	public void inserirPeloVetor() {
		int[] vetor = {3,3,9,6,9,8,9,5,7,10,4,8,10,8};
		LinkedList<Integer> lista = new LinkedList<Integer>();
		
		lista.add(1);
		lista.add(2);
		lista.add(6);
		lista.add(7);
		lista.add(8);
		
		for(int valor: vetor) {		
			if(lista.contains(valor)) {
				lista.addLast(valor);
			} else if(valor % 2 == 0) {
				lista.add(2, valor);
			} else {
				lista.add(1, valor);
			}
		}
		
		System.out.println(lista.toString());
		
	}
}
