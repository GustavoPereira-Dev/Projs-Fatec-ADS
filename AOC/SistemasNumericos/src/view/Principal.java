package view;

import controller.ConversoesController;
import controller.OperacoesController;

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
	
		OperacoesController op = new OperacoesController();
		System.out.println(op.soma("11101101", "10010110"));
		System.out.println(op.subtracao("10000000", "100101"));
		System.out.println(op.multiplicacao("1001", "1001"));
		System.out.println(op.divisao("100011", "101"));
	
	}
}
