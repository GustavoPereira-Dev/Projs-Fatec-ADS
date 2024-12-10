package controller;

import model.estrutura.grafo.lista.ProcessoLista;
import model.estrutura.grafo.matriz.ProcessoMatriz;

public class ProcessoController {
	public String teste() {
		
		ProcessoLista l = new ProcessoLista();
		
		l.add("a");
		l.add("b");
		l.add("c");
		l.add("d");
		l.add("e");
		l.add("f");
		
		
		l.add("b","a",1);
		l.add("a","d",1);
		l.add("b","c",1);
		l.add("d","e",2);
		l.add("e","c",2);
		l.add("f","e",2);
		
		String[] sequencia2 = l.pecorrerSequenciaProcessos();
		
		return l.retornarSequencia(sequencia2);
		/*
		String[] processos = {"a","b","c","d","e","f"};
		
		ProcessoMatriz m = new ProcessoMatriz(processos);
		
		m.link("b","a",1);
		m.link("a","d",1);
		m.link("b","c",1);
		m.link("d","e",2);
		m.link("e","c",2);
		m.link("f","e",2);
		
		m.link("e","b",1);
		m.unlink("e","b",1);
		
		String[] sequencia = m.pecorrerSequenciaProcessos();
		
		
		return m.retornarSequencia(sequencia);
		*/
	}
}
