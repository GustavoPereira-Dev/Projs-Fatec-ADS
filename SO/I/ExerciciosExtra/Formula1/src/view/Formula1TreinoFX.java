package view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Formula1TreinoFX extends Application {
    private static final int NUM_CORREDORES = 4;
    private static final int VOLTAS = 3;
    private static final double LARGURA_TELA = 1000;
    private static final double ALTURA_CORREDOR = 150;
    private static final double PASSO = 40;

    private VBox corredoresPane;
    private TextArea logConsole;
    private Semaphore semaforo = new Semaphore(1);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        logConsole = new TextArea();
        logConsole.setEditable(false);
        logConsole.setPrefHeight(150);

        corredoresPane = new VBox(15);
        corredoresPane.setPrefHeight(NUM_CORREDORES * ALTURA_CORREDOR);
        corredoresPane.setAlignment(Pos.CENTER);

        Button iniciarBtn = new Button("Iniciar Corrida");
        iniciarBtn.setOnAction(e -> iniciarCorrida());

        root.setCenter(corredoresPane);
        root.setBottom(logConsole);
        root.setTop(iniciarBtn);
        BorderPane.setAlignment(iniciarBtn, Pos.CENTER);

        Scene scene = new Scene(root, LARGURA_TELA, 750);
        primaryStage.setTitle("Corrida de Fórmula 1");
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
            pista.setStyle("-fx-background-color: #d0d0d0; -fx-border-color: black; -fx-border-width: 2;");

            ImageView carro = new ImageView(new Image(getClass().getResourceAsStream("/carro.png"), 40, 40, true, true));
            carro.setFitHeight(80);
            carro.setPreserveRatio(true);

            Label voltasLabel = new Label("Voltas: 0");
            voltasLabel.setTextFill(Color.BLUE);

            StackPane pistaVisual = new StackPane();
            pistaVisual.setPrefWidth(LARGURA_TELA - 150);
            pistaVisual.setAlignment(Pos.CENTER_LEFT);

            Rectangle faixa = new Rectangle(LARGURA_TELA - 150, 10, Color.GRAY);
            faixa.setTranslateY(25);

            for (int v = 1; v < VOLTAS; v++) {
                Line marcador = new Line(0, 0, 0, 80);
                marcador.setTranslateX(v * (LARGURA_TELA - 150) / VOLTAS);
                marcador.setStroke(Color.RED);
                pistaVisual.getChildren().add(marcador);
            }

            pistaVisual.getChildren().addAll(faixa, carro);

            VBox infoBox = new VBox(5, pistaVisual, voltasLabel);
            pista.getChildren().add(infoBox);
            corredoresPane.getChildren().add(pista);
        }
    }

    private void iniciarCorrida() {
        for (int i = 0; i < NUM_CORREDORES; i++) {
            HBox pista = (HBox) corredoresPane.getChildren().get(i);
            VBox infoBox = (VBox) pista.getChildren().get(0);
            StackPane pistaVisual = (StackPane) infoBox.getChildren().get(0);
            ImageView carro = (ImageView) pistaVisual.getChildren().get(pistaVisual.getChildren().size() - 1);
            Label voltasLabel = (Label) infoBox.getChildren().get(1);
            int id = i + 1;
            new Thread(new CorredorFX(carro, voltasLabel, id)).start();
        }
    }

    private void log(String msg) {
        javafx.application.Platform.runLater(() -> logConsole.appendText(msg + "\n"));
    }

    class CorredorFX implements Runnable {
        private ImageView carro;
        private Label voltasLabel;
        private int id;
        private Random random = new Random();
        private int voltas = 0;
        private double distanciaPercorrida = 0;

        public CorredorFX(ImageView carro, Label voltasLabel, int id) {
            this.carro = carro;
            this.voltasLabel = voltasLabel;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (voltas < VOLTAS) {
                    double passo = (random.nextInt(3) + 3) * PASSO;
                    distanciaPercorrida += passo;

                    moverCarro(passo);

                    if (distanciaPercorrida >= LARGURA_TELA - 150) {
                        voltas++;
                        distanciaPercorrida = 0;
                        atualizarVoltas();
                        efeitoVolta();
                        log("Carro " + id + " completou a volta " + voltas);
                        moverCarro(40);
                    }

                    Thread.sleep(900);
                }

                log("Carro " + id + " terminou a corrida!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void moverCarro(double distancia) {
            javafx.application.Platform.runLater(() -> {
                TranslateTransition tt = new TranslateTransition(Duration.millis(600), carro);
                tt.setByX(distancia);
                tt.setInterpolator(Interpolator.EASE_IN);
                tt.play();
            });
        }

        private void atualizarVoltas() {
            javafx.application.Platform.runLater(() -> {
                voltasLabel.setText("Voltas: " + voltas);
                carro.setTranslateX(0);
            });
        }

        private void efeitoVolta() {
            javafx.application.Platform.runLater(() -> {
                Rectangle brilho = new Rectangle(100, 100, Color.YELLOW);
                brilho.setOpacity(0.7);
                brilho.setArcWidth(20);
                brilho.setArcHeight(20);
                StackPane parent = (StackPane) carro.getParent();
                parent.getChildren().add(brilho);

                FadeTransition fade = new FadeTransition(Duration.seconds(1), brilho);
                fade.setFromValue(0.7);
                fade.setToValue(0);
                fade.setOnFinished(e -> parent.getChildren().remove(brilho));
                fade.play();
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
