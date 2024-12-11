package view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class TriatloGUI extends JFrame {
    private static final int NUM_ATLETAS = 25;
    private static final int DISTANCIA_CORRIDA = 3000;
    private static final int DISTANCIA_CICLISMO = 5000;
    private static final int NUM_ARMAS = 5;

    private JTextArea consoleArea;
    private JProgressBar[] progressCorrida;
    private JProgressBar[] progressCiclismo;
    private JButton iniciarButton;
    private Semaphore armas = new Semaphore(NUM_ARMAS);
    private static int[] pontuacaoFinal = new int[NUM_ATLETAS];
    private static int posicaoAtual = 1;
    
    public TriatloGUI() {
        setTitle("Prova de Triatlo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        JPanel progressPanel = new JPanel(new GridLayout(NUM_ATLETAS * 2, 1));
        progressCorrida = new JProgressBar[NUM_ATLETAS];
        progressCiclismo = new JProgressBar[NUM_ATLETAS];
        for (int i = 0; i < NUM_ATLETAS; i++) {
            progressCorrida[i] = new JProgressBar(0, DISTANCIA_CORRIDA);
            progressCorrida[i].setStringPainted(true);
            progressCorrida[i].setString("Atleta " + (i + 1) + " - Corrida");
            progressPanel.add(progressCorrida[i]);

            progressCiclismo[i] = new JProgressBar(0, DISTANCIA_CICLISMO);
            progressCiclismo[i].setStringPainted(true);
            progressCiclismo[i].setString("Atleta " + (i + 1) + " - Ciclismo");
            progressPanel.add(progressCiclismo[i]);
        }

        iniciarButton = new JButton("Iniciar Prova");
        iniciarButton.addActionListener(e -> iniciarProva());

        add(scrollPane, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.NORTH);
        add(iniciarButton, BorderLayout.SOUTH);
    }

    private void iniciarProva() {
        for (int i = 0; i < NUM_ATLETAS; i++) {
            new Thread(new Triatleta(i + 1, armas)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    private void atualizarProgresso(JProgressBar barra, int progresso) {
        SwingUtilities.invokeLater(() -> barra.setValue(progresso));
    }

    class Triatleta implements Runnable {
        private int id;
        private Semaphore armas;
        private Random random = new Random();

        public Triatleta(int id, Semaphore armas) {
            this.id = id;
            this.armas = armas;
        }

        @Override
        public void run() {
            corrida();
            tiroAlvos();
            ciclismo();
        }

        private void corrida() {
            int progresso = 0;
            while (progresso < DISTANCIA_CORRIDA) {
                int distancia = 20 + random.nextInt(6);
                progresso += distancia;
                atualizarProgresso(progressCorrida[id - 1], progresso);
                atualizarConsole("Atleta " + id + " percorreu " + progresso + " metros na corrida.");
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            atualizarConsole("Atleta " + id + " terminou a corrida.");
        }

        private void tiroAlvos() {
            try {
                armas.acquire();
                atualizarConsole("Atleta " + id + " pegou uma arma para tiro ao alvo.");
                int totalPontos = 0;
                for (int i = 0; i < 3; i++) {
                    int pontos = random.nextInt(11);
                    totalPontos += pontos;
                    int tempo = 500 + random.nextInt(2501);
                    try {
                        Thread.sleep(tempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    atualizarConsole("Atleta " + id + " marcou " + pontos + " pontos no tiro " + (i + 1) + ".");
                }
                pontuacaoFinal[id - 1] = totalPontos;
                atualizarConsole("Atleta " + id + " terminou o tiro ao alvo com " + totalPontos + " pontos.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                armas.release();
            }
        }

        private void ciclismo() {
            int progresso = 0;
            while (progresso < DISTANCIA_CICLISMO) {
                int distancia = 30 + random.nextInt(11);
                progresso += distancia;
                atualizarProgresso(progressCiclismo[id - 1], progresso);
                atualizarConsole("Atleta " + id + " percorreu " + progresso + " metros no ciclismo.");
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            atualizarConsole("Atleta " + id + " terminou o ciclismo.");
            pontuacaoFinal[id - 1] += (26 - posicaoAtual) * 10;
            posicaoAtual++;
            if (posicaoAtual == 26) {
                rankingFinal();
            }
        }

        private void rankingFinal() {
            try {
                Thread.sleep(5000); // Tempo para simular fim da prova
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ordenar os atletas por pontuação final
            Integer[] ordem = new Integer[NUM_ATLETAS];
            for (int i = 0; i < NUM_ATLETAS; i++) {
                ordem[i] = i;
            }
            java.util.Arrays.sort(ordem, (a, b) -> Integer.compare(pontuacaoFinal[b], pontuacaoFinal[a]));

            atualizarConsole("Ranking Final:");
            for (int i = 0; i < NUM_ATLETAS; i++) {
                atualizarConsole((i + 1) + "º lugar: Atleta " + (ordem[i] + 1) + " com " + pontuacaoFinal[ordem[i]] + " pontos.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TriatloGUI().setVisible(true));
    }
}
