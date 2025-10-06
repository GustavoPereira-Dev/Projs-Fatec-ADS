package br.edu.fateczl.LojaSpring.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.LojaSpring.model.Produto;

@Repository
public class ProdutoDao implements ICrud<Produto> {

	@Autowired
	private GenericDao gDao;
	
	@Override
	public Produto buscar(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor FROM produto WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,produto.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			produto.setCodigo(rs.getLong("codigo"));
			produto.setNome(rs.getString("nome"));
			produto.setValor(rs.getDouble("valor"));
		}
		rs.close();
		ps.close();
		return produto;
	}

	@Override
	public List<Produto> listar() throws SQLException, ClassNotFoundException {
		List<Produto> produtos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor FROM produto ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Produto produto = new Produto();
			produto.setCodigo(rs.getLong("codigo"));
			produto.setNome(rs.getString("nome"));
			produto.setValor(rs.getDouble("valor"));
			produtos.add(produto);
		}
		rs.close();
		ps.close();
		return produtos;
	}

	@Override
	public String inserir(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_inserir_produto(?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, produto.getCodigo());
		cs.setString(2, produto.getNome());
		cs.setDouble(3, produto.getValor());
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(4);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_atualizar_produto(?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, produto.getCodigo());
		cs.setString(2, produto.getNome());
		cs.setDouble(3, produto.getValor());
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(4);
		cs.close();
		
		return saida;
	}

	@Override
	public String excluir(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_excluir_produto(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, produto.getCodigo());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();		
		String saida = cs.getString(2);
		
		cs.close();
		
		return saida;
	}

}
