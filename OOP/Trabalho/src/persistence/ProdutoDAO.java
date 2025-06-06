package persistence;

import java.util.List;

import model.Produto;

public interface ProdutoDAO {
    List<Produto> lerTodosContatos();
    void guardar(Produto produto);
    boolean atualizar(Long id, Produto produto);
    boolean excluir(Long id);
    Produto procurarPorId(Long id);
    List<Produto> pesquisarPorNome(String nome);    
}
