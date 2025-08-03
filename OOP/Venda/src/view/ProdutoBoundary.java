package view;

import controller.ProdutoController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Produto;

public class ProdutoBoundary implements Boundary {
    private ProdutoController control = new ProdutoController();

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtDescricao = new TextField();
    private TextField txtPreco = new TextField();
    private TextField txtQuantidade = new TextField();

    private TableView<Produto> tabela = new TableView<>();

    public void bindings() {
        Bindings.bindBidirectional(txtId.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(txtPreco.textProperty(), control.precoProperty());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), control.quantidadeProperty());
    }

    public void tableCreation() {
        TableColumn<Produto, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getId())));

        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getNome()));

        TableColumn<Produto, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDescricao()));

        TableColumn<Produto, String> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPreco())));

        TableColumn<Produto, String> colQuantidade = new TableColumn<>("Quantidade");
        colQuantidade.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getQuantidade())));

        TableColumn<Produto, Void> colAcoes = new TableColumn<>("Ações");
        colAcoes.setCellFactory(col -> new TableCell<>() {
            private final Button btnRemover = new Button("Remover");

            {
                btnRemover.setOnAction(e -> {
                    Produto p = control.listaProperty().get(getIndex());
                    control.remover(p);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnRemover);
            }
        });

        tabela.getColumns().addAll(colId, colNome, colDescricao, colPreco, colQuantidade, colAcoes);
        tabela.setItems(control.listaProperty());

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            control.contatoParaTela(novo);
        });
    }

    public Pane render() {
        BorderPane principal = new BorderPane();
        GridPane form = new GridPane();
        HBox botoes = new HBox(10);

        bindings();
        tableCreation();

        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("ID:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Nome:"), 0, 1);
        form.add(txtNome, 1, 1);
        form.add(new Label("Descrição:"), 0, 2);
        form.add(txtDescricao, 1, 2);
        form.add(new Label("Preço:"), 0, 3);
        form.add(txtPreco, 1, 3);
        form.add(new Label("Quantidade:"), 0, 4);
        form.add(txtQuantidade, 1, 4);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.cadastrar();
            new Alert(Alert.AlertType.INFORMATION, "Produto salvo com sucesso!", ButtonType.OK).show();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> control.pesquisarNome());

        botoes.getChildren().addAll(btnSalvar, btnPesquisar);
        form.add(botoes, 0, 5, 2, 1);

        principal.setTop(form);
        principal.setCenter(tabela);
        
        return principal;
    }
}