package ordenacao;

public class OrdenacaoRecController {
	
	 public static void bubbleSortRecursivo(int[] vetor, int n) {
		if (n == 1) {
			return;
	    }

	    for (int i = 0; i < n - 1; i++) {
	        if (vetor[i] > vetor[i + 1]) {
	        	int aux = vetor[i];
	            vetor[i] = vetor[i + 1];
	            vetor[i + 1] = aux;
	        }
	    }

	    bubbleSortRecursivo(vetor, n - 1); 
	 }
	 
	 public static void mergeSortRecursivo(int[] vetor, int inicio, int fim) {
	     if (inicio >= fim) {
	        return;
	     }

	     int meio = (inicio + fim) / 2;

	     mergeSortRecursivo(vetor, inicio, meio);
	     mergeSortRecursivo(vetor, meio + 1, fim);
	     intercalaCrescente(vetor, inicio, meio, fim);
	 }

     private static void intercalaCrescente(int[] vetor, int inicio, int meio, int fim) {
    	 int[] temp = new int[fim - inicio + 1];

	     int i = inicio, j = meio + 1, k = 0;

	     while (i <= meio && j <= fim) {
	    	 if (vetor[i] <= vetor[j]) {
	    		 temp[k++] = vetor[i++];
	         } else {
	             temp[k++] = vetor[j++];
	         }
	     }

	     while (i <= meio) {
	         temp[k++] = vetor[i++];
	     }

	     while (j <= fim) {
	         temp[k++] = vetor[j++];
	     }

	     for (i = inicio; i <= fim; i++) {
	         vetor[i] = temp[i - inicio];
	     }
     }
     
     public static void quickSortRecursivo(int[] vetor, int inicio, int fim) {
         if (inicio < fim) {
             int posicaoPivo = dividirRecursivo(vetor, inicio, fim);

             quickSortRecursivo(vetor, inicio, posicaoPivo - 1);
             quickSortRecursivo(vetor, posicaoPivo + 1, fim);
         }
     }

     private static int dividirRecursivo(int[] vetor, int inicio, int fim) {
         int pivo = vetor[inicio];
         int i = inicio + 1;
         int j = fim;

         while (i <= j) {
             while (i <= j && vetor[i] <= pivo) {
                 i++;
             }

             while (i <= j && vetor[j] > pivo) {
                 j--;
             }

             if (i < j) {
                 trocar(vetor, i, j);
             }
         }

         trocar(vetor, inicio, j);
         return j;
     }

     private static void trocar(int[] vetor, int i, int j) {
         int temp = vetor[i];
         vetor[i] = vetor[j];
         vetor[j] = temp;
     }

}
