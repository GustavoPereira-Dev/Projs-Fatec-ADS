package edu.curso;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.scene.control.TableView;

public class FichaMedicaBoundary implements Boundary {
    private FichaMedicaControl control = new FichaMedicaControl();
    private TextField txtTipoSanguineo = new TextField();
    private TextField txtPeso = new TextField();
    private TextField txtAlergias = new TextField();
    private TableView<FichaMedica> tabela = new TableView<>();

    public void bindings() { 
        Bindings.bindBidirectional(control.tipoSanguineoProperty(), txtTipoSanguineo.textProperty());

        TextFormatter<Float> pesoFormatter = new TextFormatter<>(new FloatStringConverter());
        txtPeso.setTextFormatter(pesoFormatter);
        ObjectProperty<Float> intermediatePeso = new SimpleObjectProperty<>();
        intermediatePeso.bindBidirectional(control.pesoProperty().asObject());
        pesoFormatter.valueProperty().bindBidirectional(intermediatePeso);     
		
        Bindings.bindBidirectional(control.alergiasProperty(), txtAlergias.textProperty());
    }

    public void tableCreation() {
        TableColumn<FichaMedica, String> col1 = new TableColumn<>("Tipo Sanguíneo");
        col1.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTipoSanguineo()));
        
        TableColumn<FichaMedica, String> col2 = new TableColumn<>("Peso");
        col2.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPeso())));
        
        TableColumn<FichaMedica, String> col3 = new TableColumn<>("Alergias");
        col3.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getAlergias()));
        
        TableColumn<FichaMedica, Void> col4 = new TableColumn<>("Ações");

        Callback<TableColumn<FichaMedica, Void>, TableCell<FichaMedica, Void>> cellFactory
                = (tablecolumn) -> new TableCell<>() {

                    private Button btnApagar = new Button("Apagar");

                    {
                        btnApagar.setOnAction( e -> {
                            FichaMedica c = control.listaProperty().get( getIndex() );
                            control.remover( c );
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic( btnApagar );
                        }
                    }
                };
                
        col4.setCellFactory( cellFactory );

        tabela.getColumns().addAll(col1, col2, col3, col4);
        tabela.setItems( control.listaProperty() );

        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obs, antigo, novo) -> { 
                System.out.println("Contato selecionado ==> " + novo);
                control.fichaMedicaParaTela( novo );
            }
        );
        
    }

    public Pane render() { 
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();
        HBox paneBotoes = new HBox();

        bindings();
        tableCreation();

        ColumnConstraints colLabel = new ColumnConstraints();
        colLabel.setPercentWidth(30);

        ColumnConstraints colTextField = new ColumnConstraints();
        colTextField.setPercentWidth(70);

        RowConstraints linha = new RowConstraints();
        linha.setPrefHeight(50);

        paneForm.getColumnConstraints().addAll(colLabel, colTextField);
        paneForm.getRowConstraints().addAll(linha, linha, linha, linha, linha);

        Scene scn = new Scene(panePrincipal, 800, 600);

        paneForm.add( new Label("Tipo Sanguíneo:"), 0, 0);
        paneForm.add( txtTipoSanguineo, 1, 0);
        paneForm.add( new Label("Peso:"), 0, 1);
        paneForm.add( txtPeso, 1, 1);
        paneForm.add( new Label("Alergias:"), 0, 2);
        paneForm.add( txtAlergias, 1, 2);

        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction( evento -> {
            control.cadastrar();
            new Alert(AlertType.INFORMATION, 
                "Ficha Médica gravado com sucesso", 
                            ButtonType.OK).show();
        });

        Button btnPesquisar = new Button("Pesquisar");

        btnPesquisar.setOnAction( evento -> {
            control.pesquisarFichaMedica();
        });

        paneBotoes.getChildren().addAll(btnSalvar, btnPesquisar);

        paneForm.add( paneBotoes, 0, 4, 2, 1);

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter( tabela );

        return panePrincipal;
    }

}