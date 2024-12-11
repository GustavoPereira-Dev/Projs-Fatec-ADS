package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CorredorGUI extends JFrame {
    private static final int NUM_CORREDORES = 4;
    private static final int DISTANCIA_CORREDOR = 200;
    private JTextArea consoleArea;
    private JButton iniciarButton;
    private Semaphore semaforo;

    public CorredorGUI() {
        setTitle("Simulador de Corredores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        iniciarButton = new JButton("Iniciar");
        iniciarButton.addActionListener(e -> iniciarCorrida());

        add(scrollPane, BorderLayout.CENTER);
        add(iniciarButton, BorderLayout.SOUTH);

        semaforo = new Semaphore(1);
    }

    private void iniciarCorrida() {
        for (int i = 1; i <= NUM_CORREDORES; i++) {
            new Thread(new Corredor(semaforo, i)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    class Corredor implements Runnable {
        private Semaphore semaforo;
        private int id;
        private int distanciaPecorrida;
        private Random r = new Random();

        public Corredor(Semaphore semaforo, int id) {
            this.semaforo = semaforo;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                corredor();
                semaforo.acquire();
                abrirPorta();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }

        private void corredor() throws InterruptedException {
            while (distanciaPecorrida < DISTANCIA_CORREDOR) {
                distanciaPecorrida += r.nextInt(3) + 4; // Adiciona de 4 a 6 metros
                Thread.sleep(1000);
                atualizarConsole("Corredor Nº " + id + " percorreu " + distanciaPecorrida + " metros");
            }
            atualizarConsole("Corredor Nº " + id + " finalizou o corredor");
        }

        private void abrirPorta() throws InterruptedException {
            atualizarConsole("Corredor Nº " + id + " está abrindo e cruzando a porta");
            Thread.sleep((r.nextInt(2) + 1) * 1000);
            atualizarConsole("Corredor Nº " + id + " cruzou a porta!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CorredorGUI().setVisible(true));
    }
}
