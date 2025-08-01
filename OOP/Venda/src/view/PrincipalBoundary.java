package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {

    private Boundary telaLivro = new LivroBoundary();
    private Boundary telaProduto = new ProdutoBoundary();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane panePrincipal = new BorderPane();
        Scene scn = new Scene(panePrincipal, 800, 600);

        MenuBar menuBar = new MenuBar();

        Menu mnuLivro = new Menu("Livro");
        Menu mnuCreditos = new Menu("Créditos");
        menuBar.getMenus().addAll( mnuLivro, mnuCreditos );

        MenuItem mnuContato = new MenuItem("Contato");
        MenuItem mnuProduto = new MenuItem("Produto");
        MenuItem mnuSair = new MenuItem("Sair");
        mnuLivro.getItems().addAll( mnuContato, mnuProduto, mnuSair);

        MenuItem mnuSobre = new MenuItem("Sobre");
        mnuCreditos.getItems().addAll( mnuSobre );

        panePrincipal.setTop(menuBar);

        mnuContato.setOnAction( e -> panePrincipal.setCenter( telaLivro.render() ) );
        mnuProduto.setOnAction( e -> panePrincipal.setCenter( telaProduto.render() ) );
        mnuSair.setOnAction( e -> {
            Platform.exit();
            System.exit(0);
        });

        mnuSobre.setOnAction( e-> 
            new Alert(AlertType.INFORMATION, 
                "Feito por Gustavo Pereira", 
                ButtonType.OK).show()
        );



        stage.setScene(scn);
        stage.setTitle("Sistema Vendas");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
    
}