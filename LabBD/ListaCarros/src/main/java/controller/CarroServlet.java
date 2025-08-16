package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Carro;
import persistence.GenericDao;
import persistence.CarroDao;

@WebServlet("/carro")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String placa = request.getParameter("placa");
		
		Carro c = new Carro();
		String erro = "";
		List<Carro> carros = new ArrayList<>();
		
		try {
			GenericDao gDao = new GenericDao();
			CarroDao pDao = new CarroDao(gDao);
			
			carros = pDao.listar();
			if (acao != null) {
				c.setPlaca(placa);
				

				
				if (acao.equalsIgnoreCase("excluir")) {
					pDao.excluir(c);
					carros = pDao.listar();
					c = null;
				} else {
					c = pDao.buscar(c);
					carros = null;
				}
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("carro", c);
			request.setAttribute("carros", carros);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("carro.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placa = request.getParameter("placa");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String ano = request.getParameter("ano");
		String cor = request.getParameter("cor");
		String cmd = request.getParameter("botao");
		
		Carro c = new Carro();
		if (!cmd.equalsIgnoreCase("Listar")) {
			c.setPlaca(placa);
		}
		if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
			c.setMarca(marca);
			c.setModelo(modelo);
			c.setAno(Integer.parseInt(ano));
			c.setCor(cor);
		}
		
		GenericDao gDao = new GenericDao();
		CarroDao pDao = new CarroDao(gDao);
		
		String saida = "";
		String erro = "";
		List<Carro> carros = new ArrayList<Carro>();
		
		try {
			if (cmd.equalsIgnoreCase("Inserir")) {
				pDao.inserir(c);
				saida = "Carro "+c.getPlaca()+" inserido com sucesso";
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				pDao.atualizar(c);
				saida = "Carro "+c.getPlaca()+" modifcado com sucesso";
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				pDao.excluir(c);
				saida = "Carro com placa "+c.getPlaca()+" excluido com sucesso";
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				c = pDao.buscar(c);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				carros = pDao.listar();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				c = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				carros = null;
			}
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("carro", c);
			request.setAttribute("carros", carros);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("carro.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
