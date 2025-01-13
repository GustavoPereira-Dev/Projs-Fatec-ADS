package lista2.controller;

import lista2.model.lista.ListaDupla;
import lista2.model.lista.No;

public class Exercicio4Controller {
	public ListaDupla<Integer> pecorrendoVetor(int[] vetor) {
		ListaDupla<Integer> listaVetor = new ListaDupla<Integer>(); 
		
		for(int valor: vetor) {
			
			if(listaVetor.getTotal() == 0) {
				listaVetor.prepend(valor * 2);
			} else if (listaVetor.getTotal() < 3) {
				listaVetor.append(valor / 2);
			} else if(listaVetor.getTotal() > 10) {
				listaVetor.insert(5, valor * 3);
			} else {
				listaVetor.insert(1, valor);
			}
			
		}
		
		int posicao;
		
		while(listaVetor.getTotal() != 0) {
			
			if(listaVetor.getTotal() > 10) {
				posicao = 3;
			} else if(listaVetor.getTotal() > 5) {
				posicao = listaVetor.getTotal();
			} else if(listaVetor.getTotal() > 3) {
				posicao = 1;
			} else {
				posicao = 0;
			}
			
			System.out.println(listaVetor.get(posicao).toString());
			listaVetor.remove(posicao);
		}
		
		return listaVetor;
	}
}
