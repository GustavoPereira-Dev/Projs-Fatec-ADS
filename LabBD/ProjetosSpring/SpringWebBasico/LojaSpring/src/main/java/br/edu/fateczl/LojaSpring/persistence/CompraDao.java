package br.edu.fateczl.LojaSpring.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.LojaSpring.model.Cliente;
import br.edu.fateczl.LojaSpring.model.Compra;

@Repository
public class CompraDao implements ICrud<Compra> {
	
	@Autowired
	private GenericDao gDao;
	
	@Override
	public Compra buscar(Compra compra) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, codigoCliente, codigoProduto, quantidade FROM Compra WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1,compra.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			compra.setCodigo(rs.getLong("codigo"));
			compra.setCodigoCliente(rs.getLong("codigo_cliente"));
			compra.setCodigoProduto(rs.getLong("codigo_produto"));
			compra.setQuantidade(rs.getInt("quantidade"));
		}
		rs.close();
		ps.close();
		return compra;
	}
	
	public List<Compra> listar() throws SQLException, ClassNotFoundException {
		List<Compra> compras = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "CALL SELECT * FROM fn_dadosCompra()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			compra.setCodigo(rs.getLong("codigo"));
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
	
	@Override
	public String inserir(Compra compra ) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_inserir_cliente(?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, compra.getCodigo());
		cs.setLong(2, compra.getCodigoCliente());
		cs.setLong(3, compra.getCodigoProduto());
		cs.setInt(4, compra.getQuantidade());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}

	@Override
	public String atualizar(Compra compra ) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_atualizar_cliente(?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, compra.getCodigo());
		cs.setLong(2, compra.getCodigoCliente());
		cs.setLong(3, compra.getCodigoProduto());
		cs.setInt(4, compra.getQuantidade());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}

	@Override
	public String excluir(Compra compra) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_excluir_cliente(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setLong(1, compra.getCodigo());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();		
		String saida = cs.getString(2);
		
		cs.close();
		
		return saida;
	}

}
