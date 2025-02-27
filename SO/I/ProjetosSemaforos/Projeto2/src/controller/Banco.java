package controller;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {
	
	private Semaphore saque;
	private Semaphore deposito;
	private Semaphore transacao;
	private String tipoAcao;
	private int id;
	private int codConta;
	private double saldo;
	private double valorTransacao;
	
	
	
	
	

	public Banco(Semaphore saque, Semaphore deposito, Semaphore transacao, String tipoAcao, int id, int codConta,
			double saldo, double valorTransacao) {
		this.saque = saque;
		this.deposito = deposito;
		this.transacao = transacao;
		this.tipoAcao = tipoAcao;
		this.id = id;
		this.codConta = codConta;
		this.saldo = saldo;
		this.valorTransacao = valorTransacao;
	}

	@Override
	public void run() {
		try {
			banco();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transacao.release();
			
		}
		
	}

	private void banco() throws InterruptedException {
		
		transacao.acquire();
		if(tipoAcao == "Saque") {
			saque.acquire();
			System.out.println("Fazendo saque no valor de " + valorTransacao + " reais na conta " + codConta + " pela transação " + id);
			sleep(1000);
			System.out.println("Saque da " + id + "º transação foi feita com sucesso!");
			System.out.println("Valor da conta " + codConta + " agora: " + (saldo - valorTransacao));
			saque.release();
		} else {
			deposito.acquire();
			System.out.println("Fazendo depósito no valor de " + valorTransacao + " reais na conta " + codConta + " pela transação " + id);
			sleep(1000);
			System.out.println("Depósito da " + id + "º transação foi feita com sucesso!");
			System.out.println("Valor da conta " + codConta + " agora: " + (saldo + valorTransacao));
			deposito.release();
		}
		
		
	}
}
/*3. Um banco deve controlar Saques e Depósitos.
O sistema pode permitir um Saque e um Depósito Simultâneos, mas nunca 2 Saques ou 2
Depósitos Simultâneos. Para calcular a transação (Saque ou Depósito), o método deve
receber o código da conta, o saldo da conta e o valor a ser transacionado. Deve-se montar
um sistema que considera 20 transações simultâneas enviadas ao sistema (aleatoriamente,
essas transações podem ser qualquer uma das opções) e tratar todas as transações. Como
são transações aleatórias, podem ser 20 saques e 0 depósitos ou 19 saques e 1 depósito ou
18 saques e 2 depósitos ou .... ou 1 saque e 19 depósitos ou 0 saque e 20 depósitos.*/