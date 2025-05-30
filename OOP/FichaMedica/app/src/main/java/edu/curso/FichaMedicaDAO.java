package edu.curso;

import java.util.List;

public interface FichaMedicaDAO {
    List<FichaMedica> lerTodosContatos();
    void guardar(FichaMedica ficha);
    boolean atualizar(Long id, FichaMedica ficha);
    boolean excluir(Long id);
    FichaMedica procurarPorId(Long id);
    List<FichaMedica> pesquisarPorTipoSanguineo(String tipoSanguineo);
}