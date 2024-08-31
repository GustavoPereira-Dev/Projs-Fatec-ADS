package controller;

public class ThreadVetor extends Thread {
	int formato;
	int[] vt;
	public ThreadVetor(int formato, int[] vt) {
		this.formato = formato;
		this.vt = vt;
	}
	@Override
	public void run() {
		pecorrerVetor();
	}
	
	public void pecorrerVetor() {
		double tempoInicial = System.nanoTime();
		int i;
		if(formato % 2 != 0) {
			for(int j : vt);
		} else {
			for(i = 0; i < vt.length; i++);
		}
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		tempoTotal /= Math.pow(10, 9);
		System.out.println(tempoTotal);
	}
}
