package lista2.view;

import java.io.IOException;

import lista2.controller.Exercicio5Controller;

public class Principal {
    public static void main(String[] args) {
        Exercicio5Controller controller = new Exercicio5Controller();
        try {
            controller.novoCadastro(41, 60, 8000.00);
            controller.novoCadastro(31, 40, 5000.00);
            controller.novoCadastro(21, 30, 3000.00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
