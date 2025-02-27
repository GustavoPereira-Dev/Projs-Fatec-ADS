package view;

import javax.swing.*;
import java.awt.*;
import controller.CancelController;
import controller.RunController;
import controller.SearchController;

public class RunDialog extends JFrame {
    private JTextField textField;
    private JButton btnOk;
    private JButton btnCancel;
    private JButton btnBrowse;

    public RunDialog() {
        setTitle("Executar");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 1));

        JLabel lblPrompt = new JLabel("Digite o caminho do executável ou clique em procurar:");
        panel.add(lblPrompt);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnOk = new JButton("OK");
        buttonPanel.add(btnOk);

        btnCancel = new JButton("Cancelar");
        buttonPanel.add(btnCancel);

        btnBrowse = new JButton("Procurar");
        buttonPanel.add(btnBrowse);

        // Adding ActionListeners
        btnOk.addActionListener(new RunController(textField, this));
        btnCancel.addActionListener(new CancelController(this));
        btnBrowse.addActionListener(new SearchController(textField));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RunDialog frame = new RunDialog();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
