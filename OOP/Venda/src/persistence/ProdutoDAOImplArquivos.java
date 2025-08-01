package persistence; 

import java.io.*; 
import java.util.ArrayList; 
import java.util.List; 
import model.Produto; 

public class ProdutoDAOImplArquivos implements ProdutoDAO { 

    private static final String ARQUIVO = "produtos.csv"; 

    @Override 
    public List<Produto> lerTodosContatos() { 
        List<Produto> produtos = new ArrayList<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) { 
            String linha; 
            while ((linha = reader.readLine()) != null) { 
                String[] campos = linha.split(","); 
                if (campos.length == 5) { 
                    Produto produto = new Produto( 
                        Long.parseLong(campos[0]), campos[1], campos[2], 
                        Float.parseFloat(campos[3]), Integer.parseInt(campos[4]) 
                    ); 
                    produtos.add(produto); 
                } 
            } 
        } catch (IOException e) { 
            System.err.println("Erro ao ler o arquivo: " + e.getMessage()); 
        } 
        return produtos; 
    } 

    @Override 
    public void guardar(Produto produto) { 
        try (FileWriter writer = new FileWriter(ARQUIVO, true); 
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) { 
            bufferedWriter.write(produto.getId() + "," + produto.getNome() + "," + produto.getDescricao() + "," + 
                                 produto.getPreco() + "," + produto.getQuantidade()); 
            bufferedWriter.newLine(); 
        } catch (IOException e) { 
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage()); 
        } 
    } 

    @Override 
    public boolean atualizar(Long id, Produto produto) { 
        List<Produto> produtos = lerTodosContatos(); 
        boolean atualizado = false; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) { 
            for (Produto p : produtos) { 
                if (p.getId() == id) { 
                    writer.write(produto.getId() + "," + produto.getNome() + "," + produto.getDescricao() + "," + 
                                 produto.getPreco() + "," + produto.getQuantidade()); 
                    atualizado = true; 
                } else { 
                    writer.write(p.toString()); 
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
        List<Produto> produtos = lerTodosContatos(); 
        boolean removido = false; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) { 
            for (Produto p : produtos) { 
                if (p.getId() != id) { 
                    writer.write(p.toString()); 
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
    public Produto procurarPorId(Long id) { 
        for (Produto produto : lerTodosContatos()) { 
            if (produto.getId() == id) { 
                return produto; 
            } 
        } 
        return null; 
    } 

    @Override 
    public List<Produto> pesquisarPorNome(String nome) { 
        List<Produto> resultado = new ArrayList<>(); 
        for (Produto produto : lerTodosContatos()) { 
            if (produto.getNome().equalsIgnoreCase(nome)) { 
                resultado.add(produto); 
            } 
        } 
        return resultado; 
    } 
} 