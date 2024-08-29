package view;

import controller.DistroController;
import controller.KillController;
import controller.RedesController;
import javax.swing.JOptionPane;

public class Main {
	static RedesController proc = new RedesController();
	static KillController kill = new KillController();
	static DistroController distro = new DistroController();
	public static void main(String args[]) {
		int opc;
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção \n 1 - Exibir Redes (ip e ping) \n 2 - Exibir Kill (Listar processos e matá-los pelo pid ou nome)"
					+ " \n 3 - Exibir distro/distribuição (se o SO for Linux) \n Outro - Finalizar"));
			switch(opc) {
				case 1:
					exibirRedes();
					break;
				case 2:
					exibirKill();
					break;
				case 3:
					distro.exibeDistro();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Saindo do algoritmo");
					break;
			}
		} while(opc >= 1 && opc <= 3);
		
		
	}
	
	public static void exibirRedes() {
		int opc;
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção: \n 1 - Ver somente os IPv4 do dispositivo \n 2 - Fazer um teste de ping e mostra o tempo médio dele "
					+ "\n Outro - Voltar ao menu inicial"));
			switch(opc) {
				case 1:
					proc.ip();
					break;
				case 2:
					proc.ping();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Saindo de Redes");
					break;
			}
		} while(opc >= 1 && opc <= 2);
	}
	
	public static void exibirKill() {
		int opc, pid;
		String nome;
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção \n 1 - Listar processos \n 2 - Matar processo pelo PID \n 3 - Matar processo pelo nome "
					+ "\n Outro - Voltar ao menu"));
			switch(opc) {
				case 1:
					kill.listaProcessos();
					break;
				case 2:
					pid = Integer.parseInt(JOptionPane.showInputDialog("Digite o pid do processo que deseja matar"));
					kill.mataPid(pid);
					break;
				case 3:
					nome = JOptionPane.showInputDialog("Digite o nome do processo que deseja matar");
					kill.mataNome(nome);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Saindo de Kill");
					break;
			}
		} while(opc >= 1 && opc <= 2);
	}
	
}
