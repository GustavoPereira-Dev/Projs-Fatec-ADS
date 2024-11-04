package model.estrutura.grafo;

import java.util.*;

public class GrafoMatriz<T> {
	private int[][] matriz;
	private String[] labels;

	// GrafoMatriz(String[]): Construtor, tem que receber os elementos
	// link: Liga os elementos na matriz
	// toString

	public GrafoMatriz(String[] labels){
		this.labels = labels;
		this.matriz = new int[labels.length][labels.length];
	}

	public void link(String label1, String label2){
		int index_label1 = Arrays.asList(this.labels).indexOf(label1);
		int index_label2 = Arrays.asList(this.labels).indexOf(label2);
		this.matriz[index_label1][index_label2] = 1;
		this.matriz[index_label2][index_label1] = 1;
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