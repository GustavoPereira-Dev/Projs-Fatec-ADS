package controller;

import model.estrutura_fila.FilaFifo;
import model.estrutura_fila.No;

public class FilaFifoController{
	public FilaFifoController(){
		super();
	}

	public String teste() throws Exception{
		FilaFifo fila = new FilaFifo();
		
		fila.enqueue(1);
		fila.enqueue(2);
		fila.enqueue(3);
		fila.dequeue();

		return fila.toString();
	}
}