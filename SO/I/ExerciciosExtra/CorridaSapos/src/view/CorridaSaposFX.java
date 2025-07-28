package view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class CorridaSaposFX extends Application {
    private static final int NUM_SAPOS = 5;

    private TextArea consoleArea;
    private Pane corridaPane;
    private Button iniciarButton;
    private ImageView[] saposView = new ImageView[NUM_SAPOS];
    private Line linhaChegada;

    // Matiz para colorir cada sapo (-1.0 a 1.0)
    private final double[] hueColors = { 0.3, 0.1, -0.5, 0.9, 0.2 }; // verde, laranja, azul, rosa, amarelo

    private double distanciaFinal;
    private double tamanhoMaxPulo;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Corrida de Sapos");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        consoleArea = new TextArea();
        consoleArea.setEditable(false);
        consoleArea.setPrefHeight(150);

        corridaPane = new Pane();
        corridaPane.setPrefHeight(300);
        corridaPane.setStyle("-fx-background-color: #ccffcc; -fx-border-color: #228B22;");

        Image sapoImg = new Image(getClass().getResourceAsStream("/frog.png"), 40, 40, true, true);

        for (int i = 0; i < NUM_SAPOS; i++) {
            ImageView sapo = new ImageView(sapoImg);
            sapo.setLayoutX(0);
            sapo.setLayoutY(30 + i * 50);

            ColorAdjust efeito = new ColorAdjust();
            efeito.setHue(hueColors[i]);
            sapo.setEffect(efeito);

            saposView[i] = sapo;
            corridaPane.getChildren().add(sapo);
        }

        // Linha de chegada
        linhaChegada = new Line();
        linhaChegada.setStartY(0);
        linhaChegada.setEndY(300);
        linhaChegada.setStrokeWidth(3);
        linhaChegada.setStroke(Color.BLACK);
        corridaPane.getChildren().add(linhaChegada);

        iniciarButton = new Button("Iniciar Corrida");
        iniciarButton.setOnAction(e -> iniciarCorrida());

        root.getChildren().addAll(new Label("Corrida:"), corridaPane, iniciarButton, new Label("Console:"), consoleArea);

        Scene scene = new Scene(root, 750, 550);

        // Atualiza DISTANCIA FINAL sempre que a tela redimensiona
        scene.widthProperty().addListener((obs, oldVal, newVal) -> atualizarTamanhoCorrida(scene.getWidth()));

        // Inicia com largura padrão
        atualizarTamanhoCorrida(scene.getWidth());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void atualizarTamanhoCorrida(double largura) {
        distanciaFinal = largura - 120; // margem de segurança
        tamanhoMaxPulo = distanciaFinal * 0.08; // até 8% da pista
        linhaChegada.setStartX(distanciaFinal);
        linhaChegada.setEndX(distanciaFinal);
    }

    private void iniciarCorrida() {
        consoleArea.clear();
        for (int i = 0; i < NUM_SAPOS; i++) {
            int finalI = i;
            saposView[i].setTranslateX(0); // reset
            new Thread(() -> correr(finalI)).start();
        }
    }

    private void correr(int id) {
        Random random = new Random();
        double distancia = 0;

        while (distancia < distanciaFinal) {
            int pulo = random.nextInt((int) tamanhoMaxPulo + 1);
            distancia += pulo;
            double novaPosicao = Math.min(distancia, distanciaFinal);

            final double posicao = novaPosicao;
            final int puloFinal = pulo;
            final double totalDist = distancia;

            Platform.runLater(() -> {
                animarPulo(saposView[id], posicao);
                logConsole("Sapo " + (id + 1) + " pulou " + puloFinal + "cm. Total: " + (int) totalDist + "cm.");
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Platform.runLater(() -> logConsole("🏁 Sapo " + (id + 1) + " chegou ao fim!"));
    }

    private void animarPulo(ImageView sapo, double novaX) {
        TranslateTransition movimento = new TranslateTransition(Duration.millis(300), sapo);
        movimento.setToX(novaX);

        TranslateTransition subir = new TranslateTransition(Duration.millis(150), sapo);
        subir.setByY(-15);

        TranslateTransition descer = new TranslateTransition(Duration.millis(150), sapo);
        descer.setByY(15);

        SequentialTransition pulo = new SequentialTransition(
            new ParallelTransition(movimento, new SequentialTransition(subir, descer))
        );
        pulo.play();
    }

    private void logConsole(String mensagem) {
        consoleArea.appendText(mensagem + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

