package controller;

import model.lista_circular.ListaCircular;

public class RotementoProtocolosController {
	public String teste() {
		ListaCircular c = new ListaCircular();
		
		
		c.append("http", "2122");
		c.enqueueFila("a", "http");
		c.enqueueFila("b", "http");
		c.enqueueFila("c", "pop");
		
		c.dequeueFila("http");
		return c.toString();
	}
}
