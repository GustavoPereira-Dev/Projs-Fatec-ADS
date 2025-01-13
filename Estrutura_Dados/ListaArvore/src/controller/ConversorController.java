package controller;

import java.util.HashSet;
import java.util.Set;

import model.estrutura.arvorebinaria.ArvoreBinaria;
import model.estrutura.arvorenaobinaria.ArvoreArquivos;
import model.estrutura.arvorenaobinaria.No;

public class ConversorController {
    public static ArvoreBinaria<String> converterParaArvoreBinaria(ArvoreArquivos arvore) {
        Set<String> extensoes = new HashSet<>();
        coletarExtensoes(arvore.getRaiz(), extensoes);

        ArvoreBinaria<String> arvoreBinaria = new ArvoreBinaria<>();
        for (String extensao : extensoes) {
            arvoreBinaria.adicionar(extensao);
        }

        return arvoreBinaria;
    }

    private static void coletarExtensoes(No<String> no, Set<String> extensoes) {
        if (no != null) {
            String valor = no.getValor();
            if (valor.contains(".")) {
                String extensao = valor.substring(valor.lastIndexOf(".") + 1);
                extensoes.add(extensao);
            }

            for (int i = 0; i < no.getFilhos().total(); i++) {
                coletarExtensoes(no.getFilhos().get(i).getValor(), extensoes);
            }
        }
    }
}
