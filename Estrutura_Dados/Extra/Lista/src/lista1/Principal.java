package lista1;

import java.util.LinkedList;

public class Principal {
	

	public static void main(String[] args) {
		System.out.println("Lista A");
		System.out.println(listaA());
		
		System.out.println("Lista B");
		System.out.println(listaB());
	}
	
	public static String listaA() {
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
	
	public static String listaB() {
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
	
}	

