package controller;

import java.io.File;
import java.io.IOException;

import model.Arquivo;
import model.lista.Lista;
import model.lista.No;


public class ArquivosController<T extends Comparable<T>> {
	
	public void listarArquivos(String path) throws IOException {
		
		int i, j;
		double aux;
		
		Arquivo arquivo;
		Lista<Arquivo> listaArquivos = new Lista<Arquivo>();
		File dir = new File(path);
		if(dir.exists() && dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File f: files){
				if(f.isFile()) {
					arquivo = new Arquivo(f.getName(),converterBytesParaMega(f.length()));
					listaArquivos.append(arquivo);
				}
			}
		} else{
			throw new IOException("Diretório inválido");
		}
		
		if (listaArquivos.inicio == null) return; 
		
		boolean trocou; 
		No<Arquivo> atual; 
		No<Arquivo> proximo; 
		do { 
			trocou = false; 
			atual = listaArquivos.inicio; 
			while (atual.getProximo() != null) { 
				proximo = atual.getProximo(); 
				if (atual.getValor().compareTo(proximo.getValor()) > 0) { 
					Arquivo temp = atual.getValor(); 					
					atual.setValor(proximo.getValor()); 
					proximo.setValor(temp); 
					trocou = true; 
				} atual = proximo; 
			} 
		} while (trocou); 

		No<Arquivo> buffer = listaArquivos.inicio;
		while(buffer.getProximo() != null) {
			
			System.out.println(buffer.getValor().getNome() + ": " + buffer.getValor().getTamanho());
			buffer = buffer.getProximo();
		}
	}
	
	
	
	public double converterBytesParaMega(double bytes) {
		return ((bytes) / 1024) / 1024;
	}
}
