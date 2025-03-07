import javax.swing.JOptionPane;

public class StackHeap{
	Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]){
			double num1, num2, soma;
			num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro número: "));
			num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo número: "));
			soma = num1 + num2;
			System.out.println("Resultado soma: " + soma);
	}
}