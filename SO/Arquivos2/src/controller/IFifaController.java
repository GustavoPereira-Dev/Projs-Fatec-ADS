package controller;

import java.io.IOException;

import model.estrutura.lista.Lista;
import model.estrutura.pilha.Pilha;

public interface IFifaController {
	
	public Pilha<String> empilhaBrasileiros(String caminho, String nome) throws IOException;
	public void desmpilhaBonsBrasileiros(Pilha<String> pilha) throws IOException;
	public Lista<String> listaRevelacoes(String caminho, String nome) throws IOException;
	public void buscaListaBonsJovens(Lista<String> lista) throws IOException;
}
