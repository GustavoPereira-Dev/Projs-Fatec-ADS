package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingThread extends Thread {
	String nome;
	String url;
	public PingThread(String nome, String url) {
		this.nome = nome;
		this.url = url;
	}
	@Override
	public void run() {
		ping();
	}
	
	public void ping() {
		String proc;
		
		proc = System.getProperty("os.name").contains("Windows") ? "ping -4 -n 10 " + url : "ping -4 -c 10 " + url;
		
		String[] procArr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains("M�dia")) System.out.println(nome + ": " + linha.split("M�dia =")[1]);
				if(linha.contains("avg")) System.out.println(nome + ": " + linha.split("/")[4]);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}
}
