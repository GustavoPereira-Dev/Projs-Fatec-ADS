package lista2.view;

import java.io.IOException;

import lista2.controller.Lista2Controller;
import lista2.model.lista.ListaDupla;

public class Principal {
    public static void main(String[] args) {
    	Lista2Controller lc2 = new Lista2Controller();
    	
    	System.out.println("-----------------Exercicio 1-----------------");
    	lc2.listaX();
    	
    	System.out.println("-----------------Exercicio 2-----------------");
    	
    	System.out.println("--------Item A--------");
    	ListaDupla<Integer> lista1 = new ListaDupla<Integer>();
    	ListaDupla<Integer> lista2 = new ListaDupla<Integer>();
    	ListaDupla<Integer> mescla = new ListaDupla<Integer>();
    	lista1.append(1);
    	lista1.append(7);
    	
    	lista2.append(3);
    	lista2.append(4);
    	lista2.append(8);
    	
    	mescla.mesclar(lista1, lista2);
    	System.out.println(mescla.toString());
    	
    	System.out.println("--------Item B--------");
    	mescla.ordenar();
    	
    	System.out.println(mescla.toString());
        
    	System.out.println("-----------------Exercicio 3-----------------");
    	int[] vetor = {3, 5, 8, 12, 9, 7, 6, 2, 3, 7, 16};
    	ListaDupla<Integer> maiores = new ListaDupla<Integer>();
    	
    	for(int valor: vetor) {
    		maiores.append(valor);
    	}
    	
    	System.out.println("-----------------Exercicio 4-----------------");
    	lc2.pecorrendoVetor();
    	
    	System.out.println("-----------------Exercicio 5-----------------");
    	
    	System.out.println(maiores.doisMaiores());
    	
    	System.out.println("-----------------Exercicio 6-----------------");
    	try {
        	lc2.novoCadastro(41, 60, 8000.00);
        	lc2.novoCadastro(31, 40, 5000.00);
        	lc2.novoCadastro(21, 30, 3000.00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	System.out.println("Arquivos csvs criados");
    }
}
