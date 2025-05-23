package edu.curso;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FichaMedicaControl {
    private ObservableList<FichaMedica> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty tipoSanguineo = new SimpleStringProperty("");
    private FloatProperty peso = new SimpleFloatProperty(0f);
    private StringProperty alergias = new SimpleStringProperty("");

    public FichaMedicaControl() { 
        id.addListener( (observer, antigo, novo) -> { 
            System.out.println(novo);
        });
    }

    public void cadastrar() { 
        FichaMedica fc = telaParaFichaMedica();
        lista.add(fc);
    }

    public void remover( FichaMedica c ) {
        lista.remove( c );
    }
    public void pesquisarFichaMedica() { 
        for (FichaMedica fc : lista) { 
            if ( fc.getId() == id.get()) { 
                fichaMedicaParaTela(fc);
            }
        }
    }

    public FichaMedica telaParaFichaMedica() { 
        FichaMedica fc = new FichaMedica();
        fc.setId( id.get() );
        fc.setTipoSanguineo( tipoSanguineo.get() );
        fc.setPeso( peso.get() );
        fc.setAlergias( alergias.get() );
        return fc;
    }

    public void fichaMedicaParaTela( FichaMedica fc ) { 
        id.set( fc.getId() );
        tipoSanguineo.set( fc.getTipoSanguineo() );
        peso.set( fc.getPeso() );
        alergias.set( fc.getAlergias() );
    }


    public LongProperty idProperty() { 
        return id;
    }

    public StringProperty tipoSanguineoProperty() { 
        return tipoSanguineo;
    }

    public FloatProperty pesoProperty() { 
        return peso;
    }

    public StringProperty alergiasProperty() { 
        return alergias;
    }

    public ObservableList<FichaMedica> listaProperty() { 
        return this.lista;
    }
}
