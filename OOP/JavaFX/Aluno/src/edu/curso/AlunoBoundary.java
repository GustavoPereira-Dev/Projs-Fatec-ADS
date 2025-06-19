package edu.curso;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class AlunoBoundary extends Application {

    private AlunoControl control = new AlunoControl();
    private TextField txtId = new TextField();
    private TextField txtRa = new TextField();
    private TextField txtNome = new TextField();
    private DatePicker dtaNascimento = new DatePicker();
    private TableView<Aluno> tabela = new TableView<>();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public void tableCreation() {
    	
        TableColumn<Aluno, String> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory( c -> new ReadOnlyStringWrapper(Long.toString(c.getValue().getId())));
        
        TableColumn<Aluno, String> col2 = new TableColumn<>("Ra");
        col2.setCellValueFactory( c -> new ReadOnlyStringWrapper( c.getValue().getRa() ));

        TableColumn<Aluno, String> col3 = new TableColumn<>("Nome");
        col3.setCellValueFactory(c -> new ReadOnlyStringWrapper( c.getValue().getNome()));


        TableColumn<Aluno, String> col4 = new TableColumn<>("Nascimento");
        col4.setCellValueFactory( c -> { 
            String strNascimento = dateFormatter.format(c.getValue().getNascimento());
            return new ReadOnlyStringWrapper(strNascimento);
        });

        tabela.getColumns().addAll(col1, col2, col3, col4);

        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obs, antigo, novo) -> { 
                System.out.println("Aluno selecionado ==> " + novo);
                boundaryToEntity();
            }
        );
    }
    
    public void start(Stage stage) { 
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();
        HBox paneBotoes = new HBox();
        
        tableCreation();

        ColumnConstraints colLabel = new ColumnConstraints();
        colLabel.setPercentWidth(10);

        ColumnConstraints colTextField = new ColumnConstraints();
        colTextField.setPercentWidth(20);

        RowConstraints linha = new RowConstraints();
        linha.setPrefHeight(50);

        paneForm.getColumnConstraints().addAll(colLabel, colTextField);
        paneForm.getRowConstraints().addAll(linha, linha, linha, linha, linha);

        Scene scn = new Scene(panePrincipal, 800, 600);

        paneForm.add( new Label("Id:"), 0, 0);
        paneForm.add( txtId, 1, 0);
        paneForm.add( new Label("Ra:"), 0, 1);
        paneForm.add( txtRa, 1, 1);
        paneForm.add( new Label("Nome:"), 0, 2);
        paneForm.add( txtNome, 1, 2);
        paneForm.add( new Label("Nascimento:"), 0, 3);
        paneForm.add( dtaNascimento, 1, 3);

        Button btnSalvar = new Button("Adicionar");

        btnSalvar.setOnAction( evento -> {
            Aluno a1 = boundaryToEntity();
            control.adicionar(a1);
            new Alert(AlertType.INFORMATION, 
                "Contato gravado com sucesso", 
                            ButtonType.OK).show();
            List<Aluno> alunos = control.pesquisarPorNome("");
            tabela.setItems(FXCollections.observableArrayList(alunos));
        });

        Button btnPesquisar = new Button("Pesquisar");

        btnPesquisar.setOnAction( evento -> {
            List<Aluno> alunos = control.pesquisarPorNome( txtNome.getText() );
            if (alunos != null) { 
                tabela.setItems(FXCollections.observableArrayList(alunos));
            }
        });

        paneBotoes.getChildren().addAll(btnSalvar, btnPesquisar);

        paneForm.add(paneBotoes, 0, 4, 2, 1);

        panePrincipal.setTop(paneForm);
        
        panePrincipal.setCenter(tabela);
        

        stage.setScene(scn);
        stage.setTitle("Agenda de Contato");
        stage.show();
    }

    public Aluno boundaryToEntity() { 
    	Aluno a = new Aluno();
        a.setId(Long.parseLong(txtId.getText()));
        a.setRa(txtRa.getText());
        a.setNome(txtNome.getText());
        a.setNascimento(dtaNascimento.getValue());
        return a;
    }

    public void entityToBoundary(Aluno a) { 
        txtId.setText(Long.toString(a.getId()));
        txtRa.setText(a.getRa());
        txtNome.setText(a.getNome());
        dtaNascimento.setValue(a.getNascimento());
    }

    public static void main(String[] args) {
        Application.launch(AlunoBoundary.class, args);
    }
}