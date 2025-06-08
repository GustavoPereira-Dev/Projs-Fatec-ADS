package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Livro;

public class LivroDAOImplArquivos implements LivroDAO{

    @Override
    public List<Livro> lerTodosLivros() {
        List<Livro> livros = new ArrayList<Livro>();
        Livro livro;
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(","); 
                livro = new Livro(Long.parseLong(campos[0]), campos[1], campos[2], campos[3], campos[4], Float.parseFloat(campos[5]), Integer.parseInt(campos[6]));
                livros.add(livro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        livro.getAutor().
        return livros;

    } 

    /*	private int id;
	private String titulo;
	private String autor;
	private String editora;
	private String categoria;
	private float preco;
	private int quantidade; */
    @Override
    public void guardar(Livro livro) {
        File arquivo = new File("livro.csv");
        boolean arquivoExiste = arquivo.exists();

        try (FileWriter writer = new FileWriter(arquivo, true)) {
            // Se o arquivo não existir, escrevemos o cabeçalho primeiro
            if (!arquivoExiste) {
                writer.write(String.join(",", cabecalho) + "\n");
            } else if (arquivo.length() > 0) {
                writer.write("\n"); // Garante quebra de linha antes de novo registro
            }

            writer.write(String.join(",", dados));
            System.out.println("Linha adicionada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
    }

    @Override
    public boolean atualizar(Long id, Livro livro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public boolean excluir(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public Livro procurarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procurarPorId'");
    }

    @Override
    public List<Livro> pesquisarPorTitulo(String titulo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarPorTitulo'");
    }
    
}
