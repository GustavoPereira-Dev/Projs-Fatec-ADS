package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Frutas {
	public void readFile(String path, String nome) throws IOException{
		File arq = new File(path,nome);
		if(arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] colunas;
			while(linha != null){
				colunas = linha.split(",");
				if(colunas[2].contains("Fruits")) {
					System.out.println(colunas[0] + "\t" + colunas[1] + "\t" + colunas[3] + "\t");
				}
				linha = buffer.readLine();
			} 
			buffer.close();
			leitor.close();
			fluxo.close();
		} else{
			throw new IOException("Arquivo Inválido");
		}
	}
}
