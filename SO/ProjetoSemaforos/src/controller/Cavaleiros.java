package controller;

import java.util.concurrent.Semaphore;

public class Cavaleiros extends Thread{

	private Semaphore tocha;
	private Semaphore pedra;
	private Semaphore[] portas;
	private boolean[] semMonstro = new boolean[4];
	private int distancia;
	private int id;
	
	private boolean tochaPega;
	
	
	
	public Cavaleiros(Semaphore tocha, Semaphore pedra, Semaphore[] portas,int id) {
		this.tocha = tocha;
		this.pedra = pedra;
		this.portas = portas;
		this.id = id;
	}

	@Override
	public void run() {
		semMonstro[(int) (Math.random()* 4) + 1] = true;
		corrida();
	}

	private void corrida() {
		int distanciaMinima = 2, distanciaMaxima = 4;
		try {
			do {
				if(tocha.tryAcquire()) {
					tochaPega = true;
					distanciaMinima+=2;
					distanciaMaxima+=2;
				} 
				
				if(pedra.tryAcquire() && distancia >= 1500 && !tochaPega) {
					distanciaMinima+=2;
					distanciaMaxima+=2;
				}
				distancia += (int) (Math.random() * distanciaMaxima) + distanciaMinima;
				sleep(50);
				
			} while(distancia < 2000);
			
			entrarPorta();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			tocha.release();
			pedra.release();
			portas[0].release();
			portas[1].release();
			portas[2].release();
			portas[3].release();
		}
		
		
		
		
	}

	private void entrarPorta() throws InterruptedException {
		int portaEscolhida;
		do {
			portaEscolhida = (int) (Math.random()* 4) + 1;
		} while(!portas[portaEscolhida].tryAcquire());

		if(semMonstro[portaEscolhida]) {
			System.out.println("Cavaleiro " + id + " ganhou a corrida abrindo a porta correta!" );
		} else {
			System.out.println("Eita... Cavaleiro " + id + " perdeu a corrida abrindo a porta com monstro..." );
		}
	}
	
}

