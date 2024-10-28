package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SteamCharts {
	public void verIntervaloMedia(String path, String nome, int ano, String mes, double mediaEsperada) throws IOException{
		File arq = new File(path,nome);
		if(arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] colunas;
			linha = buffer.readLine();
			while(linha != null){
				colunas = linha.split(",");
				if(Integer.parseInt(colunas[1]) == ano && colunas[2].contains(mes) && Double.parseDouble(colunas[3]) >= mediaEsperada) {
					System.out.println(colunas[0] + " | " + colunas[3]);
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
	
	public void gerarArquivoIntervalo(String path, String nome, int ano, String mes) throws IOException{
		File arqSteam = new File("C:\\TEMP","SteamCharts.csv");
		File dir = new File(path);
		File arq = new File(path,nome + ".csv");
		StringBuffer conteudo = new StringBuffer();
		
		if(dir.exists() && dir.isDirectory()){
			
			if(arqSteam.exists() && arqSteam.isFile()){
				FileInputStream fluxo = new FileInputStream(arqSteam);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String[] colunas;
				linha = buffer.readLine();
				while(linha != null){
					colunas = linha.split(",");
					if(Integer.parseInt(colunas[1]) == ano && colunas[2].contains(mes)) {
						System.out.println(colunas[0] + " | " + colunas[3]);
						conteudo.append(colunas[0] + ";" + colunas[3] + "\n");
					}
					
					linha = buffer.readLine();
				} 
				buffer.close();
				leitor.close();
				fluxo.close();
			} else{
				throw new IOException("Arquivo Inválido");
			}
			
			
			//if(arq.exists()){
			//	existe = true;
			//}

			//buffer.append(linha);
			
			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo.toString());
			print.flush();
			print.close();
			fileWriter.close();
			
			System.out.println("Arquivo " + nome + " em " + path + " criado com sucesso!");
		} else{
			throw new IOException("Diretório Inválido");
		}
	}
}
