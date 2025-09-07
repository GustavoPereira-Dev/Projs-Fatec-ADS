package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDao implements ICrud<Cliente> {

	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public Cliente buscar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT cpf, nome, email, limite_de_credito, Dt_Nascimento FROM cliente WHERE cpf = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,cliente.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cliente.setCpf(rs.getString("CPF"));
			cliente.setNome(rs.getString("Nome"));
			cliente.setEmail(rs.getString("Email"));
			cliente.setLimiteCredito(rs.getFloat("Limite_de_credito"));
			cliente.setDtNasc(LocalDate.parse(rs.getString("Dt_Nascimento")));
		}
		rs.close();
		ps.close();
		return cliente;
	}

	@Override
	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT cpf, nome, email, limite_de_credito, dt_nascimento FROM cliente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCpf(rs.getString("CPF"));
			cliente.setNome(rs.getString("Nome"));
			cliente.setEmail(rs.getString("Email"));
			cliente.setLimiteCredito(rs.getFloat("Limite_de_credito"));
			cliente.setDtNasc(LocalDate.parse(rs.getString("dt_nascimento")));
			
			clientes.add(cliente);
		}
		rs.close();
		ps.close();
		return clientes;
	}

	@Override
	public String inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_inserir_cliente(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, cliente.getCpf());
		cs.setString(2, cliente.getNome());
		cs.setString(3, cliente.getEmail());
		cs.setFloat(4, cliente.getLimiteCredito());
		cs.setString(5, cliente.getDtNasc().toString());

		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_atualizar_cliente(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, cliente.getCpf());
		cs.setString(2, cliente.getNome());
		cs.setString(3, cliente.getEmail());
		cs.setFloat(4, cliente.getLimiteCredito());
		cs.setString(5, cliente.getDtNasc().toString());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;

	}

	@Override
	public String excluir(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_excluir_cliente(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, cliente.getCpf());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();		
		String saida = cs.getString(2);
		
		cs.close();
		
		return saida;
	}
}
