package controller;

import model.lista_circular.ListaCircular;

public class RotementoProtocolosController {
	public String teste() {
		ListaCircular c = new ListaCircular();
		
		
		c.append("http", "a");
		c.append("http", "b");
		c.append("http", "c");
		c.append("pop", "d");
		c.append("pop", "e");
		c.append("icmp", "e");
		return c.toString();
	}
}
