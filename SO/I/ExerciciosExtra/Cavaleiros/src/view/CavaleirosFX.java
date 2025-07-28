package view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CavaleirosFX extends Application {
    private static final int NUM_CAVALEIROS = 4;
    private static final int DISTANCIA_CORREDOR = 2000;
    private static final int DISTANCIA_TOCHA = 500;
    private static final int DISTANCIA_PEDRA = 1500;

    private Pane pista;
    private TextArea console;
    private double larguraTela;
    private double alturaTela;
    private double escala;
    private ImageView[] cavaleiros = new ImageView[NUM_CAVALEIROS];
    private Semaphore semaphore = new Semaphore(1);
    private boolean tochaColetada = false;
    private boolean pedraColetada = false;
    private boolean[] portasAbertas = new boolean[NUM_CAVALEIROS];
    private boolean[] semMonstro = {false, false, false, false};
    private Random rand = new Random();

    private Rectangle[] portas = new Rectangle[NUM_CAVALEIROS];

    @Override
    public void start(Stage stage) {
    	semMonstro[((int) Math.random() * 100) % 4] = true;
    	
        larguraTela = 1000;
        alturaTela = 600;
        escala = larguraTela / DISTANCIA_CORREDOR;

        pista = new Pane();
        pista.setPrefSize(larguraTela, alturaTela - 150);
        pista.setStyle("-fx-background-color: linear-gradient(to right, #222, #333);");

        VBox root = new VBox(10);
        console = new TextArea();
        console.setPrefHeight(150);
        console.setEditable(false);

        Button iniciar = new Button("Iniciar Corrida");
        iniciar.setOnAction(e -> iniciarCorrida());
        iniciar.setMaxWidth(Double.MAX_VALUE);

        root.getChildren().addAll(pista, iniciar, console);
        root.setAlignment(Pos.CENTER);

        adicionarObstaculos();
        adicionarCavaleiros();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Corrida dos Cavaleiros");
        stage.show();
    }

    private void adicionarObstaculos() {
        Rectangle tocha = new Rectangle(10, alturaTela - 150);
        tocha.setFill(Color.ORANGE);
        tocha.setX(DISTANCIA_TOCHA * escala);
        tocha.setId("tocha");
        pista.getChildren().add(tocha);

        Rectangle pedra = new Rectangle(10, alturaTela - 150);
        pedra.setFill(Color.YELLOW);
        pedra.setX(DISTANCIA_PEDRA * escala);
        pedra.setId("pedra");
        pista.getChildren().add(pedra);

        for (int i = 0; i < 4; i++) {
            Rectangle porta = new Rectangle(20, 80, Color.SADDLEBROWN);
            porta.setY(20 + i * 100);
            porta.setX(DISTANCIA_CORREDOR * escala + 30);
            pista.getChildren().add(porta);
            portas[i] = porta;
        }
    }

    private void adicionarCavaleiros() {
        Color[] cores = {Color.BLUE, Color.RED, Color.GREEN, Color.PURPLE};
        for (int i = 0; i < NUM_CAVALEIROS; i++) {
            ImageView cavaleiro = new ImageView(new Image(getClass().getResourceAsStream("/cavaleiro.png"), 40, 40, true, true));
            cavaleiro.setFitWidth(40);
            cavaleiro.setFitHeight(40);
            cavaleiro.setX(0);
            cavaleiro.setY(i * 100 + 20);
            cavaleiro.setStyle("-fx-effect: dropshadow(gaussian, " + toHex(cores[i]) + ", 10, 0.5, 0, 0);");
            cavaleiros[i] = cavaleiro;
            pista.getChildren().add(cavaleiro);
        }
    }

    private void iniciarCorrida() {
        for (int i = 0; i < NUM_CAVALEIROS; i++) {
            final int id = i;
            new Thread(() -> moverCavaleiro(id)).start();
        }
    }

    private void moverCavaleiro(int id) {
        int distancia = 0;
        int min = 2, max = 4;
        boolean temTocha = false;

        while (distancia < DISTANCIA_CORREDOR) {
            if (!tochaColetada && distancia >= DISTANCIA_TOCHA) {
                synchronized (this) {
                    if (!tochaColetada) {
                        tochaColetada = true;
                        temTocha = true;
                        min += 2;
                        max += 2;
                        log("Cavaleiro " + (id + 1) + " pegou a tocha!");
                        animarBrilho(cavaleiros[id]);
                    }
                }
            }
            if (!pedraColetada && distancia >= DISTANCIA_PEDRA && !temTocha) {
                synchronized (this) {
                    if (!pedraColetada) {
                        pedraColetada = true;
                        min += 2;
                        max += 2;
                        log("Cavaleiro " + (id + 1) + " pegou a pedra!");
                        animarPiscar(cavaleiros[id]);
                    }
                }
            }

            int passo = rand.nextInt(max - min + 1) + min;
            distancia += passo;
            final double x = Math.min(distancia * escala, larguraTela - 50);

            javafx.application.Platform.runLater(() -> {
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), cavaleiros[id]);
                tt.setToX(x);
                tt.play();
            });

            int finalDistancia = distancia;
            log("Cavaleiro " + (id + 1) + " avançou " + passo + "m. Total: " + finalDistancia + "m.");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log("Cavaleiro " + (id + 1) + " terminou a corrida.");
        tentarPorta(id);
    }

    private void tentarPorta(int id) {
        try {
            semaphore.acquire();
            int escolha;
            do {
                escolha = rand.nextInt(4);
            } while (portasAbertas[escolha]);

            portasAbertas[escolha] = true;
            final int portaEscolhida = escolha;

            if (semMonstro[escolha]) {
                log("Cavaleiro " + (id + 1) + " entrou na porta " + (escolha + 1) + " e ESCAPOU!");
                javafx.application.Platform.runLater(() -> portas[portaEscolhida].setFill(Color.LIMEGREEN));
            } else {
                log("Cavaleiro " + (id + 1) + " entrou na porta " + (escolha + 1) + " e foi DEVORADO por monstros!");
                javafx.application.Platform.runLater(() -> {
                    portas[portaEscolhida].setFill(Color.RED);
                    animarTremor(portas[portaEscolhida]);
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void animarBrilho(ImageView cavaleiro) {
        javafx.application.Platform.runLater(() -> {
            FadeTransition ft = new FadeTransition(Duration.millis(100), cavaleiro);
            ft.setFromValue(1.0);
            ft.setToValue(0.2);
            ft.setCycleCount(6);
            ft.setAutoReverse(true);
            ft.play();
        });
    }

    private void animarPiscar(ImageView cavaleiro) {
        javafx.application.Platform.runLater(() -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), cavaleiro);
            st.setByX(0.5);
            st.setByY(0.5);
            st.setCycleCount(4);
            st.setAutoReverse(true);
            st.play();
        });
    }

    private void animarTremor(Rectangle porta) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(80), porta);
        tt.setByX(5);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.play();
    }

    private void log(String msg) {
        javafx.application.Platform.runLater(() -> console.appendText(msg + "\n"));
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch();
    }
}
