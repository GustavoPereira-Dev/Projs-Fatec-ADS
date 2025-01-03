package lista3.view;

import lista3.controller.ListaRecursividadeController;
import lista3.controller.UtilController;

public class ListaRecursividade {
	public static void main(String[] args) {
		ListaRecursividadeController l3 = new ListaRecursividadeController();
		UtilController u3 = new UtilController();
		int n1 = 523578, n2 = 5, dec = 11, fib = 5;
		String texto = "teste";
		
		System.out.println(l3.quantidadeDigitos(12345, 1));
		
		System.out.println("");
		if(u3.validarNumeroComp(n1)) {
			if(u3.validarNumeroRep(n2)) {
				System.out.println(l3.repeticaoNumero(n1, n2));
			}
		}
		
		System.out.println("");
		System.out.println(l3.inversaoCaracteres(texto, texto.length() - 1));
		
		System.out.println("");
		if(u3.validarNumeroDec(dec)) System.out.println(l3.conversaoBinaria(dec));
		
		System.out.println("");
		System.out.println(l3.calcFib(0, 1, fib));
		
		System.out.println("");
		System.out.println(l3.somarSequenciaFatoriais(10));
	}
}
