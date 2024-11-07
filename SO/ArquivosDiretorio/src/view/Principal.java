package view;

import java.io.IOException;

import controller.ArquivosController;
import model.Arquivo;

public class Principal {
	public static void main(String[] args) {
		String path = "C:\\TEMP";
		ArquivosController<Arquivo> arquivos = new ArquivosController<Arquivo>();
		
		try {
			arquivos.listarArquivos(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
