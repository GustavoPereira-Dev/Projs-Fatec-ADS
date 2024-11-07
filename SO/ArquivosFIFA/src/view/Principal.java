package view;

import java.io.IOException;

import controller.FifaController;
import model.estrutura.lista.Lista;
import model.estrutura.pilha.Pilha;

public class Principal {
	public static void main(String[] args) {
		
		Pilha<String> pilha = new Pilha<String>();
		Lista<String> lista = new Lista<String>();
		
		FifaController fifa = new FifaController();
		
		String path = "C:\\TEMP";
		String nome = "data.csv";
		
		try {
			System.out.println("------------------------Pilha------------------------");
			pilha = fifa.empilhaBrasileiros(path, nome);
			fifa.desmpilhaBonsBrasileiros(pilha);
			
			System.out.println("------------------------Fila------------------------");
			lista = fifa.listaRevelacoes(path, nome);
			fifa.buscaListaBonsJovens(lista);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
