package controller;

import java.util.concurrent.Semaphore;

public class Servidor extends Thread {
	private Semaphore semaforo;
	private int threadId;
	private int tempoMinimo;
	private int tempoMaximo;
	private int diferencial;
	private int vezesExecucoes;
	
	public Servidor(int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
	}
	
	public void run() {
		int i;
		verificarIntervalos();
		System.out.println("Thread " + threadId + " iniciou os processos");
		for(i = 0; i < vezesExecucoes; i++) {
			executarProcessos();
		}
		System.out.println("Fim da execução da Thread " + threadId);
	}

	private void verificarIntervalos() {
		if(threadId % 3 == 1) {
			tempoMinimo = 200;
			tempoMaximo = 1000;
			vezesExecucoes = 2;
		} else if(threadId % 3 == 2){
			tempoMinimo = 500;
			tempoMaximo = 1500;
			vezesExecucoes = 3;
		} else {
			tempoMinimo = 1000;
			diferencial = 1500;
			tempoMaximo = 2000;
			vezesExecucoes = 3;
		}
		
		if(threadId % 3 > 0) diferencial = tempoMaximo;
		
	}
	
	private void executarProcessos() {
		try {
			System.out.println("Thread " + threadId + ": Calculando...");
			Thread.sleep((int) (Math.random() * tempoMaximo) + tempoMinimo);
			semaforo.acquire();
			System.out.println("Thread " + threadId + ": Fazendo transação do BD...");
			Thread.sleep(diferencial);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
	
}
