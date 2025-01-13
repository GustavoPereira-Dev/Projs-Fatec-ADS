package view;

import java.util.List;

import controller.ConversorController;
import controller.ListaArvoreController;
import model.estrutura.arvorebinaria.ArvoreBinaria;
import model.estrutura.arvorenaobinaria.ArvoreArquivos;

public class Principal {
	public static void main(String[] args) {
		
		// Lista para árvore
		ListaArvoreController l = new ListaArvoreController();
		System.out.println(l.teste());
		
		
		
		// Principais Extensoes
		
		ArvoreArquivos arvoreArquivos = new ArvoreArquivos();
        String[] caminhos = {
            "/home/user/docs/file1.txt",
            "/home/user/docs/file2.pdf",
            "/home/user/images/photo1.jpg",
            "/home/user/images/photo2.png",
            "/home/user/music/song.mp3"
        };

        arvoreArquivos.criarEstrutura(caminhos);

        ArvoreBinaria<String> arvoreBinaria = ConversorController.converterParaArvoreBinaria(arvoreArquivos);

        System.out.println("Extensões de arquivos em ordem:");
        arvoreBinaria.imprimirEmOrdem();
        
        
        System.out.println("\n");
        
        // Extensoes em um diretorio especifico
        List<String> extensoesOrdenadas = arvoreArquivos.getExtensoesOrdenadas("/home/user/docs");
        System.out.println("Extensões de arquivos ordenadas no diretório /home/user/docs:");
        for (String extensao : extensoesOrdenadas) {
            System.out.println(extensao);
        }
	}
	
	
}
