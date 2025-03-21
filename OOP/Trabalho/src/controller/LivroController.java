package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import model.Livro;

public class LivroController {

	static Livro livro = null;
	
	public void createLivro() {
		int id, quantidade;
		String titulo, autor, editora, categoria;
		float preco;
		
		id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do livro"));
		titulo = JOptionPane.showInputDialog("Digite o titulo do livro");
		autor = JOptionPane.showInputDialog("Digite o autor do livro");
		editora = JOptionPane.showInputDialog("Digite a editora do livro");
		categoria = JOptionPane.showInputDialog("Digite a categoria do livro");
		preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o preco do livro"));
		quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do livro"));
	
		livro = new Livro(id, titulo, autor, editora, categoria, preco, quantidade);
		JOptionPane.showMessageDialog(null, "Livro criado!");
	}
	
	public String readLivro() {
		return livro.toString();
	}
	
	public void updateLivro(int id) throws IOException{
		int opc, c;
		String s;
		
		if(id == livro.getId()) {
			do {
				
				opc = Integer.parseInt(JOptionPane.showInputDialog("Que atributo quer atualizar? \n 1 - Titulo \n 2 - Autor \n 3 - Editora \n 4 - Categoria \n 5 - Preco \n 6 - Quantidade"));
				s = JOptionPane.showInputDialog("Digite o valor do campo novo");
				
			try {
				switch(opc) {
					case 1:
						livro.setTitulo(s);
						break;
					case 2:
						livro.setAutor(s);
						break;
					case 3:
						livro.setEditora(s);
						break;
					case 4:
						livro.setCategoria(s);
						break;
					case 5:
						livro.setPreco(Float.parseFloat(s));
						break;
					case 6:
						livro.setQuantidade(Integer.parseInt(s));
						break;

				}
			} catch(Exception e) {
				throw new IOException("Tipo de valor adicionado em campo incorreto");
			}
			
			
			c = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar atualizando o objeto?\n1 para sim 0 para nao"));
			} while(c != 1);
			
			JOptionPane.showMessageDialog(null, "Campos atualizados com sucesso!");
		} else {
			throw new IOException("Erro na atualizacao de campos");
		}
		
		
		
		
	}
	
	public boolean deleteLivro(int id) {
		if(livro == null)
			return false;
		if(id != livro.getId())
			return false;
		return true;
	}
	
}
