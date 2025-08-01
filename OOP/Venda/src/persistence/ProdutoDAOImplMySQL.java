package persistence; 

import java.sql.*; import java.util.ArrayList; import java.util.List; import model.Produto; 

public class ProdutoDAOImplMySQL implements ProdutoDAO { private static final String DB_URL = "jdbc:mysql://localhost:3306/estoque"; private static final String DB_USER = "root"; private static final String DB_PASSWORD = "senha"; private Connection con; 

public ProdutoDAOImplMySQL() { 
    try { 
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
    } catch (ClassNotFoundException | SQLException e) { 
        e.printStackTrace(); 
    } 
} 
 
private Produto gerarProdutoDoResultSet(ResultSet rs) { 
    try { 
        return new Produto( 
            rs.getLong("id"), 
            rs.getString("nome"), 
            rs.getString("descricao"), 
            rs.getFloat("preco"), 
            rs.getInt("quantidade") 
        ); 
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return null; 
    } 
} 
 
@Override 
public List<Produto> lerTodosContatos() { 
    List<Produto> lista = new ArrayList<>(); 
    String sql = "SELECT * FROM Produto"; 
    try (PreparedStatement stm = con.prepareStatement(sql); 
         ResultSet rs = stm.executeQuery()) { 
        while (rs.next()) { 
            lista.add(gerarProdutoDoResultSet(rs)); 
        } 
    } catch (SQLException e) { 
        e.printStackTrace(); 
    } 
    return lista; 
} 
 
@Override 
public void guardar(Produto produto) { 
    String sql = "INSERT INTO Produto (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)"; 
    try (PreparedStatement stm = con.prepareStatement(sql)) { 
        stm.setString(1, produto.getNome()); 
        stm.setString(2, produto.getDescricao()); 
        stm.setFloat(3, produto.getPreco()); 
        stm.setInt(4, produto.getQuantidade()); 
        stm.executeUpdate(); 
    } catch (SQLException e) { 
        e.printStackTrace(); 
    } 
} 
 
@Override 
public boolean atualizar(Long id, Produto produto) { 
    String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?"; 
    try (PreparedStatement stm = con.prepareStatement(sql)) { 
        stm.setString(1, produto.getNome()); 
        stm.setString(2, produto.getDescricao()); 
        stm.setFloat(3, produto.getPreco()); 
        stm.setInt(4, produto.getQuantidade()); 
        stm.setLong(5, id); 
        return stm.executeUpdate() > 0; 
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return false; 
    } 
} 
 
@Override 
public boolean excluir(Long id) { 
    String sql = "DELETE FROM Produto WHERE id = ?"; 
    try (PreparedStatement stm = con.prepareStatement(sql)) { 
        stm.setLong(1, id); 
        return stm.executeUpdate() > 0; 
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return false; 
    } 
} 
 
@Override 
public Produto procurarPorId(Long id) { 
    String sql = "SELECT * FROM Produto WHERE id = ?"; 
    try (PreparedStatement stm = con.prepareStatement(sql)) { 
        stm.setLong(1, id); 
        ResultSet rs = stm.executeQuery(); 
        if (rs.next()) { 
            return gerarProdutoDoResultSet(rs); 
        } 
    } catch (SQLException e) { 
        e.printStackTrace(); 
    } 
    return null; 
} 
 
@Override 
public List<Produto> pesquisarPorNome(String nome) { 
    List<Produto> lista = new ArrayList<>(); 
    String sql = "SELECT * FROM Produto WHERE nome LIKE ?"; 
    try (PreparedStatement stm = con.prepareStatement(sql)) { 
        stm.setString(1, "%" + nome + "%"); 
        ResultSet rs = stm.executeQuery(); 
        while (rs.next()) { 
            lista.add(gerarProdutoDoResultSet(rs)); 
        } 
    } catch (SQLException e) { 
        e.printStackTrace(); 
    } 
    return lista; 
} 
  

} 