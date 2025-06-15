package edu.curso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CursoBoundary extends Application {

    private CursoControl control = new CursoControl();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCodigoCurso = new TextField();
	private TextField txtNomeCoordenador = new TextField();
	private TextField txtQtdAlunos = new TextField();

	@Override
    public void start(Stage stage) { 
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Scene scn = new Scene(panePrincipal, 500, 150);

        paneForm.add(new Label("Id"), 0, 0);
        paneForm.add(txtId, 1, 0);
        paneForm.add(new Label("Nome"), 0, 1);
        paneForm.add(txtNome, 1, 1);        
        paneForm.add(new Label("Código Curso"), 0, 2);
        paneForm.add(txtCodigoCurso, 1, 2);
        paneForm.add(new Label("Nome Coordenador"), 0, 3);
        paneForm.add(txtNomeCoordenador, 1, 3);
        paneForm.add(new Label("Quantidade de Alunos"), 0, 4);
        paneForm.add(txtQtdAlunos, 1, 4);

        Button btnSalvar = new Button("Adicionar");

        btnSalvar.setOnAction(evento -> {
            Curso c1 = toEntity();
            control.gravar(c1);
            new Alert(AlertType.INFORMATION, "Contato gravado com sucesso", ButtonType.OK).show();
        });

        Button btnPesquisar = new Button("Pesquisar");

        btnPesquisar.setOnAction(evento -> {
            Curso c = control.pesquisar(txtNome.getText());
            if (c != null) { 
                fromEntity(c);
            }
        });

        paneForm.add(btnSalvar, 0, 5);
        paneForm.add(btnPesquisar, 1, 5);

        panePrincipal.setCenter(paneForm);

        stage.setScene(scn);
        stage.setTitle("Gestão de Cursos");
        stage.show();
    }

    public Curso toEntity() { 
    	Curso c = new Curso();
    	c.setId(Long.parseLong(txtId.getText()));
        c.setNome( txtNome.getText() );
        c.setCodigoCurso(Integer.parseInt(txtCodigoCurso.getText()));
        c.setNomeCoordenador(txtNomeCoordenador.getText());
        c.setQtdAlunos(Integer.parseInt(txtQtdAlunos.getText()));
        return c;
    }
    
    public void fromEntity(Curso c) { 
        txtId.setText(Long.toString(c.getId()));
    	txtNome.setText(c.getNome());
    	txtCodigoCurso.setText(Integer.toString(c.getCodigoCurso()));
    	txtNomeCoordenador.setText(c.getNomeCoordenador());
    	txtQtdAlunos.setText(Integer.toString(c.getQtdAlunos()));
        
    }

    public static void main(String[] args) {
        Application.launch(CursoBoundary.class, args);
    }
}