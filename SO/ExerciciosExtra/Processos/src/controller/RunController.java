package controller;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class RunController implements ActionListener {
    private JTextField textField;
    private JFrame frame;

    public RunController(JTextField textField, JFrame frame) {
        this.textField = textField;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = textField.getText();
        File file = new File(command);
        if (file.exists() && !file.isDirectory()) {
            try {
                Runtime.getRuntime().exec(command);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao executar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Arquivo inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
