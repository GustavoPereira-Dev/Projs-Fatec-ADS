package controller;

public class ConversoesController {
	
	static String[] letrasHexadecimais = {"a","b","c","d","e","f"};
	public String decimalParaBinario(int n) {
		int div = 2;
		boolean divididoAteZero = false;
		StringBuilder s = new StringBuilder();
		while(!divididoAteZero){
			s.append(n % div);
			n /= div;
			if(n < div) {
				s.append(n % div);
				divididoAteZero = true;
			}
			
		}

		s.reverse();
		return s.toString();
	}
	
	public String decimalParaBinario(double numero) {
	    StringBuilder binario = new StringBuilder();
	    int parteInteira = (int) numero;
	    double parteFracionaria = numero - parteInteira;

	    binario.append(Integer.toBinaryString(parteInteira));
	    binario.append(".");

	    while (parteFracionaria > 0) {
	        parteFracionaria *= 2;
	        int parteInteiraFracionaria = (int) parteFracionaria;
	        binario.append(parteInteiraFracionaria);
	        parteFracionaria -= parteInteiraFracionaria;

	        // Limita a precisão a 10 dígitos binários
	        if (binario.length() > 32) {
	            break;
	        }
	    }

	    return binario.toString();
	}
	
	

	
	public String binarioParaDecimal(String n) {
		String[] seq = n.split("");
		int res = 0;
		int mult = 2;
		int cont = seq.length - 1;
		
		for(String s: seq) {
			res += (int)(Integer.parseInt(s) * Math.pow(mult, cont));
			cont--;
		}
		
		return res + "";
		
	}
	
	public int decimalParaOctal(int n) {
		int div = 8;
		boolean divididoAteZero = false;
		StringBuilder s = new StringBuilder();
		while(!divididoAteZero){
			s.append(n % div);
			n /= div;
			if(n < div) {
				s.append(n % div);
				divididoAteZero = true;
			}
			
		}

		s.reverse();
		return Integer.parseInt(s.toString());
	}
	
	
	public double binarioParaDecimalFracao(String binario) {
	    String[] partes = binario.split("\\.");
	    int parteInteira = Integer.parseInt(partes[0], 2);
	    double parteFracionaria = 0;

	    if (partes.length > 1) {
	        String fracao = partes[1];
	        for (int i = 0; i < fracao.length(); i++) {
	            if (fracao.charAt(i) == '1') {
	                parteFracionaria += Math.pow(2, -(i + 1));
	            }
	        }
	    }

	    return parteInteira + parteFracionaria;
	}

	
	public int octalParaDecimal(int n) {
		String[] seq = Integer.toString(n).split("");
		int res = 0;
		int cont = seq.length - 1;
		int mult = 8;
		
		for(String s: seq) {
			res += (int)(Integer.parseInt(s) * Math.pow(mult, cont));
			cont--;
		}
		
		return res;
		
	}
	
	public String decimalParaHexadecimal(int n) {
		int div = 16;
		boolean divididoAteZero = false;
		int mod;
		StringBuilder s = new StringBuilder();
		while(!divididoAteZero){
			mod = n % div;
			System.out.println(mod);
			s.append(mod >= 10 && mod <= 15 ? letrasHexadecimais[mod - 10] : mod);
			if(n < div) break;
			n /= div;
			if(n < div) {
				mod = n % div;
				System.out.println(mod);
				s.append(mod >= 10 && mod <= 15 ? letrasHexadecimais[mod - 10] : mod);
				divididoAteZero = true;
			}
			
		}

		s.reverse();
		return s.toString();
	}
	
	
	
	public String hexadecimalParaDecimal(String n) {
		String[] seq = n.split("");
		int res = 0;
		int cont = seq.length - 1;
		int mult = 16;
		
		for(String s: seq) {
			switch(s) {
				case "a":
					s = "10";
					break;
				case "b":
					s = "11";
					break;
				case "c":
					s = "12";
					break;
				case "d":
					s = "13";
					break;
				case "e":
					s = "14";
					break;
				case "f":
					s = "15";
					break;
			}
			res += (int)(Integer.parseInt(s) * Math.pow(mult, cont));
			cont--;
		}
		
		
		
		return res + "";
		
	}
	
	public String octalParaHexadecimal(int n) {
	    StringBuilder decBin = new StringBuilder();
	    StringBuilder s = new StringBuilder();

	    int resto;
	    int quant;
	    int inicio;
	    for (String p : Integer.toString(n).split("")) {
	        String valor = decimalParaBinario(Integer.parseInt(p));
	        while (valor.length() < 3) {
	            valor = "0" + valor;
	        }
	        decBin.append(valor);
	    }

	    resto = decBin.length() % 4;
	    quant = decBin.length() / 4;
	    if (resto != 0) {
	        s.append(binarioParaHexadecimal(decBin.substring(0, resto)));
	        inicio = resto;
	    } else {
	        inicio = 0;
	    }

	    for (int i = 0; i < quant; i++) {
	        s.append(binarioParaHexadecimal(decBin.substring(inicio, inicio + 4)));
	        inicio += 4;
	    }

	    return s.toString();
	}

	
	public String binarioParaHexadecimal(String binario) {
	    int decimal = Integer.parseInt(binario, 2);
	    return Integer.toHexString(decimal).toUpperCase();
	}

	public String hexadecimalParaBinario(String hex) {
	    int decimal = Integer.parseInt(hex, 16);
	    return Integer.toBinaryString(decimal);
	}

	public String octalParaBinario(String octal) {
	    int decimal = Integer.parseInt(octal, 8);
	    return Integer.toBinaryString(decimal);
	}

	public String binarioParaOctal(String binario) {
	    int decimal = Integer.parseInt(binario, 2);
	    return Integer.toOctalString(decimal);
	}

	public String hexadecimalParaOctal(String hex) {
	    int decimal = Integer.parseInt(hex, 16);
	    return Integer.toOctalString(decimal);
	}

	
}
