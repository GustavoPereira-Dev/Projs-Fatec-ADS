package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
	private String os;
	public DistroController() {
		os = os();
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
	
	public void exibeDistro() {
		boolean eLinux = os.contains("Linux");
		
		if(eLinux) {
			String proc = "cat /etc/os-release";
			String[] procArr = proc.split(" ");
				try {
					Process p = Runtime.getRuntime().exec(procArr);
					InputStream fluxo = p.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = buffer.readLine();
					while(linha != null) {
						if(linha.contains("NAME") && !linha.contains("PRETTY_NAME") && !linha.contains("VERSION_CODENAME")) System.out.println("Distro: " + linha.split("NAME= ")[1]);
						if(linha.contains("VERSION_ID")) System.out.println("Versão: " + linha.split("VERSION_ID= ")[1]);
						linha = buffer.readLine();
					}
					buffer.close();
					leitor.close();
					fluxo.close();
				} catch(Exception e) {
					System.err.println(e);
				}
		} else {
			System.out.println("Esse Sistema Operacional não é baseado em Linux");
		}
	}
}
