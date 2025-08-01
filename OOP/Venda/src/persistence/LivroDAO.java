package persistence;

import java.util.List;

import model.Livro;

public interface LivroDAO {
    List<Livro> lerTodosLivros();
    void guardar(Livro livro);
    boolean atualizar(Long id, Livro livro);
    boolean excluir(Long id);
    Livro procurarPorId(Long id);
    List<Livro> pesquisarPorTitulo(String titulo);    
}
