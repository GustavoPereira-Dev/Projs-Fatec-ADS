package lista2.view;

import lista2.controller.ListaRecursividadeController;
import lista2.controller.UtilController;

public class ListaRecursividade {
	public static void main(String[] args) {
		ListaRecursividadeController l2 = new ListaRecursividadeController();
		UtilController u2 = new UtilController();
		int t = 5;
		int[] vetor;
		
		System.out.println("");
		System.out.println(l2.multiplicacaoPorSoma(3, 4));
		
		System.out.println("");
		System.out.println(l2.divisaoPorSub(10, 2));
		
		System.out.println("");
		vetor = u2.carregarVetor(10);
		System.out.println(l2.somatoriaPares(vetor, vetor.length - 1));
		
		System.out.println("");
		if(u2.validarFatorial(t)) System.out.println(l2.fatorialDuplo(t));;
		
		System.out.println("");
		System.out.println(l2.mdc(24, 20));
		
		
	}
}
