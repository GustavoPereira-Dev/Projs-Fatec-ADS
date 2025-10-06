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

import br.edu.fateczl.LojaSpring.model.Cliente;

@Repository
public class ClienteDao implements ICrud<Cliente> {

	@Autowired
	private GenericDao gDao;
	
	@Override
	public Cliente buscar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome FROM cliente WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,cliente.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cliente.setCodigo(rs.getLong("codigo"));
			cliente.setNome(rs.getString("nome"));
		}
		rs.close();
		ps.close();
		return cliente;
	}

	@Override
	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome FROM cliente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCodigo(rs.getLong("codigo"));
			cliente.setNome(rs.getString("nome"));
			clientes.add(cliente);
		}
		rs.close();
		ps.close();
		return clientes;
	}

	@Override
	public String inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_inserir_cliente(?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, cliente.getCodigo());
		cs.setString(2, cliente.getNome());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(3);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_atualizar_cliente(?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, cliente.getCodigo());
		cs.setString(2, cliente.getNome());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(3);
		cs.close();
		
		return saida;
	}

	@Override
	public String excluir(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_excluir_cliente(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, cliente.getCodigo());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();		
		String saida = cs.getString(2);
		
		cs.close();
		
		return saida;
	}

}
