package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Livro;
import model.Produto;
import persistence.LivroDAO;
import persistence.LivroDAOImplArquivos;
import persistence.ProdutoDAO;
import persistence.ProdutoDAOImplArquivos;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
	    private ObservableList<Produto> lista = FXCollections.observableArrayList();
		
	    private StringProperty id = new SimpleStringProperty("");
	    private StringProperty nome = new SimpleStringProperty("");
	    private StringProperty descricao = new SimpleStringProperty("");
	    private StringProperty preco = new SimpleStringProperty("");
	    private StringProperty quantidade = new SimpleStringProperty("");
	    
	    /*
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		*/
	    
	    private ProdutoDAO produtoDAO = new ProdutoDAOImplArquivos();
	    
	    public void cadastrar() { 
	        Produto c = telaParaContato();
	        produtoDAO.guardar( c );
	        pesquisarNome();
	    }
	
	
	    public void pesquisarNome() { 
	        lista.clear();
	        lista.addAll( produtoDAO.pesquisarPorNome( nome.get() ) );
	    }
	
	    public void remover( Produto c ) {
	    	produtoDAO.excluir( c.getId() );
	        pesquisarNome();
	    }
	
	    public Produto telaParaContato() { 
	    	Produto c = new Produto();
	        c.setId( Long.parseLong( id.get() ));
	        c.setNome( nome.get() );
	        c.setDescricao( descricao.get() );
	        c.setPreco( Float.parseFloat( preco.get() ));
	        c.setQuantidade( Integer.parseInt( quantidade.get() ));
	        return c;
	    }
	


	    public void contatoParaTela( Produto c ) { 
	        id.set( Long.toString(c.getId()) );
	        nome.set( c.getNome() );
	        descricao.set( c.getDescricao() );
	        preco.set( Float.toString( c.getPreco() ));
	        quantidade.set( Integer.toString( c.getQuantidade() ));
	    }
	    
	    public StringProperty idProperty() { 
	        return this.id;
	    }
	
	    public StringProperty nomeProperty() { 
	        return this.nome;
	    }
	
	    public StringProperty descricaoProperty() { 
	        return this.descricao;
	    }
	    
	    public StringProperty precoProperty() { 
	        return this.preco;
	    }
	    
	    public StringProperty quantidadeProperty() { 
	        return this.quantidade;
	    }
	
	    public ObservableList<Produto> listaProperty() { 
	        return this.lista;
	    }
    
}
