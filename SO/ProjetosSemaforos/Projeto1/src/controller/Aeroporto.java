package controller;

import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread {
	private Semaphore[] afastamento;
	private Semaphore[] decolagem;
	private int pista; // 0 = pista norte; 1 = pista sul
	private int id;
	static final String[] pistas = {"norte","sul"};

	public Aeroporto(Semaphore[] afastamento, Semaphore[] decolagem, int pista, int id) {
		super();
		this.afastamento = afastamento;
		this.decolagem = decolagem;
		this.pista = pista;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			manobragem();
			taxiar();
			decolagem();
			afastar();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			afastamento[pista].release();
			decolagem[pista].release();
		}
		
		
	}

	private void manobragem() {
		try {
			System.out.println("Avião " + id + " está fazendo manobragem na pista " + pistas[pista] + "...");
			sleep(((int) (Math.random() * 7) + 3) * 100);
			System.out.println("Avião " + id + " concluiu a manobragem na pista " + pistas[pista] + "...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	private void taxiar() {
		try {
			System.out.println("Avião " + id + " está fazendo taxiagem na pista " + pistas[pista] + "...");
			sleep(((int) (Math.random() * 10) + 5) * 100);
			System.out.println("Avião " + id + " concluiu a taxiagem na pista " + pistas[pista] + "...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void decolagem() {
		try {
			decolagem[pista].acquire();
			System.out.println("Avião " + id + " está fazendo decolagem na pista " + pistas[pista] + "...");
			sleep(((int) (Math.random() * 8) + 6) * 100);
			System.out.println("Avião " + id + " concluiu a decolagem na pista " + pistas[pista] + "...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void afastar() {
		try {
			afastamento[pista].acquire();
			System.out.println("Avião " + id + " está fazendo afastamento na pista " + pistas[pista] + "...");
			sleep(((int) (Math.random() * 3) + 8) * 100);
			System.out.println("Avião " + id + " concluiu a decolagem na pista " + pistas[pista] + "...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
