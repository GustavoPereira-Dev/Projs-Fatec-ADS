package controller;

import model.estrutura.arvore.ArvoreBinaria;
import model.estrutura.lista.ListaEncadeadaSimples;

public class ArvoreBinariaController{
	public ArvoreBinariaController(){
		super();
	}
	
	public String teste() throws Exception{
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.criarDepartamento("Diretor", null);
		arvore.adicionarFuncionario("Diretor", "Joao");
		arvore.criarDepartamento("Coordenador de Polímeros", "Diretor");
		arvore.adicionarFuncionario("Coordenador de Polímeros", "Celso");
		arvore.criarDepartamento("Coordenador ADS", "Diretor");
		arvore.adicionarFuncionario("Coordenador ADS", "Luciano");
		arvore.criarDepartamento("Administração", "Diretor");
		arvore.adicionarFuncionario("Administração", "Léo");
		arvore.adicionarFuncionario("Administração", "Tati");
		arvore.criarDepartamento("Professores ADS", "Diretor");
		arvore.adicionarFuncionario("Professores ADS", "Luciano");
		arvore.adicionarFuncionario("Professores ADS", "Leandro");
		arvore.adicionarFuncionario("Professores ADS", "Ricardo");
		arvore.removerFuncionario("Professores ADS", "Luciano");
		
		
		
		ListaEncadeadaSimples lista = new ListaEncadeadaSimples();
		
		lista = arvore.listarOrdem();
		return lista.toString();
	}
}