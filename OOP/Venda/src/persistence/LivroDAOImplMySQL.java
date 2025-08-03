package persistence;

import model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImplMySQL implements LivroDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/estoque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "teste";
    private Connection con;

    public LivroDAOImplMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Livro gerarLivroDoResultSet(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getLong("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setEditora(rs.getString("editora"));
        livro.setCategoria(rs.getString("categoria"));
        livro.setPreco(rs.getFloat("preco"));
        livro.setQuantidade(rs.getInt("quantidade"));
        return livro;
    }

    @Override
    public List<Livro> lerTodosLivros() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                lista.add(gerarLivroDoResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void guardar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, editora, categoria, preco, quantidade) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setString(3, livro.getEditora());
            stm.setString(4, livro.getCategoria());
            stm.setFloat(5, livro.getPreco());
            stm.setInt(6, livro.getQuantidade());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean atualizar(Long id, Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, categoria = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setString(3, livro.getEditora());
            stm.setString(4, livro.getCategoria());
            stm.setFloat(5, livro.getPreco());
            stm.setInt(6, livro.getQuantidade());
            stm.setLong(7, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluir(Long id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Livro procurarPorId(Long id) {
        String sql = "SELECT * FROM livro WHERE id = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return gerarLivroDoResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + titulo + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(gerarLivroDoResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}