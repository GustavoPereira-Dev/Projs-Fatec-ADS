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

    private StringProperty tipoSanguineo = new SimpleStringProperty("");
    private FloatProperty peso = new SimpleFloatProperty(0f);
    private StringProperty alergias = new SimpleStringProperty("");

    private FichaMedicaDAO fichaMedicaDAO = new FichaMedicaDAOImpl();


    public void cadastrar() { 
        FichaMedica fc = telaParaFichaMedica();
        fichaMedicaDAO.guardar(fc);		
        lista.add(fc);
    }

    public void remover( FichaMedica c ) {
        fichaMedicaDAO.excluir(c.getId());		
        lista.remove(c);
    }
    public void pesquisarFichaMedica() { 
    	lista.clear();
        lista.addAll(fichaMedicaDAO.pesquisarPorTipoSanguineo(tipoSanguineo.get()));
    }

    public FichaMedica telaParaFichaMedica() { 
        FichaMedica fc = new FichaMedica();
        fc.setTipoSanguineo(tipoSanguineo.get());
        fc.setPeso(peso.get());
        fc.setAlergias(alergias.get());
        return fc;
    }

    public void fichaMedicaParaTela(FichaMedica fc) { 
        tipoSanguineo.set(fc.getTipoSanguineo());
        peso.set(fc.getPeso());
        alergias.set(fc.getAlergias());
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
