package edu.curso;


import java.util.ArrayList;
import java.util.List;

public class CursoControl {
    private List<Curso> lista = new ArrayList<>();

    public void gravar(Curso curso) { 
        lista.add(curso);
    }


    public Curso pesquisar(String parteNome) { 
        for (Curso c : lista) { 
            if (c.getNome().contains(parteNome)) { 
                return c;
            }
        }
        return null;
    }

}
