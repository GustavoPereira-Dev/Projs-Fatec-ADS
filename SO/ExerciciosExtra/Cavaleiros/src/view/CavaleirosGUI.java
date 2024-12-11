package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CavaleirosGUI extends JFrame {
    private static final int NUM_CAVALEIROS = 4;
    private static final int DISTANCIA_CORREDOR = 2000;
    private static final int DISTANCIA_TOCHA = 500;
    private static final int DISTANCIA_PEDRA = 1500;

    private JTextArea consoleArea;
    private JProgressBar[] progressBars;
    private JButton iniciarButton;
    private Semaphore semaphore;

    private static boolean tochaColetada = false;
    private static boolean pedraColetada = false;
    private static boolean[] portasAbertas = new boolean[4];
    private static boolean[] semMonstro = {false, false, true, false};

    public CavaleirosGUI() {
        setTitle("Corrida dos Cavaleiros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        JPanel progressPanel = new JPanel(new GridLayout(NUM_CAVALEIROS, 1));
        progressBars = new JProgressBar[NUM_CAVALEIROS];
        for (int i = 0; i < NUM_CAVALEIROS; i++) {
            progressBars[i] = new JProgressBar(0, DISTANCIA_CORREDOR);
            progressBars[i].setStringPainted(true);
            progressBars[i].setString("Cavaleiro " + (i + 1));
            progressPanel.add(progressBars[i]);
        }

        iniciarButton = new JButton("Iniciar Corrida");
        iniciarButton.addActionListener(e -> iniciarCorrida());

        add(scrollPane, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.NORTH);
        add(iniciarButton, BorderLayout.SOUTH);

        semaphore = new Semaphore(1);
    }

    private void iniciarCorrida() {
        for (int i = 0; i < NUM_CAVALEIROS; i++) {
            new Thread(new Cavaleiros(i + 1, semaphore)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    private void atualizarProgresso(int idCavaleiro, int distancia) {
        SwingUtilities.invokeLater(() -> progressBars[idCavaleiro - 1].setValue(distancia));
    }

    class Cavaleiros implements Runnable {
        private int id;
        private int distanciaPercorrida;
        private int distanciaMinima;
        private int distanciaMaxima;
        private boolean tochaPega;
        private Random random;
        private Semaphore semaphore;

        public Cavaleiros(int id, Semaphore semaphore) {
            this.id = id;
            this.distanciaPercorrida = 0;
            this.distanciaMinima = 2;
            this.distanciaMaxima = 4;
            this.tochaPega = false;
            this.random = new Random();
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                while (distanciaPercorrida < DISTANCIA_CORREDOR) {
                    if (!tochaColetada && distanciaPercorrida >= DISTANCIA_TOCHA) {
                        tochaColetada = true;
                        tochaPega = true;
                        distanciaMinima += 2;
                        distanciaMaxima += 2;
                        atualizarConsole("Cavaleiro " + id + " pegou a tocha!");
                    }
                    if (!pedraColetada && distanciaPercorrida >= DISTANCIA_PEDRA && !tochaPega) {
                        pedraColetada = true;
                        distanciaMinima += 2;
                        distanciaMaxima += 2;
                        atualizarConsole("Cavaleiro " + id + " pegou a pedra!");
                    }
                    int pulo = random.nextInt(distanciaMaxima - distanciaMinima + 1) + distanciaMinima;
                    distanciaPercorrida += pulo;
                    atualizarProgresso(id, Math.min(distanciaPercorrida, DISTANCIA_CORREDOR));
                    atualizarConsole("Cavaleiro " + id + " avançou " + pulo + " metros. Distância total: " + distanciaPercorrida + " metros.");

                    Thread.sleep(50);
                }
                atualizarConsole("Cavaleiro " + id + " terminou a corrida.");
                entrarPorta();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void entrarPorta() throws InterruptedException {
            semaphore.acquire();
            try {
                int portaEscolhida;
                do {
                    portaEscolhida = random.nextInt(4);
                } while (portasAbertas[portaEscolhida]);

                portasAbertas[portaEscolhida] = true;
                if (semMonstro[portaEscolhida]) {
                    atualizarConsole("Cavaleiro " + id + " encontrou a saída segura!");
                } else {
                    atualizarConsole("Cavaleiro " + id + " encontrou monstros atrás da porta " + portaEscolhida + "...");
                }
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CavaleirosGUI().setVisible(true));
    }
}
