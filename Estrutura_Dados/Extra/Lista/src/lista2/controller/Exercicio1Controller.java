package lista2.controller;

import java.util.LinkedList;

public class Exercicio1Controller {
	
	public static void main(String[] args) {
		teste();
	}
	public static void teste() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		int[] vetor = {100, 200, 1, 50, 39, 44, 25, 16, 99, 45, 33, 18, 102, 92};
		int tamanhoVetor = vetor.length;

		for(int i = 0; i < tamanhoVetor; i++){
			if(!l.isEmpty()){
				l.addFirst(vetor[i] * 8);
			} else if(vetor[i] % 5 == 0 || l.size() > 10){
				l.addLast(vetor[i] + 10);
			} else if(l.size() >= 2){
				l.add(vetor[i] * 3, 1);
			} else{
				l.addFirst(vetor[i]);
			}
		}
		
		
		int tamanho = l.size();
		while(!l.isEmpty()){
			if(tamanho > 10){
				System.out.println(l.get(0));
				l.removeFirst();
			} else if(tamanho > 1){
				System.out.println(l.get(tamanho - 1));
				l.removeLast();		
			} else{
				System.out.println(l.get(0));
				l.removeFirst();
			}
			tamanho = l.size();
		}
		
	}
}
