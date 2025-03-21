package view;

import controller.LivroController;

import java.io.IOException;

import javax.swing.JOptionPane;
public class Livro {
	static LivroController lc = new LivroController();
	
	public static void main(String[] args) {
		int opc, id = 0;
		
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao: \n 1 - Criar instancia de livro \n 2 - Mostrar Livro \n 3 - Atualizar Livro \n 4 - Deletar livro \n 0 - cancelar"));
			
			if(opc >= 3 && opc <= 4) 
				id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do livro"));
			
			try {
				
				switch(opc) {
					case 1:
						lc.createLivro();					
						break;
					case 2:
						JOptionPane.showMessageDialog(null, lc.readLivro());
						break;
					case 3:
						lc.updateLivro(id);
						break;
					case 4:
						lc.deleteLivro(id);
						break;		
					}
				
			
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		} while(opc != 0);
	}
}
