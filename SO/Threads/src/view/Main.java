package view;

import controller.CincoThreadsController;
import controller.CorridaSapos;
import controller.PingThread;
import controller.SomaVetoresController;
import controller.ThreadVetor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i, j;
		// 1
		for(i = 0; i < 5; i++) {
			CincoThreadsController cincoTh = new CincoThreadsController();
			cincoTh.start();
		}
		
		// 2
		int[][] mt = new int[3][5];
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 5; j++) {
				mt[i][j] = (int)((Math.random() * 60) + 1);
			}
			SomaVetoresController svt = new SomaVetoresController(mt[i]);
			svt.start();
		}
		
		
		// 3
		int[] vt = new int[1000];
		for(i = 0; i < 2; i++) {
			ThreadVetor threadVetor = new ThreadVetor(i + 1, vt);
			threadVetor.start();
		}
		
		
		// 4
		for(i = 0; i < 5; i++) {
			CorridaSapos cs = new CorridaSapos((int)((Math.random() * 20) + 1));
			cs.start();
		}
		
		// 5
		String[][] sites = {{"UOL", "www.uol.com.br"}, {"Terra", "www.terra.com.br"}, {"Google","www.google.com.br"}};
		for(i = 0; i < 3; i++) {
			PingThread pingThread = new PingThread(sites[i][0],sites[i][1]);
			pingThread.start();
		}
		
	}
	
	
	

}
