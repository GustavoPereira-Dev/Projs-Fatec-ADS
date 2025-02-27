package view;

import controller.OperacoesController;

public class Principal {

	public static void main(String[] args) {
		OperacoesController operacoesController = new OperacoesController();
		
		// 1
		operacoesController.pecorrerVetor1000Posicoes();
		operacoesController.pecorrerVetor10000Posicoes();
		operacoesController.pecorrerVetor100000Posicoes();
		
		
		// 2.
		
		operacoesController.quantPartesTexto();
		
		// 3
		operacoesController.entrarTamanhoNVetor();
		
	}

}
