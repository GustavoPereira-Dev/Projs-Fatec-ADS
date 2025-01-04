package lista3.view;

import lista3.controller.ListaRecursividadeController;
import lista3.controller.UtilController;
import javax.swing.JOptionPane;

public class ListaRecursividade {

    public static void main(String[] args) {
        ListaRecursividadeController l3 = new ListaRecursividadeController();
        UtilController u3 = new UtilController();
        int opc;

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Quantidade de dígitos\n" +
                "2. Repetição de número\n" +
                "3. Inversão de caracteres\n" +
                "4. Conversão para binário\n" +
                "5. Cálculo de Fibonacci\n" +
                "6. Somar sequência de fatoriais\n" +
                "0. Sair"
            ));

            switch (opc) {
                case 1:
                    int n = Integer.parseInt(JOptionPane.showInputDialog("Informe um número:"));
                    int digitos = l3.quantidadeDigitos(n, 1);
                    JOptionPane.showMessageDialog(null, "A quantidade de dígitos é: " + digitos);
                    break;
                case 2:
                    int n1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o número:"));
                    int n2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o dígito a ser contado:"));
                    if (u3.validarNumeroComp(n1) && u3.validarNumeroRep(n2)) {
                        int repeticao = l3.repeticaoNumero(n1, n2);
                        JOptionPane.showMessageDialog(null, "O dígito " + n2 + " aparece " + repeticao + " vezes.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Entrada inválida!");
                    }
                    break;
                case 3:
                    String texto = JOptionPane.showInputDialog("Informe o texto:");
                    String invertido = l3.inversaoCaracteres(texto, texto.length() - 1);
                    JOptionPane.showMessageDialog(null, "O texto invertido é: " + invertido);
                    break;
                case 4:
                    int dec = Integer.parseInt(JOptionPane.showInputDialog("Informe um número decimal:"));
                    if (u3.validarNumeroDec(dec)) {
                        String binario = l3.conversaoBinaria(dec);
                        JOptionPane.showMessageDialog(null, "O número binário é: " + binario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Número fora do intervalo permitido!");
                    }
                    break;
                case 5:
                    int fib = Integer.parseInt(JOptionPane.showInputDialog("Informe a posição na sequência de Fibonacci:"));
                    if (u3.validarFibonacci(fib)) {
                        int fibonacci = l3.calcFib(0, 1, fib);
                        JOptionPane.showMessageDialog(null, "O número de Fibonacci é: " + fibonacci);
                    } else {
                        JOptionPane.showMessageDialog(null, "Posição fora do intervalo permitido!");
                    }
                    break;
                case 6:
                    int sequencia = Integer.parseInt(JOptionPane.showInputDialog("Informe um número para somar a sequência de fatoriais:"));
                    double somatorio = l3.somarSequenciaFatoriais(sequencia);
                    JOptionPane.showMessageDialog(null, "A somatória da sequência de fatoriais é: " + somatorio);
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
