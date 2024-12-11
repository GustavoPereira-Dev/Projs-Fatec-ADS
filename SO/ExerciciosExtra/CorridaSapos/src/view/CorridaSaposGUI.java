package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CorridaSaposGUI extends JFrame {
    private static final int NUM_SAPOS = 5;
    private static final int DISTANCIA_FINAL = 50;
    private static final int TAMANHO_MAX_PULO = 5;

    private JTextArea consoleArea;
    private JProgressBar[] progressBars;
    private JButton iniciarButton;

    public CorridaSaposGUI() {
        setTitle("Corrida de Sapos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        JPanel progressPanel = new JPanel(new GridLayout(NUM_SAPOS, 1));
        progressBars = new JProgressBar[NUM_SAPOS];
        for (int i = 0; i < NUM_SAPOS; i++) {
            progressBars[i] = new JProgressBar(0, DISTANCIA_FINAL);
            progressBars[i].setStringPainted(true);
            progressBars[i].setString("Sapo " + (i + 1));
            progressPanel.add(progressBars[i]);
        }

        iniciarButton = new JButton("Iniciar Corrida");
        iniciarButton.addActionListener(e -> iniciarCorrida());

        add(scrollPane, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.NORTH);
        add(iniciarButton, BorderLayout.SOUTH);
    }

    private void iniciarCorrida() {
        for (int i = 0; i < NUM_SAPOS; i++) {
            new Thread(new Sapo(i + 1)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    private void atualizarProgresso(int idSapo, int distancia) {
        SwingUtilities.invokeLater(() -> progressBars[idSapo - 1].setValue(distancia));
    }

    class Sapo implements Runnable {
        private int id;
        private int distanciaPercorrida;
        private Random random;

        public Sapo(int id) {
            this.id = id;
            this.distanciaPercorrida = 0;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (distanciaPercorrida < DISTANCIA_FINAL) {
                int pulo = random.nextInt(TAMANHO_MAX_PULO + 1);
                distanciaPercorrida += pulo;
                atualizarProgresso(id, Math.min(distanciaPercorrida, DISTANCIA_FINAL));
                atualizarConsole("Sapo " + id + " pulou " + pulo + " metros. Distância percorrida: " + distanciaPercorrida + " metros.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            atualizarConsole("Sapo " + id + " chegou ao fim!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CorridaSaposGUI().setVisible(true));
    }
}
