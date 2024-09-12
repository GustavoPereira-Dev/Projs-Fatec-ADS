package controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread {
	private Semaphore semaforo;
	private int id = (int) threadId();
	
	public Cozinha(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}
	
	@Override
	public void run() {
		try{
			cozinhar();
		} catch(Exception e) {
			
		} finally {

		}
	}

	private void cozinhar() {
		double cozimentoMin, cozimentoMax;
		if(id % 2 != 0) {
			cozimentoMin = 0.5;
			cozimentoMax = 0.8;
		} else {
			cozimentoMin = 0.6;
			cozimentoMax = 1.2;
		}
		long tempoTotal =  (long) ((cozimentoMin + (cozimentoMax - cozimentoMin) * Math.random()) * 1000);
		
		System.out.println("Prato " +id + " Começou a ser feito");
		long tempo = 0;
		try { 
			do {
			
				sleep(100);
				int percentual = (int) ((tempo / (double) tempoTotal) * 100);
				System.out.println("Prato " + id + ": " + "Porcentagem restante de cozimento: " + percentual + "%");
			
			tempo += 100;
			
			} while(tempo < tempoTotal);
			
			semaforo.acquire();
			System.out.println("Prato " + id + " iniciando a entrega...");
			sleep(500);
			System.out.println("Prato " + id + ": entrega concluída!");
			
		
		
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
		
		
		
	}
}

