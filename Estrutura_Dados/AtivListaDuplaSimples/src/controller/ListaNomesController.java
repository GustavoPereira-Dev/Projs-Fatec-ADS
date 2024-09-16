package controller;

import model.estrutura_dupla.ListaDupla;

public class ListaNomesController {
    
    public String teste() {
    	 ListaDupla listaDupla = new ListaDupla();
         
         StringBuilder s = new StringBuilder();
         
         listaDupla.adicionarPessoa("José");
         listaDupla.adicionarPessoa("Joana");
         listaDupla.adicionarPessoa("Ana");
         listaDupla.adicionarPessoa("Pedro");

         s.append("Lista após adicionar pessoas: \n");
         listaDupla.exibirLista();

         s.append("\nLocalizando 'José': " + listaDupla.localizarPessoa("José") + "\n");
         s.append("Localizando 'Ana': " + listaDupla.localizarPessoa("Ana") + "\n");

         s.append("\nNomes com a letra 'A': " + listaDupla.listarNomesPorLetra('A') + "\n");
         s.append("Nomes com a letra 'P': " + listaDupla.listarNomesPorLetra('P') + "\n");
         s.append("Nomes com a letra 'J': " + listaDupla.listarNomesPorLetra('J') + "\n");
         s.append("Nomes com a letra 'B': " + listaDupla.listarNomesPorLetra('B') + "\n");
         
         s.append("\nRemovendo nome Ana: \n");
         listaDupla.removerPessoa("Ana");
         s.append("\nNomes com a letra 'A' após a remoção: " + listaDupla.listarNomesPorLetra('A') + "\n");
         return s.toString();
    }
}