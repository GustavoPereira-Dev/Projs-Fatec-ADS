package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaDao implements ICrud<Pessoa> {

	private GenericDao gDao;

	public PessoaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO pessoa VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1,pessoa.getId());
		ps.setString(2,pessoa.getNome());
		ps.setString(3, pessoa.getNascimento().toString());
		ps.setString(4,pessoa.getEmail());
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizar(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE pessoa SET nome = ?, nascimento = ?, email =? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,pessoa.getNome());
		ps.setString(2, pessoa.getNascimento().toString());
		ps.setString(3,pessoa.getEmail());
		ps.setInt(4,pessoa.getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE pessoa WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1,pessoa.getId());
		ps.execute();
		ps.close();
	}

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
}
