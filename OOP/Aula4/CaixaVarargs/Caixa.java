
public class Caixa {
	private double saldo = 0.0;
	
	public double venda(double... valoresProdutos) {
		for(double valor: valoresProdutos) {
			saldo += valor;
		}
		return saldo;
	}

}
