package edu.curso;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContatoControl {
    private ObservableList<Contato> lista = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");

    private ContatoDAO contatoDAO = new ContatoDAOImpl();

    private ObjectProperty<LocalDate> nascimento = 
        new SimpleObjectProperty<>(LocalDate.now());

    public void cadastrar() { 
        Contato c = telaParaContato();
        contatoDAO.guardar( c );
        pesquisarContato();
    }


    public void pesquisarContato() { 
        lista.clear();
        lista.addAll( contatoDAO.pesquisarPorNome( nome.get() ) );
    }

    public void remover( Contato c ) {
        contatoDAO.excluir( c.getId() );
        pesquisarContato();
    }

    public Contato telaParaContato() { 
        Contato c = new Contato();
        c.setNome( nome.get() );
        c.setTelefone( telefone.get() );
        c.setEmail( email.get() );
        c.setNascimento( nascimento.get() );
        return c;
    }

    public void contatoParaTela( Contato c ) { 
        nome.set( c.getNome() );
        telefone.set( c.getTelefone() );
        email.set( c.getEmail() );
        nascimento.set( c.getNascimento() );
    }


    public StringProperty nomeProperty() { 
        return this.nome;
    }

    public StringProperty telefoneProperty() { 
        return this.telefone;
    }

    public StringProperty emailProperty() { 
        return this.email;
    }

    public ObjectProperty<LocalDate> nascimentoProperty() { 
        return this.nascimento;
    }

    public ObservableList<Contato> listaProperty() { 
        return this.lista;
    }
}