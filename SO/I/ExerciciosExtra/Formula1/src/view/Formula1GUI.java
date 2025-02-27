package view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class Formula1GUI extends JFrame {
    private static final int NUM_CARROS = 14;
    private JTextArea consoleArea;
    private JButton iniciarButton;
    private Semaphore pista;
    private Semaphore[] equipe;

    private static final String[] nomesEquipes = {"Ferrari", "McLaren", "Mercedes", "Red Bull", "Alpine", "Aston", "Williams"};
    private static int[][] melhoresVoltas = new int[2][NUM_CARROS];
    private static int contConcluidos = 0;

    public Formula1GUI() {
        setTitle("Treino de Fórmula 1");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        iniciarButton = new JButton("Iniciar Treino");
        iniciarButton.addActionListener(e -> iniciarTreino());

        add(scrollPane, BorderLayout.CENTER);
        add(iniciarButton, BorderLayout.SOUTH);

        pista = new Semaphore(5); // Apenas 5 carros na pista simultaneamente
        equipe = new Semaphore[nomesEquipes.length];
        for (int i = 0; i < nomesEquipes.length; i++) {
            equipe[i] = new Semaphore(1); // Apenas um carro por equipe na pista
        }
    }

    private void iniciarTreino() {
        for (int escuderia = 0; escuderia < nomesEquipes.length; escuderia++) {
            for (int carro = 0; carro < 2; carro++) {
                new Thread(new Carro(pista, equipe, escuderia, carro)).start();
            }
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    private void atualizarRanking() {
        int i, j;
        int[] aux = new int[2];

        for (i = 0; i < NUM_CARROS; i++) {
            for (j = 0; j < NUM_CARROS; j++) {
                if (melhoresVoltas[1][i] < melhoresVoltas[1][j]) {
                    aux[0] = melhoresVoltas[0][i];
                    aux[1] = melhoresVoltas[1][i];
                    melhoresVoltas[0][i] = melhoresVoltas[0][j];
                    melhoresVoltas[1][i] = melhoresVoltas[1][j];
                    melhoresVoltas[0][j] = aux[0];
                    melhoresVoltas[1][j] = aux[1];
                }
            }
        }

        atualizarConsole("Ranking de pontuação:");
        for (i = 0; i < NUM_CARROS; i++) {
            atualizarConsole("Piloto nº " + (melhoresVoltas[0][i] + 1) + " ficou em " + (i + 1) + "º com a sua melhor volta em " + (melhoresVoltas[1][i] / 100) + " segundos");
        }
    }

    class Carro implements Runnable {
        private Semaphore pista;
        private Semaphore[] equipe;
        private int escuderia;
        private int id;
        private int distanciaPecorrida = 0;
        private int tempoVolta = 0;
        private int voltaAtual = 1;
        private int[] tempoVoltas = new int[3];

        public Carro(Semaphore pista, Semaphore[] equipe, int escuderia, int id) {
            this.pista = pista;
            this.equipe = equipe;
            this.escuderia = escuderia;
            this.id = id + (escuderia * 2);
        }

        @Override
        public void run() {
            try {
                equipe[escuderia].acquire();
                pista.acquire();
                atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") entrou na pista");
                corrida();
                melhorVolta();
                Thread.sleep(1000);
                atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") teve sua melhor volta com " + (melhoresVoltas[1][id] / 100) + " segundos!");
                contConcluidos++;
                if (contConcluidos == NUM_CARROS) {
                    atualizarRanking();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                equipe[escuderia].release();
                pista.release();
            }
        }

        private void corrida() throws InterruptedException {
            do {
                distanciaPecorrida += (int) (Math.random() * 100) + 10;
                atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") percorreu " + distanciaPecorrida + " metros até agora");
                Thread.sleep(500);
                tempoVolta += 500;
                if (distanciaPecorrida > (voltaAtual * 1000)) {
                    atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") completou a " + voltaAtual + "ª volta em " + (tempoVolta / 100) + " segundos");
                    tempoVoltas[voltaAtual - 1] = tempoVolta;
                    tempoVolta = 0;
                    voltaAtual++;
                    if (voltaAtual < 4) {
                        atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") está na " + voltaAtual + "ª volta agora");
                    }
                }
            } while (distanciaPecorrida < 3000);
            atualizarConsole("O piloto nº " + (id + 1) + " (" + nomesEquipes[escuderia] + ") finalizou a corrida agora");
            Thread.sleep(1000);
        }

        private void melhorVolta() {
            int melhorVolta = tempoVoltas[0];
            for (int i = 1; i < 3; i++) {
                if (tempoVoltas[i] < melhorVolta) {
                    melhorVolta = tempoVoltas[i];
                }
            }
            melhoresVoltas[0][id] = id;
            melhoresVoltas[1][id] = melhorVolta;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formula1GUI().setVisible(true));
    }
}
