package controller;

import java.util.List;

import model.estrutura.ArvoreBinariaNomes;

import java.util.List;
import model.estrutura.ArvoreBinariaNomes;

public class ArvoreNomesController {
    public static void main(String[] args) {
        ArvoreBinariaNomes<Character> arvore = new ArvoreBinariaNomes<>();
        String[] nomes = {"Ana", "Amanda", "Antonio", "Bruno", "Beatriz"};
        for (String nome : nomes) {
            arvore.adicionarNome(nome);
        }

        System.out.print("Em Ordem: ");
        arvore.emOrdem(arvore.getRaiz());
        System.out.println();

        System.out.print("Pré-Ordem: ");
        arvore.preOrdem(arvore.getRaiz());
        System.out.println();

        System.out.print("Pós-Ordem: ");
        arvore.posOrdem(arvore.getRaiz());
        System.out.println();

        System.out.println("Encontrar 'Amanda': " + (arvore.encontrarNome(arvore.getRaiz(), "Amanda") ? "Encontrado" : "Não encontrado"));
        System.out.println("Encontrar 'Carlos': " + (arvore.encontrarNome(arvore.getRaiz(), "Carlos") ? "Encontrado" : "Não encontrado"));

        List<String> nomesComA = arvore.nomesComCaractere(arvore.getRaiz(), "A");
        System.out.println("Nomes com 'A': " + nomesComA);
    }
}
