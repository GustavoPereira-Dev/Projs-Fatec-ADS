package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.estrutura.lista.Lista;
import model.estrutura.lista.No;
import model.estrutura.pilha.Pilha;





public class FifaController implements IFifaController {

	@Override
	public Pilha<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		File arq = new File(caminho,nome);
		Pilha<String> pilha = new Pilha<String>();
		
		
		if(arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] colunas;
			linha = buffer.readLine();
			while(linha != null){
				colunas = linha.split(",");
				
				if(colunas[5].contains("Brazil")) {
					pilha.push(linha);
				}
				
				linha = buffer.readLine();
			} 
			buffer.close();
			leitor.close();
			fluxo.close();
		} else{
			throw new IOException("Arquivo Inválido");
		}

		
		return pilha;
	}

	@Override
	public void desmpilhaBonsBrasileiros(Pilha<String> pilha) throws IOException {
		
		String[] colunas;
		while(pilha.ultimo != null) {
			colunas = pilha.ultimo.getValor().split(",");
			
			if(Integer.parseInt(colunas[7]) > 80) {
				System.out.println(pilha.ultimo.getValor());
			}
			
			pilha.pop();
		}
	}

	@Override
	public Lista<String> listaRevelacoes(String caminho, String nome) throws IOException {
		File arq = new File(caminho,nome);
		Lista<String> lista = new Lista<String>();
		
		if(arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String[] colunas;
			linha = buffer.readLine();
			while(linha != null){
				colunas = linha.split(",");
				
				if(Integer.parseInt(colunas[3]) <= 20) {
					lista.append(linha);
				}
				
				linha = buffer.readLine();
			} 
			buffer.close();
			leitor.close();
			fluxo.close();
		} else{
			throw new IOException("Arquivo Inválido");
		}

		
		return lista;
	}

	@Override
	public void buscaListaBonsJovens(Lista<String> lista) throws IOException {
		String[] colunas;
		
		while(lista.fim != null) {
			colunas = lista.fim.getValor().split(",");
			
			if(Integer.parseInt(colunas[7]) > 80) {
				System.out.println("Nome: " + colunas[2] + "; Idade: " + colunas[3] + " Overall: " + colunas[7]);
			}
			
			lista.fim = lista.fim.getAnterior();
		}
	}

	

	
	

	
	/*O método empilhaBrasileiros recebe o caminho e o nome do arquivo, deverá inicializar uma pilha, abrir o arquivo,
	ler o arquivo, verificar se na coluna referente à nacionalidade existe o valor “Brazil” e empilhar (push) a linha
	inteira, apenas de jogadores brasileiros. O método deve retornar essa pilha.
	O método desempilhaBonsBrasileiros deve receber uma pilha de Strings como parâmetro, percorrer a pilha,
	desempilhar (pop) e imprimir (somente nome e Overall) apenas de jogadores com Overall acima de 80. Perceba
	que, como estão ordenados, no arquivo, do melhor para o pior, os jogadores serão empilhados do melhor para o
	pior, portanto, o primeiro impresso deverá ser o pior dentre os escolhidos e o último deverá ser Neymar Jr, com
	Overall 92.
	O método listaRevelacoes recebe o caminho e o nome do arquivo, deverá inicializar uma lista encadeada, abrir o
	arquivo, ler o arquivo, verificar se na coluna referente à idade o valor é menor ou igual a 20, e adicionar ao final da
	lista, a linha inteira, apenas de jogadores jovens. O método deve retornar essa lista.
	O método buscaListaBonsJovens deve receber uma lista de Strings como parâmetro, percorrer a lista do último
	para o primeiro, imprimir (somente nome, idade e Overall) apenas de jogadores com Overall acima de 80 e 20
	anos ou menos.*/
}
