package controller;

import java.util.Arrays;
import java.util.Scanner;

public class OperacoesController {

	public OperacoesController() {
		super();
	}

	// 1
	public void pecorrerVetor1000Posicoes() {
		int[] vetor = new int[1000];
		
		double tempoInicial = System.nanoTime();
		
		for(int i = 0; i < vetor.length; i++) {
			vetor[i] = 0;
		}
		
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		tempoTotal /= Math.pow(10, 9);
		System.out.println("Tempo ==> " + tempoTotal);
		
	}
	
	public void pecorrerVetor10000Posicoes() {
		int[] vetor = new int[10000];
		double tempoInicial = System.nanoTime();

		for(int i = 0; i < vetor.length; i++) {
			vetor[i] = 0;
		}
		
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		tempoTotal /= Math.pow(10, 9);
		System.out.println("Tempo ==> " + tempoTotal);
	}
	
	public void pecorrerVetor100000Posicoes() {
		int[] vetor = new int[10000];
		double tempoInicial = System.nanoTime();

		for(int i = 0; i < vetor.length; i++) {
			vetor[i] = 0;
		}
		
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		tempoTotal /= Math.pow(10, 9);
		System.out.println("Tempo ==> " + tempoTotal);
	}
	
	// 2
	public void quantPartesTexto() {
		Scanner scanner = new Scanner(System.in);
		
		String[] textos = scanner.nextLine().split(";");
		
		
		int quant = 0;

		for(int i = 0; i < textos.length; i++) {
			quant++;
		}
		
		System.out.println("Quantidade ==> " + quant);
		
		
	}
	
	
	// 3
	public void entrarTamanhoNVetor() {
		Scanner scanner = new Scanner(System.in);

		int tamanhoVetor = scanner.nextInt();
		int vet[] = new int[tamanhoVetor];

		for(int i = 0; i < vet.length; i++) {
			vet[i] = scanner.nextInt();
		}
		
		for(int i:  vet) {
			if(i % 2 == 1) {
				System.out.println(i + " é ímpar");
			} else if(i % 10 == 0) {
				System.out.println(i + " é par e múltiplo de 10" );
			}
		}

	}
}
