package lista3.controller;

public class UtilController {
	public boolean validarNumeroComp(int n) {
		return n >= 10 && n <= 999999 ? true : false;
	}
	
	public boolean validarNumeroRep(int n) {
		return n >= 1 && n <= 9 ? true : false;
	}
	
	public boolean validarNumeroDec(int n) {
		return n >= 0 && n <= 2000 ? true : false;
	}
	
	public boolean validarFibonacci(int n) {
		return n >= 0 && n <= 20 ? true : false;
	}
	
}
