package lista1;

import java.util.LinkedList;

import lista1.controller.Lista1Controller;
import lista1.model.listaencadeada.ListaSimples;

public class Principal {
	

	public static void main(String[] args) {
		Lista1Controller lc1 = new Lista1Controller();
		
		System.out.println("-----------------Exercicio 1-----------------");
		
		System.out.println("--------Lista A--------");
		lc1.listaA();
		System.out.println("--------Lista B--------");
		lc1.listaB();
		
		System.out.println("-----------------Exercicio 2-----------------");
		
		ListaSimples<String> lista1 = new ListaSimples<String>();
		lista1.append("Item 1");
		lista1.append("Item 0");
		lista1.append("Item 2");
		lista1.append("Item 3");
		lista1.append("Item 4");
		lista1.append("Item 5");
		lista1.append("Item 6");
		lista1.insert(3, "Item 3.1");
		lista1.remove(1);
		System.out.println("Index do Item 3: " + lista1.index("Item 3"));
		System.out.println(lista1.toString());
		
		System.out.println("-----------------Exercicio 3-----------------");
		int[] vetor = {3, 5, 18, 12, 9, 7, 6, 2, 13, 4, 16};
		ListaSimples<Integer> lista2 = new ListaSimples<Integer>();
		
		for(int valor: vetor) {
			lista2.append(valor);
		}
		
		System.out.println(lista2.toString());
		lista2.reverse();
		System.out.println(lista2.toString());
		
		
	}
	
	
	
}	

