package view;

import controller.NumerosController;

public class Numeros{
	public static void main(String args[]){
		NumerosController imp = new NumerosController();
		
		System.out.println("Resultado: " + imp.numeros(5));
	}
}