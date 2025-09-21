package br.edu.fateczl.TransacaoProduto.persistence;

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

import br.edu.fateczl.TransacaoProduto.model.Transacao;

@Repository
public class SaidaDao implements ICrud<Transacao>{

	@Autowired
	private GenericDao gDao;
	
	@Override
	public Transacao buscar(Transacao transacao) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT Codigo_Transacao, Codigo_Produto, Quantidade, Valor_Total FROM Saida WHERE Codigo_Transacao = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,transacao.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			transacao.setCodigo(rs.getLong("Codigo_Transacao"));
			transacao.setCodigoProduto(rs.getLong("Codigo_Produto"));
			transacao.setQuantidade(rs.getInt("Quantidade"));
			transacao.setValorTotal(rs.getLong("Valor_Total"));
		}
		rs.close();
		ps.close();
		return transacao;
	}


	@Override
	public List<Transacao> listar() throws SQLException, ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT Codigo_Transacao, Codigo_Produto, Quantidade, Valor_Total FROM Saida";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Transacao transacao = new Transacao();
			transacao.setCodigo(rs.getLong("Codigo_Transacao"));
			transacao.setCodigoProduto(rs.getLong("Codigo_Produto"));
			transacao.setQuantidade(rs.getInt("Quantidade"));
			transacao.setValorTotal(rs.getLong("Valor_Total"));
			transacoes.add(transacao);
		}
		rs.close();
		ps.close();
		return transacoes;
	}

	@Override
	public String inserir(Transacao t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_inserir_transacao(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "s");
		cs.setLong(2, t.getCodigo());
		cs.setLong(3, t.getCodigoProduto());
		cs.setInt(4, t.getQuantidade());
		cs.setFloat(5, t.getValorTotal());
		
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Transacao t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_atualizar_transacao(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "s");
		cs.setLong(2, t.getCodigo());
		cs.setLong(3, t.getCodigoProduto());
		cs.setInt(4, t.getQuantidade());
		cs.setFloat(5, t.getValorTotal());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;
	}

	@Override
	public String excluir(Transacao t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_excluir_transacao(?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "s");
		cs.setLong(2, t.getCodigo());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();		
		String saida = cs.getString(3);
		
		cs.close();
		
		return saida;
	}

}
