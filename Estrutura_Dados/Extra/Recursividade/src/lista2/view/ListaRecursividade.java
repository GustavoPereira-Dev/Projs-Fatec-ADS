package lista2.view;

import lista2.controller.ListaRecursividadeController;
import lista2.controller.UtilController;
import javax.swing.JOptionPane;

public class ListaRecursividade {

    public static void main(String[] args) {
        ListaRecursividadeController l2 = new ListaRecursividadeController();
        UtilController u2 = new UtilController();
        int opc;

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Multiplicação por soma\n" +
                "2. Divisão por subtração\n" +
                "3. Somatória de pares em vetor\n" +
                "4. Fatorial duplo\n" +
                "5. Máximo divisor comum (MDC)\n" +
                "0. Sair"
            ));

            switch (opc) {
                case 1:
                    int n1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro número:"));
                    int n2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo número:"));
                    int multiplicacao = l2.multiplicacaoPorSoma(n1, n2);
                    JOptionPane.showMessageDialog(null, "A multiplicação por soma é: " + multiplicacao);
                    break;
                case 2:
                    int dividendo = Integer.parseInt(JOptionPane.showInputDialog("Informe o dividendo:"));
                    int divisor = Integer.parseInt(JOptionPane.showInputDialog("Informe o divisor:"));
                    int resto = l2.divisaoPorSub(dividendo, divisor);
                    JOptionPane.showMessageDialog(null, "O resto da divisão por subtração é: " + resto);
                    break;
                case 3:
                    int[] vetor = u2.carregarVetor(10);
                    int somatoriaPares = l2.somatoriaPares(vetor, vetor.length - 1);
                    JOptionPane.showMessageDialog(null, "A somatória de pares no vetor é: " + somatoriaPares);
                    break;
                case 4:
                    int t = Integer.parseInt(JOptionPane.showInputDialog("Informe um número ímpar:"));
                    if (u2.validarFatorial(t)) {
                        int fatorialDuplo = l2.fatorialDuplo(t);
                        JOptionPane.showMessageDialog(null, "O fatorial duplo é: " + fatorialDuplo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Número informado não é ímpar!");
                    }
                    break;
                case 5:
                    int x = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro número:"));
                    int y = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo número:"));
                    int mdc = l2.mdc(x, y);
                    JOptionPane.showMessageDialog(null, "O MDC é: " + mdc);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opc != 0);
    }
}
