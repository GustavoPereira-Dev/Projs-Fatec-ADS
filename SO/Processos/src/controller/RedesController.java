package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	private String os;
	public RedesController() {
		os = os();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
		
	public void ip() {
		String proc, ct;
		
		if(os.contains("Windows")) {
			proc = "IPCONFIG";
			ct = "IPv4";
		} else {
			proc = "ip addr";
			ct = "inet ";
		}

		String[] procArr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains(ct)) System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void ping() {
		String proc;
		
		proc = os.contains("Windows") ? "ping -4 -n 10 www.google.com.br" : "ping -4 -c 10 www.google.com.br";
		
		String[] procArr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains("M�dia") && proc == "ping -4 -n 10 www.google.com.br") System.out.println("Média: " + linha.split("M�dia =")[1]);
				if(linha.contains("avg") && proc == "ping -4 -c 10 www.google.com.br") System.out.println("Média: " + linha.split("/")[4]);
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
