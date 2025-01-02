package controller;

import java.io.*;
import model.Pessoa;

public class PessoaController {
	public PessoaController() {
		super();
	}
	
	// Bubble Sort com Nome
	public Pessoa[] ordenaNome() throws IOException{
		Pessoa[] pessoas = Pessoa.carregar();
		for(int i = 0; i < pessoas.length; i++) {
			for(int j = 0; j < pessoas.length - 1; j++) {
				if(pessoas[j].getNome().compareTo(pessoas[j + 1].getNome()) > 0) {
					Pessoa aux = pessoas[j];
					pessoas[j] = pessoas[j + 1];
					pessoas[j + 1] = aux;
				}
			}
		}
		
		return pessoas;
	}
	

	// QuickSort com Sobrenome
	public Pessoa[] ordenaSobrenome(Pessoa[] pessoas, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivoFixo = dividir(pessoas, inicio, fim);
            ordenaSobrenome(pessoas, inicio, posicaoPivoFixo - 1);
            ordenaSobrenome(pessoas, posicaoPivoFixo + 1, fim);
        }
        return pessoas;
    }

	// MergeSort com Idade
    private static int dividir(Pessoa[] pessoas, int inicio, int fim) {
        int ponteiroEsquerda = inicio + 1;
        int ponteiroDireita = fim;
        Pessoa pivo = pessoas[inicio];

        while (ponteiroEsquerda <= ponteiroDireita) {
            while (ponteiroEsquerda <= ponteiroDireita && pessoas[ponteiroEsquerda].getSobrenome().compareTo(pivo.getSobrenome()) <= 0) {
                ponteiroEsquerda++;
            }

            while (ponteiroDireita >= ponteiroEsquerda && pessoas[ponteiroDireita].getSobrenome().compareTo(pivo.getSobrenome()) > 0) {
                ponteiroDireita--;
            }

            if (ponteiroEsquerda < ponteiroDireita) {
                trocar(pessoas, ponteiroEsquerda, ponteiroDireita);
                ponteiroEsquerda++;
                ponteiroDireita--;
            }
        }

        trocar(pessoas, inicio, ponteiroDireita);
        return ponteiroDireita;
    }
    
    private static void trocar(Pessoa[] pessoas, int i, int j) {
        Pessoa auxiliar = pessoas[i];
        pessoas[i] = pessoas[j];
        pessoas[j] = auxiliar;
    }
    
    public Pessoa[] ordenaIdade(Pessoa[] vetor, int inicio, int fim) {
		
		if(inicio < fim) {
			int meio = (inicio + fim) / 2;
			ordenaIdade(vetor, inicio, meio); // Esquerda
			ordenaIdade(vetor, meio + 1, fim); // Direita
			intercala(vetor, inicio, meio, fim);
		}
		
		return vetor;
	}
	
    private void intercala(Pessoa[] vetor, int inicio, int meio, int fim) {
        Pessoa novoVetor[] = new Pessoa[vetor.length];
        for(int i = inicio; i <= fim; i++) {
            novoVetor[i] = vetor[i];
        }
        
        int esq = inicio;
        int dir = meio + 1;
        
        for(int cont = inicio; cont <= fim; cont++) {
            if(esq > meio) {
                vetor[cont] = novoVetor[dir];
                dir++;
            } else if(dir > fim) {
                vetor[cont] = novoVetor[esq];
                esq++;
            } else if(novoVetor[esq].getIdade() < novoVetor[dir].getIdade()) { // Ordenação Crescente pela idade
                vetor[cont] = novoVetor[esq];
                esq++;
            } else {
                vetor[cont] = novoVetor[dir];
                dir++;
            }
        }
    }

	

}
	

