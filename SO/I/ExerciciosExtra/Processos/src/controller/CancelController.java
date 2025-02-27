package controller;

import java.awt.event.*;

import javax.swing.JFrame;

public class CancelController implements ActionListener {
    private JFrame frame;

    public CancelController(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
