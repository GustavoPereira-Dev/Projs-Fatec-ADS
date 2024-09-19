package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Corredor extends Thread{
	private Semaphore semaforo;
	private int id;
	private int distanciaPecorrida = 0;
	
	private Random r = new Random();
	private static final int distanciaCorredor = 200;
	
	public Corredor(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			corredor();
			semaforo.acquire();
			abrirPorta();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void corredor() throws InterruptedException {
		do {
			distanciaPecorrida += r.nextInt(4,6);
			sleep(1000);
			System.out.println("Corredor Nº " + id + " pecorreu " + distanciaPecorrida + " metros");
		} while(distanciaPecorrida < distanciaCorredor);
		
		System.out.println("Corredor Nº " + id + " finalizou o corredor");
	}

	private void abrirPorta() throws InterruptedException {
		System.out.println("Corredor Nº " + id + " está abrindo e cruzando a porta");
		sleep(((int) (Math.random() * 2) + 1) * 1000);
		System.out.println("Corredor Nº " + id + " cruzou a porta!");
	
	}
}

/*2 - Quatro pessoas caminham, cada uma em um corredor diferente. Os 4 corredores terminam
em uma única porta. Apenas 1 pessoa pode cruzar a porta, por vez. Considere que cada
corredor tem 200m. e cada pessoa anda de 4 a 6 m/s. Cada pessoa leva de 1 a 2 segundos
para abrir e cruzar a porta. Faça uma aplicação em java que simule essa situação.*/