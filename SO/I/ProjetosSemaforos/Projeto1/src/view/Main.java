package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.Aeroporto;
import controller.Cavaleiros;
import controller.Triatlo;

public class Main {
	public static void main(String args[]) {
		Random r = new Random();
		int i;
		
		
		// 1
		System.out.println("---------------------------------Exercício 1---------------------------------");
		Semaphore porta = new Semaphore(1);
		
		
		for(i = 1; i <= 4; i++) {
			Cavaleiros c = new Cavaleiros(porta,i);
			c.start();
		}
		
		
		try {
			Thread.sleep(45000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 2
		
		System.out.println("---------------------------------Exercício 2---------------------------------");
		// 0 = pista norte; 1 = pista sul
		Semaphore[] afastamento = new Semaphore[2];
		Semaphore[] decolagem = new Semaphore[2];
		
		
		for(i = 0; i < 2; i++) {
			afastamento[i] = new Semaphore(1);
			decolagem[i] = new Semaphore(2);
		}
		
		for(i = 1; i <= 12; i++) {
			Aeroporto a = new Aeroporto(afastamento, decolagem,r.nextInt(1,100) % 2, i);
			a.start();
		}
		
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3
		System.out.println("---------------------------------Exercício 3 ---------------------------------");
		Semaphore armas = new Semaphore(5);
		
		for(i = 0; i < 25; i++) {
			Triatlo t = new Triatlo(armas, i);
			t.start();
		}
		
	}
}
