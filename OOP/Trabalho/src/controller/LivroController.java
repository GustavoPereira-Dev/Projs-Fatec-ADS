package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroController {

        static Livro livro = null;
        private List<Livro> livros = new ArrayList<>();
    
        public void createLivro() {
                int id, quantidade;
                String titulo, autor, editora, categoria;
                float preco;

                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do livro"));
                titulo = JOptionPane.showInputDialog("Digite o titulo do livro");
                autor = JOptionPane.showInputDialog("Digite o autor do livro");
                editora = JOptionPane.showInputDialog("Digite a editora do livro");
                categoria = JOptionPane.showInputDialog("Digite a categoria do livro");
                preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o preco do livro"));
                quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do livro"));

                livro = new Livro(id, titulo, autor, editora, categoria, preco, quantidade);
                livros.add(livro);
                JOptionPane.showMessageDialog(null, "Livro criado!");
        }

        public String readLivro() {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do livro"));
                for(Livro l: livros){
                    if(l.getId() == id){
                        return l.toString();
                    }
                }    
                return "Livro não encontrado";
        }

        public void updateLivro(int id) throws IOException{
                int opc, c, size = livros.size();
                String s;
                for(Livro l: livros){
                    size--;
                    if(l.getId() == id){
                
                
                        do {

                                opc = Integer.parseInt(JOptionPane.showInputDialog("Que atributo quer atualizar? \n 1 - Titulo \n 2 - Autor \n 3 - Editora \n 4 - Categoria \n 5 - Preco \n 6 - Quantidade"));
                                s = JOptionPane.showInputDialog("Digite o valor do campo novo");

                        try {
                                switch(opc) {
                                        case 1:
                                                l.setTitulo(s);
                                                break;
                                        case 2:
                                                l.setAutor(s);
                                                break;
                                        case 3:
                                                l.setEditora(s);
                                                break;
                                        case 4:
                                                l.setCategoria(s);
                                                break;
                                        case 5:
                                                l.setPreco(Float.parseFloat(s));
                                                break;
                                        case 6:
                                                l.setQuantidade(Integer.parseInt(s));
                                                break;
                                }            
                        } catch(Exception e) {
                                throw new IOException("Tipo de valor adicionado em campo incorreto");
                        }


                        c = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar atualizando o objeto?\n1 para sim 0 para nao"));
                        } while(c != 1);

                        JOptionPane.showMessageDialog(null, "Campos atualizados com sucesso!");
                } else if (size == 0) {
                        throw new IOException("Erro na atualizacao de campos");
                }
                }




        }

        public boolean deleteLivro(int id) {
                int cont = 0;
                for(Livro l: livros){
                    if(l.getId() == id){
                        livros.remove(cont);
                        return true;
                    }
                    cont++;
                }    
                return false;
        }

}