package model;

public class OrdenacaoVetor{
	
	// Classe que sera utilizada na biblioteca depois
	
	public int[] bubbleSortCrescente(int[] vetor) {
		int aux;
		
		for(int i = 0; i < (vetor.length - 1); i++) {
			for(int j = 0; j < (vetor.length - 1); j++) {
				if(vetor[j] > vetor[j + 1]) { 
					aux = vetor[j];
					vetor[j] = vetor[j + 1];
					vetor[j + 1] = aux;
				}
			}
		}
		
		return vetor;
	}
	
	public int[] bubbleSortDecrescente(int[] vetor) {
		int aux;
		
		for(int i = 0; i < (vetor.length - 1); i++) {
			for(int j = 0; j < (vetor.length - 1); j++) {
				if(vetor[j] < vetor[j + 1]) { 
					aux = vetor[j];
					vetor[j] = vetor[j + 1];
					vetor[j + 1] = aux;
				}
			}
		}
		
		return vetor;
	}
	
	public int[] mergeSort(int[] vetor, int inicio, int fim) {
		
		if(inicio < fim) {
			int meio = (inicio + fim) / 2;
			mergeSort(vetor, inicio, meio); 
			mergeSort(vetor, meio + 1, fim); 
			intercalaCrescente(vetor, inicio, meio, fim);
		}
		
		return vetor;
	}
	
	private void intercalaCrescente(int[] vetor, int inicio, int meio, int fim) {
		int novoVetor[] = new int[vetor.length];
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
			} else if(novoVetor[esq] < novoVetor[dir]) { 
				vetor[cont] = novoVetor[esq];
				esq++;
			} else {
				vetor[cont] = novoVetor[dir];
				dir++;
			}
		}
		
	}
	

	@SuppressWarnings("unused")
	private void intercalaDecrescente(int[] vetor, int inicio, int meio, int fim) {
		int novoVetor[] = new int[vetor.length];
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
			} else if(novoVetor[esq] > novoVetor[dir]) { 
				vetor[cont] = novoVetor[esq];
				esq++;
			} else {
				vetor[cont] = novoVetor[dir];
				dir++;
			}
		}
		
	}

	public int[] quickSort(int[] vetor, int inicio, int fim) {
	    if (inicio < fim) {
	        int posicaoPivoFixo = dividirCrescente(vetor, inicio, fim);
	        quickSort(vetor, inicio, posicaoPivoFixo - 1);
	        quickSort(vetor, posicaoPivoFixo + 1, fim);
	    }
	    return vetor;
	}
	
	private int dividirCrescente(int[] vetor, int inicio, int fim) {
	    int ponteiroEsquerda = inicio + 1;
	    int ponteiroDireita = fim;
	    int pivo = vetor[inicio];
	    
	    while (ponteiroEsquerda <= ponteiroDireita) {
	        while (ponteiroEsquerda <= ponteiroDireita && vetor[ponteiroEsquerda] <= pivo) { 
	            ponteiroEsquerda++;
	        }
	        
	        while (ponteiroDireita >= ponteiroEsquerda && vetor[ponteiroDireita] > pivo) { 
	            ponteiroDireita--;
	        }
	        
	        if (ponteiroEsquerda < ponteiroDireita) {
	            trocar(vetor, ponteiroEsquerda, ponteiroDireita);
	            ponteiroEsquerda++;
	            ponteiroDireita--;
	        }
	    }
	    
	    trocar(vetor, inicio, ponteiroDireita);
	    return ponteiroDireita;
	}

	@SuppressWarnings("unused")
	private int dividirDecrescente(int[] vetor, int inicio, int fim) {
	    int ponteiroEsquerda = inicio + 1;
	    int ponteiroDireita = fim;
	    int pivo = vetor[inicio];
	    
	    while (ponteiroEsquerda <= ponteiroDireita) {
	        while (ponteiroEsquerda <= ponteiroDireita && vetor[ponteiroEsquerda] >= pivo) { 
	            ponteiroEsquerda++;
	        }
	        
	        while (ponteiroDireita >= ponteiroEsquerda && vetor[ponteiroDireita] < pivo) { 
	            ponteiroDireita--;
	        }
	        
	        if (ponteiroEsquerda < ponteiroDireita) {
	            trocar(vetor, ponteiroEsquerda, ponteiroDireita);
	            ponteiroEsquerda++;
	            ponteiroDireita--;
	        }
	    }
	    
	    trocar(vetor, inicio, ponteiroDireita);
	    return ponteiroDireita;
	}
	
	private void trocar(int[] vetor, int i, int j) {
	    int auxiliar = vetor[i];
	    vetor[i] = vetor[j];
	    vetor[j] = auxiliar;
	}

	
	
}
