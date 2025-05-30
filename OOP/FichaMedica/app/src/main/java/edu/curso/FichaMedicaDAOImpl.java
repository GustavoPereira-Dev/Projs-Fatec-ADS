package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class FichaMedicaDAOImpl implements FichaMedicaDAO {
    private static final String DB_URL = "jdbc:mariadb://localhost:3307/pootarde";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "alunofatec";
    private Connection con = null;

    public FichaMedicaDAOImpl() { 
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) { 
            e.printStackTrace();
        }
    }

    private FichaMedica gerarFichaMedicaDoResultSet( ResultSet rs ) { 
    	FichaMedica ficha = new FichaMedica();
        try { 
            ficha.setId(rs.getLong("id"));
            ficha.setTipoSanguineo(rs.getString("tipoSanguineo"));
            ficha.setPeso(rs.getFloat("peso"));
            ficha.setAlergias(rs.getString("tipoSanguineo"));
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return ficha;
    }

    public List<FichaMedica> lerTodosContatos(){
        List<FichaMedica> lista = new ArrayList<>();
        String sql = "SELECT * FROM FichaMedica";
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) { 
                lista.add( gerarContatoDoResultSet(rs ));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return lista;
    }

    public void guardar(FichaMedica ficha){
        String sql = "INSERT INTO FichaMedica (tipoSanguineo, peso, alergias) " + 
        "VALUES (?, ?, ?)";
        System.out.println("guardar() SQL: " + sql);
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ficha.getTipoSanguineo());
            stm.setString(2, ficha.getPeso());
            stm.setString(3, ficha.getAlergias());
            stm.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    public boolean atualizar(Long id, FichaMedica ficha){
        String sql = "UPDATE FichaMedia SET tipoSanguineo = ?, peso = ?, alergias = ? WHERE id = ?";
        
        System.out.println("atualizar() SQL: " + sql);
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ficha.getTipoSanguineo());
            stm.setString(2, ficha.getPeso());
            stm.setString(3, ficha.getAlergias());
            stm.setLong(4, ficha.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluir(Long id){
        String sql = "DELETE FROM FichaMedica WHERE id = ?";
        
        System.out.println("excluir() SQL: " + sql);
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return false;
    }

    public FichaMedica procurarPorId(Long id){
        String sql = "SELECT * FROM FichaMedica WHERE id = ?";
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.first()) { 
                return gerarContatoDoResultSet(rs);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return null;
    }

    public List<FichaMedica> pesquisarPorTipoSanguineo(String tipoSanguineo ){
        List<FichaMedica> lista = new ArrayList<>();
        String sql = "SELECT * FROM FichaMedica WHERE tipoSanguineo LIKE ?";
        try { 
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + tipoSanguineo + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) { 
                lista.add(gerarContatoDoResultSet(rs));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        return lista;
    }
}