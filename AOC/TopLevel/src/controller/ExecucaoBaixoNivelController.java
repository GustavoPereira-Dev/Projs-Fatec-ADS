package controller;

import javax.swing.JOptionPane;

import model.BaixoNivel;

import java.util.*;
import java.lang.Exception;
public class ExecucaoBaixoNivelController {
	private List<String> listaComandos;
	private List<String> listaMemorias;
	private String[][] comandos;
	private String[][] memorias;
	private String[] status = new String[3];
	private BaixoNivel b;
	final int posicaoComandos = 200;
	final int posicaoMemorias = 950;
	
	public void adicionarAcoes() {
		int cont = 0, i;
		String valor;
		
		do {
			valor = JOptionPane.showInputDialog("Digite o valor da memória: (0 para cancelar)");
			listaMemorias.add(valor);
		} while(valor !=  "0" || valor.length() != 4);
		
		do {
			valor = JOptionPane.showInputDialog("Digite o valor do comando: (0 para cancelar)");
			listaComandos.add(valor);
		} while(valor != "0" || valor.length() != 4);
		
		comandos = new String[listaComandos.size()][2];
		memorias = new String[listaMemorias.size()][2];
		cont = posicaoMemorias;
		for(i = 0; listaMemorias.size() < i; i++) {
			comandos[i][0] = Integer.toString(posicaoMemorias);
			comandos[i][0] = listaMemorias.get(i);
			cont++;
		}
		
		cont = posicaoComandos;
		for(i = 0; listaComandos.size() < i; i++) {
			comandos[i][0] = Integer.toString(posicaoComandos);
			comandos[i][0] = listaComandos.get(i);
			cont++;
		}
		
		
		b = new BaixoNivel(comandos, memorias, status);
		
	}
	
	public void executar() throws IllegalArgumentException {
		comandos = b.getComandos();
		memorias = b.getMemorias();
		status = b.getStatus();
		
		int i, posicao;
		String acao, local;
		for(i = 0; i < comandos[0].length; i++) {
			acao =  comandos[i][1].substring(0,1);
			local = comandos[i][1].substring(1,5);
			status[0] = comandos[i][0]; 
			status[2] = comandos[i][1]; 
			switch(acao) {
				case "1":
					posicao = procurarMemoria(local); 
					status[1] = memorias[posicao][1];
					break;
				case "2":
					posicao = procurarMemoria(local); 
					memorias[posicao][1] = status[1];
					break;
				case "3":
					posicao = procurarMemoria(local); 
					JOptionPane.showMessageDialog(null, "Memória nº " + memorias[posicao][0] + ": " + memorias[posicao][1]);
					break;
				case "4":
					posicao = procurarAcao(local);
					if(posicao < i) throw new IllegalArgumentException("Um jump deve ser feito acima da ação e não abaixo");
					i = posicao;
					break;
				case "5":
					posicao = procurarMemoria(local); 
					status[1] = organizarRegistrador(status[i],memorias[i][1],"+");
					break;
				case "6":
					posicao = procurarMemoria(local); 
					status[1] = organizarRegistrador(status[1],memorias[i][1],"-");
					break;
				case "7":
					posicao = procurarMemoria(local); 
					status[1] = organizarRegistrador(status[1],memorias[i][1],"*");
					break;
				case "8":
					posicao = procurarMemoria(local); 
					status[1] = organizarRegistrador(status[1],memorias[i][1],"/");
					break;
				case "9":
					posicao = procurarMemoria(local); 
					if(!memorias[posicao][1].contains(status[1])) status[1] = memorias[posicao][1];
					break;
				default:
					break;
			}
			
			System.out.println("PC: " +  status[0] + "; AC: " + status[1] + "; IR: " + status[2]);
		}
	}
	

	private String organizarRegistrador(String s1, String s2, String op) {
		int n1 = Integer.parseInt(s1);
		int n2 = Integer.parseInt(s2);
		int res = 0;
		StringBuilder valorRegistrador = new StringBuilder();
		switch(op) {
			case "+":
				res = n1 + n2;
				break;
			case "-":
				res = n1 - n2;
				break;
			case "*":
				res = n1 * n2;
				break;
			case "/":
				res = n1 / n2;
				break;
		}
		
		valorRegistrador.append(Integer.toString(res));
		while(valorRegistrador.length() < 4) {
			valorRegistrador.insert(0, "0");
		}
		return valorRegistrador.toString();
	}

	public int procurarMemoria(String endereco) throws IllegalArgumentException {
		int i;
		for(i = 0; i < memorias.length; i++) {
			if(endereco == memorias[i][0]) return i;
		}
		throw new IllegalArgumentException("Endereço de memória não encontrado");
		
	}
	
	public int procurarAcao(String endereco) {
		int i;
		for(i = 0; i < comandos.length; i++) {
			if(endereco == comandos[i][0]) return i;
		}
		throw new IllegalArgumentException("Endereço de ação não encontrado");
	}
}
