package controller;

public class SomaVetoresController extends Thread {
	private int[] vet;
	public SomaVetoresController(int[] vet) {
		this.vet = vet;
	}
	@Override
	public void run() {
		soma();
	}
	
	public void soma() {
		int contador = 0;
		for(int i = 0; i < 5; i++) {
			contador += vet[i];
		}
		System.out.println(contador + " #" + getId());
		
	}
	
}
