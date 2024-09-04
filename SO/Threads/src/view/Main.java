package view;

import controller.CincoThreadsController;
import controller.CorridaSapos;
import controller.PingThread;
import controller.SomaVetoresController;
import controller.ThreadVetor;

public class Main {

	public static void main(String[] args) {

		
		int i, j;
		System.out.println("--------------------------------Exercício 1----------------------------------------");
		
		// 1
		for(i = 0; i < 5; i++) {
			CincoThreadsController cincoTh = new CincoThreadsController();
			cincoTh.start();
		}
		
		
		
		// 2
		System.out.println("--------------------------------Exercício 2----------------------------------------");
		int[][] mt = new int[3][5];
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 5; j++) {
				mt[i][j] = (int)((Math.random() * 60) + 1);
			}
			SomaVetoresController svt = new SomaVetoresController(mt[i]);
			svt.start();
		}
		
		
		System.out.println("--------------------------------Exercício 3----------------------------------------");
		// 3
		int[] vt = new int[1000];
		for(i = 0; i < 2; i++) {
			ThreadVetor threadVetor = new ThreadVetor(i + 1, vt);
			threadVetor.start();
		}
		
		
		
		System.out.println("--------------------------------Exercício 5----------------------------------------");
		// 5
		String[][] sites = {{"UOL", "www.uol.com.br"}, {"Terra", "www.terra.com.br"}, {"Google","www.google.com.br"}};
		for(i = 0; i < 3; i++) {
			PingThread pingThread = new PingThread(sites[i][0],sites[i][1]);
			pingThread.start();
		}
		
		
		System.out.println("--------------------------------Exercício 4----------------------------------------");
		// 4
		int sapo1, sapo2, sapo3, sapo4, sapo5;
		for(i = 0; i < 10; i++) {
			
			try {
				Thread.sleep(500);
				sapo1 = (int) (Math.random() * 5) + 1;
				sapo2 = (int) (Math.random() * 5) + 1;
				sapo3 = (int) (Math.random() * 5) + 1;
				sapo4 = (int) (Math.random() * 5) + 1;
				sapo5 = (int) (Math.random() * 5) + 1;
				
				CorridaSapos corrida = new CorridaSapos(sapo1,sapo2,sapo3,sapo4,sapo5);
				corrida.start();
			} catch(Exception e) {
				
			}
			
		}
		
	}
	
	
	

}
