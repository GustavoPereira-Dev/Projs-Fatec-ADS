package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cruzamento extends Thread{
	Semaphore semaforo;
	private int id = (int) threadId();
	private String sentido;
	private boolean estaCruzando;
	
	static Random rn = new Random();
	static private String sentidoAtual;
	static final private String[] sentidos = {"leste","sul","oeste","norte"};
	
	
	public Cruzamento(Semaphore semaforo, String sentido) {
		this.semaforo = semaforo;
		this.sentido = sentido;
	}
	
	@Override
	public void run() {
		cruzamento();
	}

	private void cruzamento() {
		// TODO Auto-generated method stub
		do {
			
		
			try {
				
				
				//sleep(1000);
				semaforo.acquire();
				sentidoAtual = sentido;
				System.out.println(sentidoAtual);
				if(sentidoAtual == sentido) {
					estaCruzando = true;
					System.out.println("O carro " + id + " está passando o cruzamento e indo pelo sentido " + sentido + " " + sentidoAtual);
					sleep(500);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(estaCruzando) {
					semaforo.release();
					System.out.println("O carro " + id + " passou o cruzamento pelo sentido " + sentido + " " + sentidoAtual);
				}
			}
		} while(!estaCruzando);
		System.out.println("Saindo");
	}
	
	
}

/*
 * 1 - Fazer uma aplicação, console, que gerencie a figura abaixo:
Para tal, usar uma variável sentido, que será alterado pela Thread que controla cada carro
com a movimentação do carro. Quando a Thread tiver a possibilidade de ser executada, ela
deve imprimir em console o sentido que o carro está passando. Só pode passar um carro por
vez no cruzamento. Usar threadId() ou getId() para identificar os carros.
*/
 