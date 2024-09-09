package controller;

import model.estrutura_circular.CircularDupla;
import model.estrutura_circular.No;

public class CircularDuplaController{
	
	
	public String teste() throws Exception{
		CircularDupla lista = new CircularDupla();
		
		lista.append(1);
		lista.append(2);
		lista.append(3);
		
		lista.remove(lista.getLast().getProximo());
		
		
		return lista.toString();
	}
}

// javac -s ./src/ -d ./dist/ ./src/model/estrutura/No.java ./src/model/estrutura/CircularDupla.java  ./src/controller/CircularDuplaController.java ./src/view/CircularDupla.java
// java -classpath dist view/CircularDupla
