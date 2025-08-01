package view;

import controller.LivroController;
import model.Livro;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LivroBoundary implements Boundary {
    private LivroController control = new LivroController();

    private TextField txtId = new TextField();
    private TextField txtTitulo = new TextField();
    private TextField txtAutor = new TextField();
    private TextField txtEditora = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtPreco = new TextField();
    private TextField txtQuantidade = new TextField();

    private TableView<Livro> tabela = new TableView<>();

    public void bindings() {
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty());
        Bindings.bindBidirectional(txtTitulo.textProperty(), control.tituloProperty());
        Bindings.bindBidirectional(txtAutor.textProperty(), control.autorProperty());
        Bindings.bindBidirectional(txtEditora.textProperty(), control.editoraProperty());
        Bindings.bindBidirectional(txtCategoria.textProperty(), control.categoriaProperty());
        Bindings.bindBidirectional(txtPreco.textProperty(), control.precoProperty());
        Bindings.bindBidirectional(txtQuantidade.textProperty(), control.quantidadeProperty());
    }

    public void tableCreation() {
        TableColumn<Livro, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getId())));

        TableColumn<Livro, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTitulo()));

        TableColumn<Livro, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getAutor()));

        TableColumn<Livro, String> colEditora = new TableColumn<>("Editora");
        colEditora.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getEditora()));

        TableColumn<Livro, String> colCategoria = new TableColumn<>("Categoria");
        colCategoria.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCategoria()));

        TableColumn<Livro, String> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPreco())));

        TableColumn<Livro, String> colQuantidade = new TableColumn<>("Qtd");
        colQuantidade.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getQuantidade())));

        tabela.getColumns().addAll(colId, colTitulo, colAutor, colEditora, colCategoria, colPreco, colQuantidade);
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

        String[] labels = {"ID", "Título", "Autor", "Editora", "Categoria", "Preço", "Quantidade"};
        TextField[] fields = {txtId, txtTitulo, txtAutor, txtEditora, txtCategoria, txtPreco, txtQuantidade};

        for (int i = 0; i < labels.length; i++) {
            form.add(new Label(labels[i] + ":"), 0, i);
            form.add(fields[i], 1, i);
        }

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.cadastrar();
            new Alert(Alert.AlertType.INFORMATION, "Livro cadastrado com sucesso!", ButtonType.OK).show();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> control.pesquisarTitulo());

        botoes.getChildren().addAll(btnSalvar, btnPesquisar);
        form.add(botoes, 0, labels.length, 2, 1);

        principal.setTop(form);
        principal.setCenter(tabela);
        return principal;
    }
}