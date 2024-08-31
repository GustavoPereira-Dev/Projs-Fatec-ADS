package controller;

public class CorridaSapos extends Thread {
	final int tamanhoPista = 150;
	int posicaoSapo;
	int puloSapo;
	
	public CorridaSapos(int puloSapo) {
		this.puloSapo = puloSapo;
	}
	@Override
	public void run() {
		pular();
	}
	
	private void pular() {
		do {
			posicaoSapo += (int)((Math.random() * puloSapo) + 1);
		} while(posicaoSapo < tamanhoPista);
		
		System.out.println( " #" + getId() + " chegou agora!");
		
	}

}
