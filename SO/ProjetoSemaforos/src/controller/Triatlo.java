package controller;

import java.util.concurrent.Semaphore;

public class Triatlo extends Thread {
	Semaphore armas;
	private int id, objetivo = 0;
	static int[][] pontuacao = new int[25][2]; 
	static String posicao = "";
	static int posicaoAtual = 1;
	

	public Triatlo(Semaphore armas, int id) {
		this.armas = armas;
		this.id = id;
	}
	
	@Override
	public void run() {
		corrida();
		tiroAlvos();
		ciclismo();
		ranking();
	}

	
	private void corrida() {
		// TODO Auto-generated method stub
		pontuacao[id][0] = id;
		try {
			do {
				objetivo += (int) (Math.random() * 25) + 20;
				sleep(30);
			} while(objetivo < 3000);
			objetivo = 0;
		} catch(Exception e) {
			
		}
		
	}

	private void tiroAlvos() {
		try {
			armas.acquire();
			do {
				
				pontuacao[id][1] = (int) (Math.random() * 10) + 1; 
				sleep((int) (Math.random() * 3000) + 500);
				objetivo++;
			} while(objetivo < 3);
		} catch(Exception e) {
			
		} finally{
			armas.release();
		}
		
	}

	private void ciclismo() {
		try {
			
			do {
				objetivo+= (int) (Math.random() * 25) + 20;
				sleep(40);
			} while(objetivo < 5);
			
			
			pontuacao[id][1] += (26 - posicaoAtual) * 10;
			posicao+= "Atleta" + id + " chegou em " + posicao + "º! \n";
			posicaoAtual++;
		} catch(Exception e) {
			
		}
		
	}

	private void ranking() {
		int i, j;
		int aux[] = new int[2];
		System.out.println("Ranking de chegada: \n" + posicao);
		
		
		for(i = 0; i < 25; i++) {
			for(j = 0; j < 25; j++) {
				if(pontuacao[1][i] > pontuacao[1][j]) {
					aux[0] = pontuacao[0][i];
					aux[1] = pontuacao[1][i];
					pontuacao[0][i] = pontuacao[0][j];
					pontuacao[1][i] = pontuacao[1][j];
					pontuacao[0][j] = aux[0];
					pontuacao[1][j] = aux[1];
				}
			}
		}
		
		System.out.println("Ranking de pontuação: ");
		for(i = 0; i < 25; i++) {
			System.out.println("Atleta nº " + pontuacao[0][i] + " ficou em " + i + "º com a pontuação " + pontuacao[1][i]);
		}
		
	}

	

}
