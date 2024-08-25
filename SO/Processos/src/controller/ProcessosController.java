package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	public ProcessosController() {
		super();
	}
	
	public void so() {
		String sistema = System.getProperty("os.name");
		String ver = System.getProperty("os.version");
		String arch = System.getProperty("os.arch");
		
		System.out.println(sistema + " " + ver + " " + arch);
	}
	
	public String getOS() {
		return System.getProperty("os.name");
	}
	
	public void callProcess(String proc) {
		try {
			Runtime.getRuntime().exec(proc);
		} catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
	}
	
	public void readProcess(String proc) {
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
}
