package main;

import java.io.IOException;

import controller.Frutas;
import controller.SteamCharts;

public class Principal {
	public static void main(String[] args) {
		Frutas frutas = new Frutas();
		SteamCharts steam = new SteamCharts();
		String path = "C:\\TEMP";
		
		
		String nome1 = "generic_food.csv";
		String nome2 = "SteamCharts.csv";
		String nome3 = "steamTeste.csv";
		try {
			
			System.out.println("--------------------- Ex 1 ---------------------");
			frutas.readFile(path, nome1);
			System.out.println("------------------Ex 2 met 2---------------------");
			steam.verIntervaloMedia(path, nome2,2021,"February",300.21);
			System.out.println("------------------Ex 2 met 2---------------------");
			steam.gerarArquivoIntervalo(path,nome3,2020,"February");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// NumberFormatException
}
