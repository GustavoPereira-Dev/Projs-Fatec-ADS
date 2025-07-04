import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Contatos extends Application{
	@Override
	public void start(Stage stage) throws Exception{
        Pane painel = new Pane();
        Scene scn = new Scene(painel, 400, 400);

		Label lId = new Label("Id: ");
        lId.relocate(50, 50);
        painel.getChildren().add(lId);

		Label lNome = new Label("Nome: ");
		lNome.relocate(50, 130);
        painel.getChildren().add(lNome);
		
		Label lTelefone = new Label("Telefone: ");
		lTelefone.relocate(50, 210);
        painel.getChildren().add(lTelefone);
		
		TextField tId = new TextField();
        tId.relocate(200, 50);
        painel.getChildren().add(tId);
		
		TextField tNome = new TextField();
		tNome.relocate(200, 130);
        painel.getChildren().add(tNome);

		TextField tTelefone = new TextField();
		tTelefone.relocate(200, 210);
        painel.getChildren().add(tTelefone);

		Button btnSalvar = new Button("Salvar");
        btnSalvar.relocate(50, 260);
        painel.getChildren().add(btnSalvar);

		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.relocate(100, 260);
        painel.getChildren().add(btnPesquisar);


		stage.setScene(scn);
        stage.setTitle("Agenda de Contatos");
        stage.show();

	}

	public static void main(String[] args){
		Application.launch(args);
	}
}