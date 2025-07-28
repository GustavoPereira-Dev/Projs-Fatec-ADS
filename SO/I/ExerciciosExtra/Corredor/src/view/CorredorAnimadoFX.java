package view;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CorredorAnimadoFX extends Application {

    private static final int NUM_CORREDORES = 4;
    private static final int DISTANCIA_CORREDOR = 200;
    private static final double LARGURA_TELA = 1000;
    private static final double ALTURA_CORREDOR = 120;
    private static final double PASSO = LARGURA_TELA / DISTANCIA_CORREDOR;

    private VBox corredoresPane;
    private TextArea logConsole;
    private Semaphore semaforo = new Semaphore(1);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        logConsole = new TextArea();
        logConsole.setEditable(false);
        logConsole.setPrefHeight(150);

        corredoresPane = new VBox(10);
        corredoresPane.setPrefHeight(NUM_CORREDORES * ALTURA_CORREDOR);
        corredoresPane.setAlignment(Pos.CENTER);

        Button iniciarBtn = new Button("Iniciar Corrida");
        iniciarBtn.setOnAction(e -> iniciarCorrida());

        root.setCenter(corredoresPane);
        root.setBottom(logConsole);
        root.setTop(iniciarBtn);
        BorderPane.setAlignment(iniciarBtn, Pos.CENTER);

        Scene scene = new Scene(root, LARGURA_TELA, 600);
        primaryStage.setTitle("Corrida dos Corredores");
        primaryStage.setScene(scene);
        primaryStage.show();

        prepararCorredores();
    }

    private void prepararCorredores() {
        corredoresPane.getChildren().clear();
        for (int i = 0; i < NUM_CORREDORES; i++) {
            HBox pista = new HBox();
            pista.setSpacing(10);
            pista.setPrefHeight(ALTURA_CORREDOR);
            pista.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: black;");

            ImageView corredor = new ImageView(new Image(getClass().getResourceAsStream("/teste.gif"), 40, 40, true, true));
            corredor.setFitHeight(60);
            corredor.setPreserveRatio(true);
            corredor.setTranslateX(0);

            Rectangle porta = new Rectangle(40, 60, Color.BROWN);
            porta.setTranslateX(LARGURA_TELA - 100);

            pista.getChildren().addAll(corredor, porta);
            corredoresPane.getChildren().add(pista);
        }
    }

    private void iniciarCorrida() {
        prepararCorredores();
        for (int i = 0; i < NUM_CORREDORES; i++) {
            HBox pista = (HBox) corredoresPane.getChildren().get(i);
            ImageView corredor = (ImageView) pista.getChildren().get(0);
            int id = i + 1;
            new Thread(new CorredorFX(corredor, id)).start();
        }
    }

    private void log(String msg) {
        Platform.runLater(() -> logConsole.appendText(msg + "\n"));
    }

    class CorredorFX implements Runnable {
        private ImageView corredor;
        private int id;
        private Random random = new Random();
        private double posicaoAtual = 0;

        public CorredorFX(ImageView corredor, int id) {
            this.corredor = corredor;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (posicaoAtual < LARGURA_TELA - 140) {
                    double metros = 4 + random.nextInt(3); // 4 a 6 metros
                    double passo = metros * PASSO;
                    posicaoAtual += passo;
                    moverCorredor(passo);

                    log("Corredor " + id + " percorreu " + Math.min((int) (posicaoAtual / PASSO), DISTANCIA_CORREDOR) + " metros");
                    Thread.sleep(1000);
                }

                log("Corredor " + id + " chegou na porta");
                semaforo.acquire();
                abrirECruzarPorta();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }

        private void moverCorredor(double distancia) {
            Platform.runLater(() -> {
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), corredor);
                tt.setByX(distancia);
                tt.setInterpolator(Interpolator.EASE_BOTH);
                tt.play();
            });
        }

        private void abrirECruzarPorta() throws InterruptedException {
            Platform.runLater(() -> {
                // Anima a porta abrindo (simplesmente muda a cor para verde)
                HBox pista = (HBox) corredor.getParent();
                Rectangle porta = (Rectangle) pista.getChildren().get(1);
                porta.setFill(Color.GREEN);
            });
            log("Corredor " + id + " está abrindo e cruzando a porta...");
            Thread.sleep((1 + random.nextInt(2)) * 1000); // 1 a 2 segundos
            log("Corredor " + id + " cruzou a porta!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
