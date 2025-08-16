package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Carro;

public class CarroDao implements ICrud<Carro> {

	private GenericDao gDao;

	public CarroDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Carro carro) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO Carro VALUES (?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,carro.getPlaca());
		ps.setString(2,carro.getMarca());
		ps.setString(3, carro.getModelo());
		ps.setInt(4, carro.getAno());
		ps.setString(5,carro.getCor());
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizar(Carro carro) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE Carro SET Marca = ?, Modelo = ?, Ano = ?, Cor = ? WHERE Placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,carro.getMarca());
		ps.setString(2, carro.getModelo());
		ps.setInt(3, carro.getAno());
		ps.setString(4,carro.getCor());
		ps.setString(5,carro.getPlaca());
		ps.execute();
		ps.close();
	}

	@Override
	public void excluir(Carro carro) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE Carro WHERE Placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,carro.getPlaca());
		ps.execute();
		ps.close();
	}

	@Override
	public Carro buscar(Carro carro) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT Placa, Marca, Modelo, Ano, Cor FROM Carro WHERE Placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1,carro.getPlaca());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			carro.setPlaca(rs.getString("Placa"));
			carro.setMarca(rs.getString("Marca"));
			carro.setModelo(rs.getString("Modelo"));
			carro.setAno(rs.getInt("Ano"));
			carro.setCor(rs.getString("Cor"));
		}
		rs.close();
		ps.close();
		return carro;
	}

	@Override
	public List<Carro> listar() throws SQLException, ClassNotFoundException {
		List<Carro> carros = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT Placa, Marca, Modelo, Ano, Cor FROM Carro";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Carro carro = new Carro();
			carro.setPlaca(rs.getString("Placa"));
			carro.setMarca(rs.getString("Marca"));
			carro.setModelo(rs.getString("Modelo"));
			carro.setAno(rs.getInt("Ano"));
			carro.setCor(rs.getString("Cor"));
			
			carros.add(carro);
		}
		rs.close();
		ps.close();
		return carros;
	}
}
