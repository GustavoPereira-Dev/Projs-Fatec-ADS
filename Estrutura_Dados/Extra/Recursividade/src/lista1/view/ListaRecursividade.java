package lista1.view;

import lista1.controller.ListaRecursividadeController;
import lista1.controller.UtilController;
import javax.swing.JOptionPane;

public class ListaRecursividade {

    public static void main(String[] args) {
        ListaRecursividadeController l1 = new ListaRecursividadeController();
        UtilController u1 = new UtilController();
        int opc;
        
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Somatória de números\n" +
                "2. Menor valor em vetor\n" +
                "3. Calcular fatorial\n" +
                "4. Total de negativos em vetor\n" +
                "5. Somar sequência\n" +
                "0. Sair"
            ));
            
            switch (opc) {
                case 1:
                    int n = Integer.parseInt(JOptionPane.showInputDialog("Informe um número:"));
                    int soma = l1.somatoriaNumeros(n);
                    JOptionPane.showMessageDialog(null, "A somatória é: " + soma);
                    break;
                case 2:
                    int[] vetor = u1.carregarVetor(10);
                    int menor = l1.calcMenor(vetor, vetor.length - 1, vetor[vetor.length - 1]);
                    JOptionPane.showMessageDialog(null, "O menor valor no vetor é: " + menor);
                    break;
                case 3:
                    int t = Integer.parseInt(JOptionPane.showInputDialog("Informe um número (máx 12):"));
                    if (u1.verificarEntradaFat(t)) {
                        int fat = l1.calcFat(t);
                        JOptionPane.showMessageDialog(null, "O fatorial é: " + fat);
                    } else {
                        JOptionPane.showMessageDialog(null, "Número maior que 12!");
                    }
                    break;
                case 4:
                    int[] vetorNegativos = u1.carregarVetor(10);
                    int totalNegativos = l1.totalNegativos(vetorNegativos, vetorNegativos.length - 1);
                    JOptionPane.showMessageDialog(null, "Total de negativos no vetor é: " + totalNegativos);
                    break;
                case 5:
                    int seq = Integer.parseInt(JOptionPane.showInputDialog("Informe um número:"));
                    double somatorio = l1.somarSequencia(seq);
                    JOptionPane.showMessageDialog(null, "A somatória da sequência é: " + somatorio);
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
