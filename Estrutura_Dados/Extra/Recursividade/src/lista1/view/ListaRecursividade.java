package lista1.view;

import lista1.controller.ListaRecursividadeController;
import lista1.controller.UtilController;

public class ListaRecursividade {
	public static void main(String[] args) {
		ListaRecursividadeController l1 = new ListaRecursividadeController();
		UtilController u1 = new UtilController();
		int[] vetor;
		
		System.out.println(l1.somatoriaNumeros(10));
		
		System.out.println("");
		vetor = u1.carregarVetor(10);
		
		System.out.println(l1.calcMenor(vetor, vetor.length - 1, vetor[vetor.length - 1]));
		System.out.println("");
		
		
		int t = 5;
		System.out.println("");
		if(u1.verificarEntradaFat(t)) System.out.println(l1.calcFat(t));
		
		System.out.println("");
		System.out.println(l1.totalNegativos(vetor, vetor.length - 1));
		
		System.out.println("");
		System.out.println(l1.somarSequencia(t));
	}
}
