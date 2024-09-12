package view;

import java.util.concurrent.Semaphore;

import controller.Cozinha;
import controller.Servidor;

public class Main {

	public static void main(String[] args) {
		
		int i;
		Semaphore semaforo = new Semaphore(1);
		
		
		System.out.println("------------------------------------Exercício 2 ------------------------------------");
		// 2
		for(i = 1; i <= 5; i++) {
			try {
				Cozinha c = new Cozinha(semaforo,i);
				c.start();
				Thread.sleep(500);
			} catch(Exception e) {
						
			}
				
		}

		
		try {
			Thread.sleep(8000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("------------------------------------Exercício 1------------------------------------");
//		1
		for(i = 1; i <= 21; i++) {
			Servidor s = new Servidor(i,semaforo);
			s.start();
		}
	
	}

}
