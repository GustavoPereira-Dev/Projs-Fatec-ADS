package lista2.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import lista2.model.Cliente;
import lista2.model.lista.ListaDupla;

public class Lista2Controller {
	
	// Exercicio 1
	public void listaX() {
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		int[] vetor = {100, 200, 1, 50, 39, 44, 25, 16, 99, 45, 33, 18, 102, 92};
		int tamanhoVetor = vetor.length;

		// Adicao de elementos
		
		for(int i = 0; i < tamanhoVetor; i++){
			if(!l.isEmpty()){
				l.addFirst(vetor[i] * 8);
			} else if(vetor[i] % 5 == 0 || l.size() > 10){
				l.addLast(vetor[i] + 10);
			} else if(l.size() >= 2){
				l.add(vetor[i] * 3, 1);
			} else{
				l.addFirst(vetor[i]);
			}
		}
		
		// Remocao e adicao de elementos
		int tamanho = l.size();
		while(!l.isEmpty()){
			if(tamanho > 10){
				System.out.println(l.get(0));
				l.removeFirst();
			} else if(tamanho > 1){
				System.out.println(l.get(tamanho - 1));
				l.removeLast();		
			} else{
				System.out.println(l.get(0));
				l.removeFirst();
			}
			tamanho = l.size();
		}
		
	}
	
	
	// Exercicio 4
	public ListaDupla<Integer> pecorrendoVetor() {
		int[] vetor = {25, 42, 23, 74, 80, 77, 13, 41, 59, 35, 68, 53, 75, 84, 44, 94, 39, 71, 88};
		ListaDupla<Integer> listaVetor = new ListaDupla<Integer>(); 
		
		for(int valor: vetor) {
			
			if(listaVetor.getTotal() == 0) {
				listaVetor.prepend(valor * 2);
			} else if (listaVetor.getTotal() < 3) {
				listaVetor.append(valor / 2);
			} else if(listaVetor.getTotal() > 10) {
				listaVetor.insert(5, valor * 3);
			} else {
				listaVetor.insert(1, valor);
			}
			
		}
		
		int posicao = 0;
		
		while(listaVetor.getTotal() != 0) {
			
			if(listaVetor.getTotal() > 10) {
				posicao = 3;
			} else if(listaVetor.getTotal() > 5) {
				posicao = listaVetor.getTotal() - 1;
			} else if(listaVetor.getTotal() > 3) {
				posicao = 1;
			} else {
				posicao = 0;
			}
			
			System.out.println(listaVetor.get(posicao).toString());
			listaVetor.remove(posicao);
		}
		
		return listaVetor;
	}
	
	// Exercicio 5
    private void novoArquivo(ListaDupla<Cliente> lista, String nomeArquivo) throws IOException {
        File file = new File("C:\\TEMP", nomeArquivo);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < lista.getTotal(); i++) {
            buffer.append(lista.get(i).getValor().toString()).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(buffer.toString());
        }
    }

    public void novoCadastro(int idadeMin, int idadeMax, double limiteCredito) throws IOException {
        ListaDupla<Cliente> listaClientes = new ListaDupla<Cliente>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\TEMP\\cadastro.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 4) {
                    // Ignorar linhas mal formatadas
                    continue;
                }
                Cliente cliente = new Cliente(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]));
                if (cliente.getIdade() >= idadeMin && cliente.getIdade() <= idadeMax && cliente.getLimiteCredito() > limiteCredito) {
                    listaClientes.append(cliente);
                }
            }
        }
        String nomeArquivo = "Idade_" + idadeMin + "_a_" + idadeMax + "_limite_" + limiteCredito + ".csv";
        novoArquivo(listaClientes, nomeArquivo);
    }
}
