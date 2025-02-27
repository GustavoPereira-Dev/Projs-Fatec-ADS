package view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class AeroportoGUI extends JFrame {
    private static final int NUM_AVIOES = 12;
    private static final int NUM_PISTAS = 2;
    private JTextArea consoleArea;
    private JProgressBar[] progressBars;
    private JButton iniciarButton;
    private Semaphore[] semaforoDecolagem = new Semaphore[NUM_PISTAS];
    private Semaphore semaforoDecolagemArea = new Semaphore(2);

    public AeroportoGUI() {
        setTitle("Aeroporto - Decolagem de Aviões");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        JPanel progressPanel = new JPanel(new GridLayout(NUM_AVIOES, 1));
        progressBars = new JProgressBar[NUM_AVIOES];
        for (int i = 0; i < NUM_AVIOES; i++) {
            progressBars[i] = new JProgressBar(0, 4000);
            progressBars[i].setStringPainted(true);
            progressBars[i].setString("Avião " + (i + 1));
            progressPanel.add(progressBars[i]);
        }

        iniciarButton = new JButton("Iniciar Decolagens");
        iniciarButton.addActionListener(e -> iniciarDecolagens());

        add(scrollPane, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.NORTH);
        add(iniciarButton, BorderLayout.SOUTH);

        for (int i = 0; i < NUM_PISTAS; i++) {
            semaforoDecolagem[i] = new Semaphore(1);
        }
    }

    private void iniciarDecolagens() {
        for (int i = 0; i < NUM_AVIOES; i++) {
            int pista = (int) (Math.random() * NUM_PISTAS);
            new Thread(new Aeroporto(i + 1, pista)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    private void atualizarProgresso(int idAviao, int progresso) {
        SwingUtilities.invokeLater(() -> progressBars[idAviao - 1].setValue(progresso));
    }

    class Aeroporto implements Runnable {
        private int id;
        private int pista;
        private Semaphore[] afastamento = new Semaphore[NUM_PISTAS];
        private Semaphore[] decolagem = new Semaphore[NUM_PISTAS];

        public Aeroporto(int id, int pista) {
            this.id = id;
            this.pista = pista;
        }

        @Override
        public void run() {
            try {
                manobrar();
                taxiar();
                decolar();
                afastar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoDecolagem[pista].release();
                semaforoDecolagemArea.release();
            }
        }

        private void manobrar() throws InterruptedException {
            semaforoDecolagem[pista].acquire();
            int duracao = ((int) (Math.random() * 4) + 3) * 100;
            atualizarConsole("Avião " + id + " está fazendo manobragem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            Thread.sleep(duracao);
            atualizarConsole("Avião " + id + " concluiu a manobragem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            atualizarProgresso(id, duracao);
        }

        private void taxiar() throws InterruptedException {
            int duracao = ((int) (Math.random() * 6) + 5) * 100;
            atualizarConsole("Avião " + id + " está fazendo taxiagem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            Thread.sleep(duracao);
            atualizarConsole("Avião " + id + " concluiu a taxiagem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            atualizarProgresso(id, 1000 + duracao);
        }

        private void decolar() throws InterruptedException {
            semaforoDecolagemArea.acquire();
            int duracao = ((int) (Math.random() * 3) + 6) * 100;
            atualizarConsole("Avião " + id + " está fazendo decolagem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            Thread.sleep(duracao);
            atualizarConsole("Avião " + id + " concluiu a decolagem na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            atualizarProgresso(id, 2000 + duracao);
        }

        private void afastar() throws InterruptedException {
            int duracao = ((int) (Math.random() * 5) + 3) * 100;
            atualizarConsole("Avião " + id + " está fazendo afastamento na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            Thread.sleep(duracao);
            atualizarConsole("Avião " + id + " concluiu o afastamento na pista " + (pista == 0 ? "Norte" : "Sul") + "...");
            atualizarProgresso(id, 3000 + duracao);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AeroportoGUI().setVisible(true));
    }
}
