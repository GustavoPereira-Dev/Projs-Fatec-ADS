package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

        static Produto produto = null;
        private List<Produto> produtos = new ArrayList<>();
        
        /*
         *         private int id;
        private String nome;
        private String descricao;
        private float preco;
        private int quantidade;
         * */
        public void createProduto() {
                int id, quantidade;
                String nome, descricao;
                float preco;

                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto"));
                nome = JOptionPane.showInputDialog("Digite o nome do produto");
                descricao = JOptionPane.showInputDialog("Digite a descricao do produto");
                preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o preco do produto"));
                quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto"));

                produto = new Produto(id, nome, descricao, preco, quantidade);
                produtos.add(produto);
                JOptionPane.showMessageDialog(null, "Produto criado!");
        }

        public String readProduto() {
             int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto"));
                for(Produto p: produtos){
                    if(p.getId() == id){
                        return p.toString();
                    }
                }    
                return "Produto não encontrado";
        }

        public void updateProduto(int id) throws IOException{
                int opc, c, size = produtos.size();;
                String s;

                for(Produto p: produtos){
                    size--;
                    if(p.getId() == id){
                        do {

                                opc = Integer.parseInt(JOptionPane.showInputDialog("Que atributo quer atualizar? \n 1 - Nome \n 2 - Descricao \n 3 - Preco \n 4 - Quantidade"));
                                s = JOptionPane.showInputDialog("Digite o valor do campo novo");

                        try {
                                switch(opc) {
                                        case 1:
                                                p.setNome(s);
                                                break;
                                        case 2:
                                                p.setDescricao(s);
                                                break;
                                        case 3:
                                                p.setPreco(Float.parseFloat(s));
                                                break;
                                        case 4:
                                                produto.setQuantidade(Integer.parseInt(s));
                                                break;

                                }
                        } catch(Exception e) {
                                throw new IOException("Tipo de valor adicionado em campo incorreto");
                        }


                        c = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar atualizando o objeto?\n1 para sim 0 para nao"));
                        } while(c != 1);

                        JOptionPane.showMessageDialog(null, "Campos atualizados com sucesso!");
                } else {
                        throw new IOException("Erro na atualizacao de campos");
                }
                    
                }




        }

        public boolean deleteProduto(int id) {
                int cont = 0;
                for(Produto p: produtos){
                    if(p.getId() == id){
                        produtos.remove(cont);
                        return true;
                    }
                    cont++;
                }    
                return false;
        
        }
}