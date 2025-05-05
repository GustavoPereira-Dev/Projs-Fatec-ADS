import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) throws Exception{
        GridPane painel = new GridPane();
        BorderPane bPane = new BorderPane();
        
        Scene scn = new Scene(painel, 400, 400);
        
        TextField tCalc = new TextField();
        bPane.setLeft(tCalc);
        
        Button btnCE = new Button("CE");
        bPane.setRight(btnCE);
        
        painel.add(bPane, 0, 0);
        
        Button btn1 = new Button("1");
        painel.add(btn1, 1,1);
        
        Button btn2 = new Button("2");
        painel.add(btn2, 2,1);
        
        Button btn3 = new Button("3");
        painel.add(btn3, 3,1);
        
        Button btnAd = new Button("+");
        painel.add(btnAd, 4,1);
        
        Button btn4 = new Button("4");
        painel.add(btn4, 1, 2);
        
        Button btn5 = new Button("5");
        painel.add(btn5, 2, 2);
        
        Button btn6 = new Button("6");
        painel.add(btn6, 3, 2);
        
        Button btnSub = new Button("-");
        painel.add(btnSub, 4, 2);
        
        Button btn7 = new Button("7");
        painel.add(btn7, 1, 3);
        
        Button btn8 = new Button("8");
        painel.add(btn8, 2, 3);
        
        Button btn9 = new Button("9");
        painel.add(btn9, 3, 3);
        
        Button btnMult = new Button("*");
        painel.add(btnMult, 4, 3);
       
        Button btnDecimal = new Button(".");
        painel.add(btnDecimal, 1, 4);
        
        Button btn0 = new Button("0");
        painel.add(btn0, 2, 4);
        
        Button btnIgual = new Button("=");
        painel.add(btnIgual, 3, 4);
        
        Button btnDiv = new Button("/");
        painel.add(btnDiv, 4, 4);
		stage.setScene(scn);
        stage.setTitle("Calculadora");
        stage.show();

	}

	public static void main(String[] args){
		Application.launch(args);
	}
}