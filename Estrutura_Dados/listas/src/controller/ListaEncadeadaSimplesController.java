package controller;

import model.estrutura_simples.ListaEncadeadaSimples;

public class ListaEncadeadaSimplesController{
	public ListaEncadeadaSimplesController(){
		super();
	}
	
	public String teste() throws Exception{
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		
		lista.append(1);
		lista.append(2);
		lista.append(3);
		lista.insert(0, 4);
		
		return lista.toString();
	}
}