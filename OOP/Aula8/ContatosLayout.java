import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ContatosLayout extends Application{
	@Override
	public void start(Stage stage) throws Exception{
        GridPane painel = new GridPane();
        BorderPane bPane = new BorderPane();
        
        Scene scn = new Scene(painel, 400, 400);
        
        painel.setHgap(50);
        painel.setVgap(30);

		Label lId = new Label("Id: ");
        painel.add(lId,0,0);

		Label lNome = new Label("Nome: ");
        painel.add(lNome,0,1);
		
		Label lTelefone = new Label("Telefone: ");
        painel.add(lTelefone,0,2);
		
		TextField tId = new TextField();
        painel.add(tId,1,0);
		
		TextField tNome = new TextField();
        painel.add(tNome,1,1);

		TextField tTelefone = new TextField();
        painel.add(tTelefone,1,2);
        
        painel.add(bPane, 1, 3);
		Button btnSalvar = new Button("Salvar");
        bPane.setLeft(btnSalvar);

		Button btnPesquisar = new Button("Pesquisar");
        bPane.setCenter(btnPesquisar);

		stage.setScene(scn);
        stage.setTitle("Agenda de Contatos");
        stage.show();

	}

	public static void main(String[] args){
		Application.launch(args);
	}
}