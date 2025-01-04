package lista1.controller;

public class ListaRecursividadeController {
	public int somatoriaNumeros(int n) {
		// Condição de parada: quando o número é igual a 0, o resultado da soma é 0
		if(n < 0) return 0;
		
		// Relação de chamada: soma n + somatoriaNumeros(n - 1)
		return n + somatoriaNumeros(n - 1);
	}
	
//	public int menorValor(int[] vetor, int tam, int menor) {
//		if(tam < 0) return menor;
//		
//		return menorValor(vetor, tam - 1, calcMenor(menor));
//	}
	
	public int calcMenor(int[] vt, int i, int menor) {
		
		// Condição de parada: quando o número é igual a 0, o resultado da soma é o maior inteiro possível
		if(i < 0) {
			return Integer.MAX_VALUE;
		}
		
		// Relação de chamada: atribuicao calcMenor(vt, i - 1, menor)
		menor = calcMenor(vt, i - 1, menor);
		
		if(i == 0 || vt[i] < menor) {
			menor = vt[i];
		} 
		
		return menor;
	}
	
	
	public int calcFat(int n) {
		if(n > 1) {
			// Relação de chamada: produto n * calcFat(n - 1)
			return n * calcFat(n - 1);
		}
		// Condição de parada: quando o número é menor que 1, o resultado é 1
		return 1;
	}
	
	public int totalNegativos(int[] vt, int i) {
		
		int total = 0;
		
		// Condição de parada: quando o número é igual a 0, o resultado da soma é 0
		if(i < 0) return 0;
		
		// Relação de chamada: atribuicao totalNegativos(vt, i - 1)
		total = totalNegativos(vt, i - 1);
		if(vt[i] < 0) {
			total++;
		}
		
		return total;
	}
	
	
	  public double somarSequencia(int n) {
	        // Condição de parada: quando n é igual a 1, a somatória é 1
	        if (n == 1) {
	            return 1.0;
	        }
	        // Relação de chamada dos passos: somatória de 1/n + somarSequencia(n-1)
	        return 1.0 / n + somarSequencia(n - 1);
	    }


}
