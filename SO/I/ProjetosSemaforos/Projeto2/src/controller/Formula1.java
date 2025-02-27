package controller;

import java.util.concurrent.Semaphore;

public class Formula1 extends Thread{
	
	private Semaphore pista; // 5 carros simultaneamnete na pista
	private Semaphore[] equipe = new Semaphore[5];
	private int escudeira;
	private int id;
	private int distanciaPecorrida = 0;
	private int tempoVolta = 0;
	private int voltaAtual = 1;
	private int[] tempoVoltas = new int[3];
	
	static private int[][] melhoresVoltas = new int[2][14];
	static int contConcluidos = 0;
	final String[] nomesEquipes = {"Ferrari","McLaren","Mercedes","Red Bull", "Alpine","Aston","Willians"};
	
	public Formula1(Semaphore pista, Semaphore[] equipe, int escudeira, int id) {
		this.pista = pista;
		this.equipe = equipe;
		this.escudeira = escudeira;
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			equipe[escudeira].acquire();
			pista.acquire();
			System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") entrou na pista");
			corrida();
			melhorVolta();
			sleep(1000);
			System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") teve sua melhor volta com " + (melhoresVoltas[1][id] / 100) + " segundos!");
			contConcluidos++;
			if(contConcluidos == 14) rankingVolta();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			equipe[escudeira].release();
			pista.release();
		}
	}

	private void corrida() throws InterruptedException {
		do {
			distanciaPecorrida += (int) (Math.random() * 100) + 10;
			System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") pecorreu " + distanciaPecorrida + " metros até agora");
			sleep(500);
			tempoVolta+=500;
			if(distanciaPecorrida > (voltaAtual * 1000)) {
				System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") pecorreu a " + voltaAtual + "º volta em " + (tempoVolta / 100) + " segundos");
				tempoVoltas[voltaAtual - 1] = tempoVolta;
				tempoVolta = 0;
				voltaAtual++;
				if(voltaAtual < 4) System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") está na " + voltaAtual + "º volta agora");
			}
		} while(distanciaPecorrida < 3000);
		System.out.println("O piloto nº " + (id+1) + " (" + nomesEquipes[escudeira] + ") finalizou a corrida agora");
		sleep(1000);
	}

	private void melhorVolta() {
		int i = 0, melhorVolta = 0;
		for(i = 0; i < 3; i++) {
			if(i == 0) {
				melhorVolta = tempoVoltas[i];
			} else if(tempoVoltas[i] < melhorVolta) {
				melhorVolta = tempoVoltas[i];
			}
		}
		
		melhoresVoltas[0][id] = id;
		melhoresVoltas[1][id] = melhorVolta;
		
	}

	private void rankingVolta() {
		int i, j;
		int aux[] = new int[2];
		
		//sleep(5000);
		
		for(i = 0; i < 14; i++) {
			for(j = 0; j < 14; j++) {
				if(melhoresVoltas[1][i] < melhoresVoltas[1][j]) {
					aux[0] = melhoresVoltas[0][i];
					aux[1] = melhoresVoltas[1][i];
					melhoresVoltas[0][i] = melhoresVoltas[0][j];
					melhoresVoltas[1][i] = melhoresVoltas[1][j];
					melhoresVoltas[0][j] = aux[0];
					melhoresVoltas[1][j] = aux[1];
				}
			}
		}
		
		System.out.println("Ranking de pontuação: ");
		for(i = 0; i < 14; i++) {
			System.out.println("Piloto nº " + (melhoresVoltas[0][i] + 1) + " ficou em " + (i + 1) + "º com a sua melhor volta em " + (melhoresVoltas[1][i] / 100) + " segundos");
		}
	}
	
	
}

/*Você foi contratado para automatizar um treino de Fórmula 1. As regras estabelecidas pela
direção da provas são simples:
“No máximo 5 carros das 7 escuderias[equipe] (Cada escuderia tem 2 carros diferentes,
portanto, 14 carros no total) presentes podem entrar na pista simultaneamente, mas apenas
um carro de cada equipe. O segundo carro deve ficar à espera, caso um companheiro de
equipe já esteja na pista. Cada piloto deve dar 3 voltas na pista. O tempo de cada volta deverá
ser exibido e a volta mais rápida de cada piloto deve ser armazenada para, ao final, exibir o
grid de largada, ordenado do menor tempo para o maior.”*/