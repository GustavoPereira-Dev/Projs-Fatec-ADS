package lista2.controller;

public class ListaRecursividadeController {
	
	public int multiplicacaoPorSoma(int n1, int n2) {
		
		// Condição de parada: quando n2 é igual ou menor que 0, a somatória e 0
		if(n2 <= 0) return 0;
		
		// Relação de chamada dos passos: n1 + multiplicacaoPorSoma(n1, n2 - 1)
		return n1 + multiplicacaoPorSoma(n1, n2 - 1);
	}
	
	public int divisaoPorSub(int n1, int n2) {
		
		// Condição de parada: quando n1 e menor que n2, a divisao e n1
		if(n1 < n2) return n1;
		
		// Relação de chamada dos passos: divisaoPorSub(n1 - n2, n2)
		return divisaoPorSub(n1 - n2, n2);
	}
	
	public int somatoriaPares(int[] vetor, int i) {
		int pares = 0;
		
		// Condição de parada: quando i e menor que 0, a divisao e 0
		if(i < 0) return 0;
		
		// Relação de chamada dos passos: somatoriaPares(vetor, i - 1)
		pares = somatoriaPares(vetor, i - 1);
		if(vetor[i] % 2 == 0) pares++;
		
		return pares;
	}
	
	public int fatorialDuplo(int n) {
		if(n >= 1) {
			// Relação de chamada: produto n * fatorialDuplo(n - 1)
			return n * fatorialDuplo(n - 2);
		}
		// Condição de parada: quando o número é menor que 1, o resultado é 1
		return 1;
	}
	
	public int mdc(int x, int y) {
		
		// Condição de parada: quando x é igual a y, o MDC é x (ou y)
		if(x == y) {
			return x;
		}
		
		// Relação de chamada dos passos:
        // Se x é maior que y, calcula MDC(x - y, y)
		if(x > y) {
			return mdc(x - y, y);
		}
		
		// Caso contrário, calcula MDC(y, x)
		return mdc(y, x);
	}
	
}
