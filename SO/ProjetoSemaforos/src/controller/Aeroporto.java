package controller;

import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread {
	private Semaphore quantAvioes;
	private Semaphore[] decolagem;
	private int pista;
	private int id;
	
	public Aeroporto(Semaphore quantAvioes, Semaphore[] decolagem) {
		this.quantAvioes = quantAvioes;
		this.decolagem = decolagem;
	}
	
	
	
	@Override
	public void run() {
		try {
			manobragem();
			taxiar();
			decolagem();
			afastar();
		} catch(Exception e) {
			
		} finally {
			quantAvioes.release();
			decolagem[0].release();
			decolagem[1].release();
		}
		
		
	}



	private void manobragem() {
		// TODO Auto-generated method stub
		try {
			pista = (int) (Math.random() * 1);
			quantAvioes.acquire();
			System.out.println("Fazendo manobragem...");
			sleep((int) (Math.random() * 700) + 300);
		} catch(Exception e) {
			
		}
	}



	private void taxiar() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Fazendo taxiagem...");
			sleep((int) (Math.random() * 1000) + 500);
		} catch(Exception e) {
			
		}
	}



	private void decolagem() {
		// TODO Auto-generated method stub
		try {
			decolagem[pista].acquire();
			System.out.println("Fazendo decolagem...");
			sleep((int) (Math.random() * 800) + 600);
		} catch(Exception e) {
			
		}
	}



	private void afastar() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Fazendo afastamento...");
			sleep((int) (Math.random() * 800) + 600);
		} catch(Exception e) {
			
		}
	}
	
}
