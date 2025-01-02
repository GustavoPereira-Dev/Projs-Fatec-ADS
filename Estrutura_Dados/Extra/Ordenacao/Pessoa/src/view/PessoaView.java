package view;

import controller.PessoaController;
import model.Pessoa;

public class PessoaView {
	public static void main(String[] args) {
		try {
			PessoaController obj = new PessoaController(); 
			Pessoa[] pessoas = obj.ordenaNome();
			
			System.out.println("Ordenação por Nome: ");
			for(int i = 0; i < pessoas.length; i++) 
				System.out.println(pessoas[i].toString());
			
			System.out.println("");
			pessoas = obj.ordenaSobrenome(pessoas,0,pessoas.length - 1);
			System.out.println("Ordenação por Sobreome: ");
			for(int i = 0; i < pessoas.length; i++) 
				System.out.println(pessoas[i].toString());
			
			System.out.println("");
			pessoas = obj.ordenaIdade(pessoas,0,pessoas.length - 1);
			System.out.println("Ordenação por Idade: ");
			for(int i = 0; i < pessoas.length; i++) 
				System.out.println(pessoas[i].toString());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

