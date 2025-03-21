package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ProdutoController;

public class Produto {
	static ProdutoController pc = new ProdutoController();
	
	public static void main(String[] args) {
		int opc, id = 0;
		
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao: \n 1 - Criar instancia de Produto \n 2 - Mostrar Produto \n 3 - Atualizar Produto \n 4 - Deletar Produto \n 0 - cancelar"));
			
			if(opc >= 3 && opc <= 4) 
				id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto"));
			
			try {
				
				switch(opc) {
					case 1:
						pc.createProduto();					
						break;
					case 2:
						JOptionPane.showMessageDialog(null, pc.readProduto());
						break;
					case 3:
						pc.updateProduto(id);
						break;
					case 4:
						pc.deleteProduto(id);
						break;		
					}
				
			
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		} while(opc != 0);
	}
}
