package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CruzamentoGUI extends JFrame {
    private static final int NUM_CARROS = 10;
    private JTextArea consoleArea;
    private JButton iniciarButton;
    private Semaphore semaforo;
    private static Random rn = new Random();
    private static final String[] sentidos = {"leste", "sul", "oeste", "norte"};
    private static String sentidoAtual;

    public CruzamentoGUI() {
        setTitle("Simulador de Cruzamento");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        iniciarButton = new JButton("Iniciar");
        iniciarButton.addActionListener(e -> iniciarCruzamento());

        add(scrollPane, BorderLayout.CENTER);
        add(iniciarButton, BorderLayout.SOUTH);

        semaforo = new Semaphore(1);
    }

    private void iniciarCruzamento() {
        for (int i = 0; i < NUM_CARROS; i++) {
            String sentido = sentidos[rn.nextInt(sentidos.length)];
            new Thread(new Carro(semaforo, sentido, i + 1)).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(() -> consoleArea.append(mensagem + "\n"));
    }

    class Carro implements Runnable {
        private Semaphore semaforo;
        private String sentido;
        private int id;

        public Carro(Semaphore semaforo, String sentido, int id) {
            this.semaforo = semaforo;
            this.sentido = sentido;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                semaforo.acquire();
                String mensagem = "O carro " + id + " está passando o cruzamento e indo pelo sentido " + sentido;
                atualizarConsole(mensagem);
                Thread.sleep(500); // Simula o tempo necessário para o carro cruzar
                mensagem = "O carro " + id + " passou o cruzamento pelo sentido " + sentido;
                atualizarConsole(mensagem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CruzamentoGUI().setVisible(true));
    }
}
