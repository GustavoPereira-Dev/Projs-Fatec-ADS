package controller;

import java.util.concurrent.Semaphore;

public class Cavaleiros extends Thread{

	private Semaphore porta;
	private int distancia;
	private int id;
	private boolean tochaPega;
	
	private static boolean tochaColetada;
	private static boolean pedraColetada;
	private static boolean[] portasAbertas = new boolean[4];
	private static boolean[] semMonstro = {false,false,true,false};
	
	public Cavaleiros(Semaphore porta,int id) {
		this.porta = porta;
		this.id = id;
	}

	@Override
	public void run() {
		corrida();
	}

	private void corrida() {
		int distanciaMinima = 2, distanciaMaxima = 4;
		try {
			do {
				
				if(!tochaColetada && distancia <= 500) {
					tochaColetada = true;
					tochaPega = true;
					System.out.println("Cavaleiro " + id + " pegou a tocha!");
					distanciaMinima+=2;
					distanciaMaxima+=2;
				} 
				
				if(!pedraColetada && distancia >= 1500 && !tochaPega) {
					pedraColetada = true;
					System.out.println("Cavaleiro " + id + " pegou a pedra!");
					distanciaMinima+=2;
					distanciaMaxima+=2;
				}
				
				distancia += (int) (Math.random() * distanciaMaxima) + distanciaMinima;
				sleep(50);
				
				System.out.println("Caveleiro " + id + " está com " + distancia + " metros pecorridos");
			} while(distancia < 2000);
			System.out.println("Cavaleiro " + id + " terminou a corrida, agora chega a escolha das portas...");
			entrarPorta();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			porta.release();
		}
		
	}

	private void entrarPorta() throws InterruptedException {
		int portaEscolhida;
		porta.acquire();
		do {
			portaEscolhida = (int) (Math.random() * 4);
			
		} while(portasAbertas[portaEscolhida]);
		
		System.out.println(portaEscolhida);
		portasAbertas[portaEscolhida] = true;
		if(semMonstro[portaEscolhida]) {
			System.out.println("Cavaleiro " + id + " ganhou a corrida abrindo a porta correta!" );
		} else {
			System.out.println("Eita... Cavaleiro " + id + " perdeu a corrida abrindo a porta " + portaEscolhida + " com monstros..." );
		}
		
	}
	
}

