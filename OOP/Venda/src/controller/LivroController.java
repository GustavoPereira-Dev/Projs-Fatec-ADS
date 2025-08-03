package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Livro;
import persistence.LivroDAO;
import persistence.LivroDAOImplArquivos;

import java.util.ArrayList;
import java.util.List;

public class LivroController {
	    private ObservableList<Livro> lista = FXCollections.observableArrayList();
	
	    private StringProperty id = new SimpleStringProperty("");
	    private StringProperty titulo = new SimpleStringProperty("");
	    private StringProperty autor = new SimpleStringProperty("");
	    private StringProperty editora = new SimpleStringProperty("");
	    private StringProperty categoria = new SimpleStringProperty("");
	    private StringProperty preco = new SimpleStringProperty("");
	    private StringProperty quantidade = new SimpleStringProperty("");
	    
	    private LivroDAO livroDAO = new LivroDAOImplArquivos();
	    
	    public void cadastrar() { 
	        Livro c = telaParaContato();
	        livroDAO.guardar(c);
	        pesquisarTitulo();
	    }


	    public void pesquisarTitulo() { 
	        lista.clear();
	        lista.addAll(livroDAO.pesquisarPorTitulo(titulo.get()));
	    }

	    public void remover(Livro c) {
	    	livroDAO.excluir(c.getId());
	        pesquisarTitulo();
	    }

	    public Livro telaParaContato() { 
	    	Livro c = new Livro();
	        c.setId( Long.parseLong(id.get()));
	        c.setTitulo(titulo.get());
	        c.setAutor(autor.get());
	        c.setEditora(editora.get());
	        c.setCategoria(categoria.get());
	        c.setPreco(Float.parseFloat(preco.get()));
	        c.setQuantidade(Integer.parseInt(quantidade.get()));
	        return c;
	    }

	    
	    public void contatoParaTela( Livro c ) { 
	        id.set(Long.toString(c.getId()));
	        titulo.set(c.getTitulo());
	        autor.set(c.getAutor());
	        editora.set(c.getEditora());
	        categoria.set(c.getCategoria());
	        preco.set(Float.toString(c.getPreco()));
	        quantidade.set(Integer.toString( c.getQuantidade()));
	    }
	    
        public StringProperty idProperty() { 
            return id;
        }

        public StringProperty tituloProperty() { 
            return titulo;
        }

        public StringProperty autorProperty() { 
            return autor;
        }
        
        public StringProperty editoraProperty() { 
            return editora;
        }

        public StringProperty categoriaProperty() { 
            return categoria;
        }

        public StringProperty precoProperty() { 
            return preco;
        }
        
        public StringProperty quantidadeProperty() { 
            return quantidade;
        }

        public ObservableList<Livro> listaProperty() { 
            return this.lista;
        }
}