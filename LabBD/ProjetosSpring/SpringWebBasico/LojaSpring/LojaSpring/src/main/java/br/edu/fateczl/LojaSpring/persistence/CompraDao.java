package br.edu.fateczl.LojaSpring.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.LojaSpring.model.Compra;

@Repository
public class CompraDao {
	
	@Autowired
	private GenericDao gDao;
	
	public List<Compra> listar() throws SQLException, ClassNotFoundException {
		List<Compra> compras = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "CALL SELECT * FROM fn_dadosCompra()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			compra.setNomeCliente(rs.getString("Nome_Cliente"));
			compra.setNomeProduto(rs.getString("Nome_Produto"));
			compra.setQuantidade(rs.getInt("Quantidade"));
			compra.setValorTotal(rs.getDouble("Valor_Total"));
			compra.setDataCompra(LocalDate.parse(rs.getString("Data_Hoje")));
			compras.add(compra);
		}
		rs.close();
		ps.close();
		return compras;
	}

}
