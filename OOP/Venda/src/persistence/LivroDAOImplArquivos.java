package persistence; 

import java.io.*; 
import java.util.ArrayList; 
import java.util.List; 
import model.Livro; 

public class LivroDAOImplArquivos implements LivroDAO { 
    private static final String ARQUIVO = "livros.csv"; 

    @Override 
    public List<Livro> lerTodosLivros() { 
        List<Livro> livros = new ArrayList<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) { 
            String linha; 
            while ((linha = reader.readLine()) != null) { 
                String[] campos = linha.split(","); 
                if (campos.length == 7) { 
                    Livro livro = new Livro( 
                        Long.parseLong(campos[0]), campos[1], campos[2], campos[3], campos[4], 
                        Float.parseFloat(campos[5]), Integer.parseInt(campos[6]) 
                    ); 
                    livros.add(livro); 
                } 
            } 
        } catch (IOException e) { 
            System.err.println("Erro ao ler o arquivo: " + e.getMessage()); 
        } 
        return livros; 
    } 

    @Override 
    public void guardar(Livro livro) { 
        try (FileWriter writer = new FileWriter(ARQUIVO, true); 
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) { 
            bufferedWriter.write(livro.getId() + "," + livro.getTitulo() + "," + livro.getAutor() + "," + 
                                 livro.getEditora() + "," + livro.getCategoria() + "," + livro.getPreco() + "," + 
                                 livro.getQuantidade()); 
            bufferedWriter.newLine(); 
        } catch (IOException e) { 
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage()); 
        } 
    } 
  
    @Override 
    public boolean atualizar(Long id, Livro livro) { 
        List<Livro> livros = lerTodosLivros(); 
        boolean atualizado = false; 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) { 
            for (Livro l : livros) { 
                if (l.getId() == id) { 
                    writer.write(livro.getId() + "," + livro.getTitulo() + "," + livro.getAutor() + "," + 
                                 livro.getEditora() + "," + livro.getCategoria() + "," + livro.getPreco() + "," + 
                                 livro.getQuantidade()); 
                    atualizado = true; 
                } else { 
                    writer.write(l.toString()); 
                } 
                writer.newLine(); 
            } 
        } catch (IOException e) { 
            System.err.println("Erro ao atualizar o arquivo: " + e.getMessage()); 
        } 
        return atualizado; 
    } 

    @Override 
    public boolean excluir(Long id) { 
        List<Livro> livros = lerTodosLivros(); 
        boolean removido = false; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) { 
            for (Livro l : livros) { 
                if (l.getId() != id) { 
                    writer.write(l.toString()); 
                    writer.newLine(); 
                } else { 
                    removido = true; 
                } 
            } 
        } catch (IOException e) { 
            System.err.println("Erro ao excluir do arquivo: " + e.getMessage()); 
        } 
        return removido; 
    } 

    @Override 
    public Livro procurarPorId(Long id) { 
        for (Livro livro : lerTodosLivros()) { 
            if (livro.getId() == id) { 
                return livro; 
            } 
        } 
        return null; 
    } 
  
    @Override 
    public List<Livro> pesquisarPorTitulo(String titulo) { 
        List<Livro> resultado = new ArrayList<>(); 
        for (Livro livro : lerTodosLivros()) { 
            if (livro.getTitulo().equalsIgnoreCase(titulo)) { 
                resultado.add(livro); 
            } 
        } 
        return resultado; 
    } 
} 