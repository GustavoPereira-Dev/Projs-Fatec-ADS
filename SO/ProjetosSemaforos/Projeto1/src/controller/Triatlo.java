package controller;

import java.util.concurrent.Semaphore;

public class Triatlo extends Thread {
	Semaphore armas;
	private int id;
	
	private int progressoPecorrido = 0;
	private int objetivo = 0;
	private int pontosTiro;
	
	static int[][] pontuacao = new int[2][25]; 
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
	}

	
	private void corrida() {
		// TODO Auto-generated method stub
		pontuacao[0][id] = id;
		objetivo = 3000;
		try {
			System.out.println("Atleta " + (id + 1) + " iniciou a corrida");
			do {
				progressoPecorrido += (int) (Math.random() * 25) + 20;
				System.out.println("Atleta " + (id + 1) + " pecorreu " + progressoPecorrido + " metros");
				sleep(30);
			} while(progressoPecorrido < objetivo);
			System.out.println("Atleta " + (id + 1) + " finalizou a corrida");
			progressoPecorrido = 0;
			objetivo = 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void tiroAlvos() {
		try {
			System.out.println("Atleta " + (id + 1) + " entrou no tiro ao alvos");
			armas.acquire();
			objetivo = 3;
			System.out.println("Atleta " + (id + 1) + " pegou a arma");
			do {
				
				pontosTiro = (int) (Math.random() * 10) + 1;
				pontuacao[1][id] += pontosTiro; 
				sleep((int) (Math.random() * 3000) + 500);
				progressoPecorrido++;
				System.out.println("Atleta " + (id + 1) + " mirou no " + progressoPecorrido + "º alvo e ganhou " + pontosTiro + " pontos (tendo " + pontuacao[1][id] + " pontos até agora");
			} while(progressoPecorrido < objetivo);
			System.out.println("Atleta " + (id + 1) + " finalizou o tiro ao alvo");
			progressoPecorrido = 0;
			objetivo = 0;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			armas.release();
		}
		
	}

	private void ciclismo() {
		try {
			objetivo = 5000;
			System.out.println("Atleta " + (id + 1) + " entrou no ciclismo");
			do {
				progressoPecorrido+= (int) (Math.random() * 25) + 20;
				System.out.println("Atleta " + (id + 1) + " pecorreu " + progressoPecorrido + " metros");
				sleep(40);
			} while(progressoPecorrido < objetivo);
			System.out.println("Atleta " + (id + 1) + " finalizou o ciclismo e o trajeto inteiro!");
			pontuacao[1][id] += (26 - posicaoAtual) * 10;
			posicao+= "Atleta " + (id + 1) + " chegou em " + posicaoAtual + "º! \n";
			posicaoAtual++;
			if(posicaoAtual == 26) ranking();

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void ranking() throws InterruptedException {
		int i, j;
		int aux[] = new int[2];
		System.out.println("Ranking de chegada: \n" + posicao);
		
		sleep(5000);
		
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
			System.out.println("Atleta nº " + (pontuacao[0][i] + 1) + " ficou em " + (i + 1) + "º com a pontuação " + pontuacao[1][i]);
		}
		
	}
}