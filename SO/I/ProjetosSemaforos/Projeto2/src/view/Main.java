package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.Banco;
import controller.Corredor;
import controller.Cruzamento;
import controller.Formula1;

public class Main {
	
	public static void main(String[] args) {
		Random r = new Random();
		String tipoAcao;
		String[] transacoes = {"Saque","Depósito"};
		Semaphore semaforo = new Semaphore(1);
		int i;

		
		// 2
//		for(i = 1; i <= 4; i++) {
//			Corredor cd = new Corredor(semaforo,i);
//			cd.start();
//		}
		
		
		
		
		
		// 3
//		Semaphore transacao = new Semaphore(2);
//		
//		for(i = 1; i <= 20; i++) {
//			tipoAcao = transacoes[r.nextInt(0,100) % 2];
//			Banco b = new Banco(semaforo, semaforo, transacao,tipoAcao,i,r.nextInt(1,100),r.nextDouble(200,1000),r.nextDouble(30,200));
//			b.start();
//		}
		
//		public Formula1(Semaphore pista, Semaphore[] equipe, int escudeira, int id) {
		
		Semaphore pista = new Semaphore(5);
		Semaphore equipes[] = new Semaphore[7];
		
		for(i = 0; i < 7; i++) {
			equipes[i] = new Semaphore(1);
		}
		
		int cont = 0;
		for(i = 0; i < 14; i++) {
			if(i % 2 == 0) cont++;
			Formula1 f = new Formula1(pista,equipes,cont - 1,i);
			f.start();
		}
		
	}
}
