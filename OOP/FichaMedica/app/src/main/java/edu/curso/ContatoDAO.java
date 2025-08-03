package edu.curso;

import java.util.List;

public interface ContatoDAO {
    List<Contato> lerTodosContatos();
    void guardar(Contato contato);
    boolean atualizar(Long id, Contato contato);
    boolean excluir(Long id);
    Contato procurarPorId(Long id);
    List<Contato> pesquisarPorNome(String nome);
}