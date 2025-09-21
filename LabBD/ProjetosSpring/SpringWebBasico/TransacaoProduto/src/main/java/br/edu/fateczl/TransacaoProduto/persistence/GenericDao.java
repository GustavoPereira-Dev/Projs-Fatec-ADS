package br.edu.fateczl.TransacaoProduto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDao {

	private Connection c;

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		String hostName = "localhost";
		String dbName = "Produto";
		String user = "leandro";
		String senha = "12345678";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:51444;databaseName=%s;user=%s;password=%s;", 
				hostName, dbName, user, senha));

		return c;
	}

}