package model.estrutura.grafo.matriz;

import java.util.*;
import java.lang.Exception;

public class ProcessoMatriz<T> extends Thread{
	private int[][] matriz;
	private String[] labels;


	public ProcessoMatriz(String[] labels){
		this.labels = labels;
		this.matriz = new int[labels.length][labels.length];
	}

	public void link(String label1, String label2, int sentido) throws IllegalArgumentException{
		int index_label1 = Arrays.asList(this.labels).indexOf(label1);
		int index_label2 = Arrays.asList(this.labels).indexOf(label2);
		try {
			if(sentido == 1) {
				this.matriz[index_label1][index_label2] = 1;
			} else if(sentido == 2) {
				this.matriz[index_label2][index_label1] = 1;
			} else{
				this.matriz[index_label1][index_label2] = 1;
				this.matriz[index_label2][index_label1] = 1;
			}

		} catch(Exception e) {
			throw new IllegalArgumentException("Algumas dos processos não são existentes!");
		}
		
	}
	
	public void unlink(String label1, String label2, int sentido) throws IllegalArgumentException{
		int index_label1 = Arrays.asList(this.labels).indexOf(label1);
		int index_label2 = Arrays.asList(this.labels).indexOf(label2);
		try {
			if(sentido == 1) {
				if(this.matriz[index_label1][index_label2] == 0) throw new IllegalArgumentException("O processo " + label1 + " não tem dependência com o " + label2);
				this.matriz[index_label1][index_label2] = 0;
			} else if(sentido == 2) {
				if(this.matriz[index_label2][index_label1] == 0) throw new IllegalArgumentException("O processo " + label2 + " não tem dependência com o " + label1);
				this.matriz[index_label2][index_label1] = 0;
			} 

		} catch(Exception e) {
			throw new IllegalArgumentException("Algumas dos processos não são existentes!");
		}
		
	}
	
	public String[] pecorrerSequenciaProcessos() {
		int comp = labels.length;
		String[] sequencia = new String[comp];
		boolean[] foiExecutado = new boolean[comp];
		boolean podeExecutar = true;
		int procsExecutados = 0, i, j;
		
		while(procsExecutados < comp) {
			
			for(i = 0; i < comp; i++) {
				if(procsExecutados == comp) break;
				if(foiExecutado[i]) continue;
				for(j = 0; j < comp; j++) {
					if(matriz[i][j] == 1  && !foiExecutado[j] ) {
						podeExecutar = false;
						break;
					}
				}
				
				if(podeExecutar && !foiExecutado[i]) {
					foiExecutado[i] = true;
					sequencia[procsExecutados] = labels[i];
					procsExecutados++;
				}
				
				podeExecutar = true;
			}
		}
		
		return sequencia;
	}
	
	public String retornarSequencia(String[] sequencia) {
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < sequencia.length; i++) {
			s.append((i + 1) + "º" + sequencia[i] + "\n");
		}
		
		
		return s.toString();
		
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < this.labels.length; i++){
			builder.append(this.labels[i] + ": ");
			for(int j = 0; j < this.labels.length; j++){
				if(this.matriz[i][j] == 1){
					builder.append(this.labels[j] + " ");	
				}
			}
			builder.append("\r\n");
		}
		return builder.toString();
	}
}