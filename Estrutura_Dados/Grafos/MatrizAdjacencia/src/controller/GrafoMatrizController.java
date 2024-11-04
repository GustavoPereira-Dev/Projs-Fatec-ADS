package controller;

import model.estrutura.grafo.GrafoMatriz;

public class GrafoMatrizController{
	public GrafoMatrizController(){
		super();
	}

	public String teste() throws Exception{
		String[] routers = new String[]{"A","B","C","D"};
		GrafoMatriz g = new GrafoMatriz(routers);

		g.link("A","B");
		g.link("A","D");
		g.link("B","C");
		g.link("C","D");

		return g.toString();
	}

}