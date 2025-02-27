package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	private String os;
	public KillController() {
		os = os();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void listaProcessos() {
		String proc;
		
		proc = os.contains("Windows") ? "TASKLIST /FO TABLE" : "ps -ef";
		
		String[] procArr = proc.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void mataPid(int pid) {
		String proc;
		proc = os.contains("Windows") ? "TASKKILL /PID " + pid : "kill -9 " + pid;
		try {
			Runtime.getRuntime().exec(proc);
		} catch(Exception e) {
			System.err.println(e);
		}
		
	}
	
	public void mataNome(String nome) {
		String proc;
		proc = os.contains("Windows") ? "TASKKILL /IM " + nome : "pkill " + nome;
		try {
			Runtime.getRuntime().exec(proc);
		} catch(Exception e) {
			System.err.println(e);
		}
		
	}
	
}
