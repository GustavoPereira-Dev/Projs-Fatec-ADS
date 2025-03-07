
public class Caixa {
	private double saldo = 0.0;
	
	public double venda(double valorProduto) {
		saldo += valorProduto;
		return saldo;
	}

}
