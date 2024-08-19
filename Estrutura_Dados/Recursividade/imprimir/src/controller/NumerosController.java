package controller;

public class NumerosController{
	
	public NumerosController(){
		super();
	}
	
	public int numeros(int n){
		if(n >= 7) return 1;
		return n + numeros(++n);
	}
}