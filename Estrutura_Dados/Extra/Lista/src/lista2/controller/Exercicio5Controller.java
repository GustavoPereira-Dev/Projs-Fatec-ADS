package lista2.controller;


import java.io.*;
import lista2.model.Cliente;
import lista2.model.lista.ListaDupla;

public class Exercicio5Controller {
    
    private void novoArquivo(ListaDupla<Cliente> lista, String nomeArquivo) throws IOException {
        File file = new File("C:\\TEMP", nomeArquivo);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < lista.getTotal(); i++) {
            buffer.append(lista.get(i).getValor().toString()).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(buffer.toString());
        }
    }

    public void novoCadastro(int idadeMin, int idadeMax, double limiteCredito) throws IOException {
    	ListaDupla<Cliente> listaClientes = new ListaDupla<Cliente>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\TEMP\\cadastro.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Cliente cliente = new Cliente(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]));
                if (cliente.getIdade() >= idadeMin && cliente.getIdade() <= idadeMax && cliente.getLimiteCredito() > limiteCredito) {
                    listaClientes.append(cliente);
                }
            }
        }
        String nomeArquivo = "Idade_" + idadeMin + "_a_" + idadeMax + "_limite_" + limiteCredito + ".csv";
        novoArquivo(listaClientes, nomeArquivo);
    }
}

