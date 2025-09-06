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
import model.Pessoa;

public class PessoaDao implements ICrud<Pessoa> {

	private GenericDao gDao;

	public PessoaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

//	@Override
//	public void inserir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
//		Connection c = gDao.getConnection();
//		String sql = "INSERT INTO pessoa VALUES (?,?,?,?)";
//		PreparedStatement ps = c.prepareStatement(sql);
//		ps.setInt(1,pessoa.getId());
//		ps.setString(2,pessoa.getNome());
//		ps.setString(3, pessoa.getNascimento().toString());
//		ps.setString(4,pessoa.getEmail());
//		ps.execute();
//		ps.close();
//	}
//
//	@Override
//	public void atualizar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
//		Connection c = gDao.getConnection();
//		String sql = "UPDATE pessoa SET nome = ?, nascimento = ?, email =? WHERE id = ?";
//		PreparedStatement ps = c.prepareStatement(sql);
//		ps.setString(1,pessoa.getNome());
//		ps.setString(2, pessoa.getNascimento().toString());
//		ps.setString(3,pessoa.getEmail());
//		ps.setInt(4,pessoa.getId());
//		ps.execute();
//		ps.close();
//	}
//
//	@Override
//	public void excluir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
//		Connection c = gDao.getConnection();
//		String sql = "DELETE pessoa WHERE id = ?";
//		PreparedStatement ps = c.prepareStatement(sql);
//		ps.setInt(1,pessoa.getId());
//		ps.execute();
//		ps.close();
//	}
	
	@Override
	public Pessoa buscar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, nascimento, email FROM pessoa WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1,pessoa.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setNascimento(LocalDate.parse(rs.getString("nascimento")));
			pessoa.setEmail(rs.getString("email"));
		}
		rs.close();
		ps.close();
		return pessoa;
	}

	@Override
	public List<Pessoa> listar() throws SQLException, ClassNotFoundException {
		List<Pessoa> pessoas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, nascimento,"
				+ " CONVERT(CHAR(10), nascimento, 103) AS dtNasc, email "
				+ " FROM pessoa";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setNascimento(LocalDate.parse(rs.getString("nascimento")));
			pessoa.setDtNasc(rs.getString("dtNasc"));
			pessoa.setEmail(rs.getString("email"));
			
			pessoas.add(pessoa);
		}
		rs.close();
		ps.close();
		return pessoas;
	}

//	Chamada de SP deve estar entre chaves
	@Override
	public String inserir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "I");
		cs.setInt(2, pessoa.getId());
		cs.setString(3, pessoa.getNome());
		cs.setString(4, pessoa.getNascimento().toString());
		cs.setString(5, pessoa.getEmail());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "U");
		cs.setInt(2, pessoa.getId());
		cs.setString(3, pessoa.getNome());
		cs.setString(4, pessoa.getNascimento().toString());
		cs.setString(5, pessoa.getEmail());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;

	}

	@Override
	public String excluir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setInt(2, pessoa.getId());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		
		return saida;
	}
}
