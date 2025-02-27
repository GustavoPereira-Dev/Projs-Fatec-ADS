package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class CozinhaGUI extends JFrame {
    private Semaphore semaforo = new Semaphore(1);
    private JTextArea consoleArea = new JTextArea();
    private JButton iniciarButton = new JButton("Iniciar Cozinha");

    public CozinhaGUI() {
        setTitle("Simulador de Cozinha");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);
        add(scrollPane, BorderLayout.CENTER);
        add(iniciarButton, BorderLayout.SOUTH);

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarCozinha();
            }
        });
    }

    private void iniciarCozinha() {
        for (int i = 1; i <= 5; i++) {
            new Cozinha(semaforo, i).start();
        }
    }

    private void atualizarConsole(String mensagem) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                consoleArea.append(mensagem + "\n");
            }
        });
    }

    class Cozinha extends Thread {
        private Semaphore semaforo;
        private int id;

        public Cozinha(Semaphore semaforo, int id) {
            this.semaforo = semaforo;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                cozinhar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void cozinhar() {
            double cozimentoMin, cozimentoMax;
            if (id % 2 != 0) {
                cozimentoMin = 0.5;
                cozimentoMax = 0.8;
            } else {
                cozimentoMin = 0.6;
                cozimentoMax = 1.2;
            }
            long tempoTotal = (long) ((cozimentoMin + (cozimentoMax - cozimentoMin) * Math.random()) * 1000);

            atualizarConsole("Prato " + id + " começou a ser feito");
            long tempo = 0;
            try {
                do {
                    Thread.sleep(100);
                    int percentual = (int) ((tempo / (double) tempoTotal) * 100);
                    atualizarConsole("Prato " + id + ": Porcentagem restante de cozimento: " + percentual + "%");
                    tempo += 100;
                } while (tempo < tempoTotal);

                semaforo.acquire();
                atualizarConsole("Prato " + id + " iniciando a entrega...");
                Thread.sleep(500);
                atualizarConsole("Prato " + id + ": entrega concluída!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CozinhaGUI().setVisible(true);
            }
        });
    }
}
