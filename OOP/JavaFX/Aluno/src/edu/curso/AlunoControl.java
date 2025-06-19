package edu.curso;

import java.util.ArrayList;
import java.util.List;

public class AlunoControl {
    private List<Aluno> lista = new ArrayList<>();

    public void adicionar(Aluno a) { 
        lista.add(a);
    }


    public List<Aluno> pesquisarPorNome(String nome) { 
    	List<Aluno> nomes = new ArrayList<>();
        for (Aluno c : lista) { 
            if (c.getNome().contains(nome)) { 
                nomes.add(c);
            }
        }
        return nomes;
    }

}
