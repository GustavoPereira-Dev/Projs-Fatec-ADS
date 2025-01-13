package lista1.controller;

import lista1.model.listaencadeada.ListaSimples;
import lista1.model.pilha.Pilha;

public class ReverterListaController {
	public void teste() {
		int[] vetor = {3, 5, 18, 12, 9, 7, 6, 2, 13, 4, 16};
		ListaSimples<Integer> l = new ListaSimples<Integer>();

		// Adicionando elementos 
		for(int e: vetor) {
			l.append(e);
		}
	
		l.reverse();
		
	}
}
