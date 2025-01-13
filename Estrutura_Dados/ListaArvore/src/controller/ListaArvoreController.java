package controller;

import model.estrutura.arvorenaobinaria.ArvoreArquivos;

public class ListaArvoreController {
    public String teste() {
        String[] caminhos = {
            "/home/usuario/Documents/prova_ed.odt",
            "/home/usuario/Documents/hino_BOTAFOGO.mp3",
            "/home/usuario/Documents/prova_ihc.odt",
            "/home/usuario/Documents/prova_ed_mais_top.odt",
            "/home/usuario/Images/AlunosFelizes.png",
            "/home/usuario/Images/CachorroCaramelo.png",
            "/home/usuario/Downloads/Debian12.8.img",
        };

        ArvoreArquivos arvore = new ArvoreArquivos();

        arvore.criarEstrutura(caminhos);

        return arvore.imprimirArvore();
    }
}
