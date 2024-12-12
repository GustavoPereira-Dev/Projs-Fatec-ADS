package view;

import controller.ConversoesController;

public class Principal {
	public static void main(String[] args) {
		ConversoesController cs = new ConversoesController();
		System.out.println(cs.decimalParaBinario(50));
		System.out.println(cs.binarioParaDecimal("110010"));
		System.out.println(cs.decimalParaOctal(71));
		System.out.println(cs.octalParaDecimal(107));
		System.out.println(cs.decimalParaHexadecimal(1021));
		System.out.println(cs.hexadecimalParaDecimal("3fd"));
		System.out.println(cs.octalParaHexadecimal(127));
	
		
	}
}
