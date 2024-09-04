package controller;

public class CorridaSapos extends Thread{
	private int puloS1;
	private int puloS2;
	private int puloS3;
	private int puloS4;
	private int puloS5;
	
	private static int sapo1, sapo2, sapo3, sapo4, sapo5, volta;

	public CorridaSapos(int puloS1, int puloS2, int puloS3, int puloS4, int puloS5) {
		this.puloS1 = puloS1;
		this.puloS2 = puloS2;
		this.puloS3 = puloS3;
		this.puloS4 = puloS4;
		this.puloS5 = puloS5;
	}
	
	
	public void run() {
		corrida();
	}
	
	public void corrida() {
		volta++;
		sapo1+=puloS1;
		sapo2+=puloS2;
		sapo3+=puloS3;
		sapo4+=puloS4;
		sapo5+=puloS5;
		
		if(volta == 1) {
			System.out.println("Ei amigos da rede Fatec, a corrida irá começar neste exato momento! \n"
					+ "Sapo1 inicia dando um pulo de " + puloS1 + " metros! \n"
					+ "Sapo2 inicia dando um pulo de " + puloS2 + " metros! \n"
					+ "Sapo3 inicia dando um pulo de " + puloS3 + " metros! \n"
					+ "Sapo4 inicia dando um pulo de " + puloS4 + " metros! \n"
					+ "Sapo5 inicia dando um pulo de " + puloS5 + " metros! \n");
		} else if(volta < 10) {
			System.out.println("Vamos no placar de agora: \n"
					+ "Sapo1 deu um pulo de " + puloS1 + " metros, dando uma distância pecorrida total de " + sapo1 + " metros! \n"
					+ "Sapo2 deu um pulo de " + puloS2 + " metros, dando uma distância pecorrida total de " + sapo2 + " metros! \n"
					+ "Sapo3 deu um pulo de " + puloS3 + " metros, dando uma distância pecorrida total de " + sapo3 + " metros! \n"
					+ "Sapo4 deu um pulo de " + puloS4 + " metros, dando uma distância pecorrida total de " + sapo4 + " metros! \n"
					+ "Sapo5 deu um pulo de " + puloS5 + " metros, dando uma distância pecorrida total de " + sapo5 + " metros! \n");
		} else {
			System.out.println("O final da corrida de sapos está chegando! \n"
					+ "Sapo1 deu um pulo de " + puloS1 + " metros, dando uma distância pecorrida total de " + sapo1 + " metros! \n"
					+ "Sapo2 deu um pulo de " + puloS2 + " metros, dando uma distância pecorrida total de " + sapo2 + " metros! \n"
					+ "Sapo3 deu um pulo de " + puloS3 + " metros, dando uma distância pecorrida total de " + sapo3 + " metros! \n"
					+ "Sapo4 deu um pulo de " + puloS4 + " metros, dando uma distância pecorrida total de " + sapo4 + " metros! \n"
					+ "Sapo5 deu um pulo de " + puloS5 + " metros, dando uma distância pecorrida total de " + sapo5 + " metros! \n");
			sapoCampeao();
		}
		
		
	}
	
	public void sapoCampeao() {
		int posicao;
		int numeroPosicao = 0;
		String[] textoPosicao = {"Campeão", "Segundo Lugar", "Terceiro Lugar", "Quarto Lugar", "Último"};
		int[] sapos = {sapo1,sapo2,sapo3,sapo4,sapo5};
		try {
			
			do {
				posicao = Math.max(sapos[0], Math.max(sapos[1], Math.max(sapos[2], Math.max(sapos[3], sapos[4]))));
				if(sapo1 == posicao) {
					System.out.println("Sapo 1 foi o " + textoPosicao[numeroPosicao] + "!");
					sapos[0] = 0;
				} else if(sapo2 == posicao) {
					System.out.println("Sapo 2 foi o " + textoPosicao[numeroPosicao] + "!");
					sapos[1] = 0;
				} else if(sapo3 == posicao) {
					System.out.println("Sapo 3 foi o " + textoPosicao[numeroPosicao] + "!");
					sapos[2] = 0;
				} else if(sapo4 == posicao) {
					System.out.println("Sapo 4 foi o " + textoPosicao[numeroPosicao] + "!");
					sapos[3] = 0;
				} else if(sapo5 == posicao) {
					System.out.println("Sapo 5 foi o " + textoPosicao[numeroPosicao] + "!");
					sapos[4] = 0;
				}
				numeroPosicao++;
			} while(posicao > 0);
		} catch(Exception e) {
			
		}
		
		
		
	}
	
	
}
