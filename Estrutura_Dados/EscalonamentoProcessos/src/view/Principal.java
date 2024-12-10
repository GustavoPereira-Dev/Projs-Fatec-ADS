package view;

import controller.ProcessoController;

public class Principal {
	public static void main(String[] args) {
		ProcessoController p = new ProcessoController();
		
		try {
			System.out.println(p.teste());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
