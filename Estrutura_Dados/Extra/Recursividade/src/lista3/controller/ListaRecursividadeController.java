package lista3.controller;

public class ListaRecursividadeController {
	public int quantidadeDigitos(int n, int digito) {
		// Condição de parada: quando n é menor ou menor que 0, a quantidade de digitos e 0
		if(n < digito) {
			return 0;
		}
		
		// Relação de chamada dos passos: somatoria 1 + quantidadeDigitos(n, digito * 10)
		return 1 + quantidadeDigitos(n, digito * 10);
	}
	
	public int repeticaoNumero(int n, int rep) {
		int quant = 0;
		
		// Condição de parada: quando n dividido por 10 é menor que 0 estamos na ultimo repeticao
		if(n / 10 == 0) {
			return n % 10 == rep ? 1 : 0;
		}
		
		// Relação de chamada dos passos: atribuicao repeticaoNumero(n / 10, rep)
		quant = repeticaoNumero(n / 10, rep);
		if(n % 10 == rep) {
			quant++;
		}
		
		return quant;
	}
	
	public String inversaoCaracteres(String texto, int i) {
		StringBuilder res = new StringBuilder();
		
		// Condição de parada: quando i for menor que 0 o valor e uma String nula
		if(i < 0) {
			return "";
		}
		
		// Relação de chamada dos passos: adicao no String builder de texto.substring(i,i+1) + inversaoCaracteres(texto, i - 1
		res.append(texto.substring(i,i+1) + inversaoCaracteres(texto, i - 1));
		return res.toString();
	}
	
	public String conversaoBinaria(int n) {
		
		// Condição de parada: quando i for menor que 0 o valor e uma String nula
		if(n == 0) {
			return "";
		}
		
		// Relação de chamada dos passos: atribuicao conversaoBinaria(n / 2) + n % 2
		return conversaoBinaria(n / 2) + n % 2;
	}
	
	public int calcFib(int a1, int a2, int indx) {
		
		// Condição de parada: quando index for menor ou igual que 2 soma-se os anteriores (1 e 0)
		if(indx <= 2) {
			return a1 + a2;
		}
		
		
		return calcFib(a2,(a1 + a2),indx - 1);
	}
	
	public double somarSequenciaFatoriais(double n) {
		double serie = 0;
		
		
		if(n != 1) {
			// Relação de chamada dos passos: razao somarSequenciaFatoriais(n - 1) / calcFat((int)(n))
			serie = (double) (somarSequenciaFatoriais(n - 1) / calcFat((int)(n)));
			return serie;
		}
		// Condição de parada: quando i for igual a 1 o valor retornado e 1
		return 1;
	}
	
	private int calcFat(int n) {
		if(n > 1) {
			// Relação de chamada dos passos: produto n * calcFat(n - 1
			return n * calcFat(n - 1);
		}
		// Condição de parada: quando n for menor ou igual a 1 o valor sera 1
		return 1;
	}
}
