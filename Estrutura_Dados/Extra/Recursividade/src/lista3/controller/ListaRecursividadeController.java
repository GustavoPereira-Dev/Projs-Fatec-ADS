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
		
		if(n / 10 == 0) {
			return n % 10 == rep ? 1 : 0;
		}
		
		quant = repeticaoNumero(n / 10, rep);
		if(n % 10 == rep) {
			quant++;
		}
		
		return quant;
	}
	
	public String inversaoCaracteres(String texto, int i) {
		StringBuilder res = new StringBuilder();
		if(i < 0) {
			return "";
		}
		
		res.append(texto.substring(i,i+1) + inversaoCaracteres(texto, i - 1));
		return res.toString();
	}
	
	public String conversaoBinaria(int n) {
		if(n == 0) {
			return "";
		}
		
		return conversaoBinaria(n / 2) + n % 2;
	}
	
	public int calcFib(int a1, int a2, int indx) {
		if(indx == 2) {
			return a1 + a2;
		}
		
		return calcFib(a2,(a1 + a2),indx - 1);
	}
	
	public double somarSequenciaFatoriais(double n) {
		double serie = 0;
		
		if(n != 1) {
			serie = (double) (somarSequenciaFatoriais(n - 1) / calcFat((int)(n)));
			return serie;
		}
		return 1;
	}
	
	private int calcFat(int n) {
		if(n > 1) {
			return n * calcFat(n - 1);
		}
		return 1;
	}
}
