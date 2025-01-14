package lista1.controller;

import java.util.LinkedList;

import lista1.model.listaencadeada.ListaSimples;

public class Lista1Controller {
	
	// Exercicio 1
	public String listaA() {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		for(int i = 0; i < 10; i++){
			if(i % 2 == 0){
				lista.addFirst(i * i);
			} else if(i <= 6){
				lista.addFirst(i);
			} else{
				System.out.println(lista.get(lista.size() - 1));
				lista.removeLast();
			}
			System.out.println(lista.size());
		}
		
		return lista.toString();
	}
	
	public String listaB() {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		for(int i = 0; i < 115; i++){
			if(lista.isEmpty()){
				lista.addFirst(i + 100);
			} else if(lista.size() <= 4){
				lista.addFirst(i + 50);
			} else{
				System.out.println(lista.get(0));
				lista.removeFirst();
			}
			System.out.println(lista.size());
		}
		return lista.toString();
	}
	
	
	// Exercicio 3
	public void reverterLista() {
		int[] vetor = {3, 5, 18, 12, 9, 7, 6, 2, 13, 4, 16};
		ListaSimples<Integer> l = new ListaSimples<Integer>();

		// Adicionando elementos 
		for(int e: vetor) {
			l.append(e);
		}
	
		l.reverse();
		
	}
}
