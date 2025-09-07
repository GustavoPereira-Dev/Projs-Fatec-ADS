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
import model.Cliente;
import persistence.GenericDao;
import persistence.ClienteDao;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String cpf = request.getParameter("cpf");
		
		Cliente c = new Cliente();
		String erro = "";
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			
			GenericDao gDao = new GenericDao();
			ClienteDao cDao = new ClienteDao(gDao);
			clientes = cDao.listar();
			if (acao != null) {
				c.setCpf(cpf);
				

				
				if (acao.equalsIgnoreCase("excluir")) {
					cDao.excluir(c);
					clientes = cDao.listar();
					c = null;
				} else {
					c = cDao.buscar(c);
					clientes = null;
				}
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("cliente", c);
			request.setAttribute("clientes", clientes);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("cliente.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saida = "";
		String erro = "";
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c = new Cliente();
		String cmd = "";
		
		
		try {
			String cpf = request.getParameter("cpf");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String limiteCredito = request.getParameter("limite_credito");
			String nascimento = request.getParameter("nascimento");
			cmd = request.getParameter("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				c.setCpf(cpf);
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				c.setNome(nome);
				c.setEmail(email);
				c.setLimiteCredito(Float.parseFloat(limiteCredito));
				c.setDtNasc(LocalDate.parse(nascimento));
			}
			
			GenericDao gDao = new GenericDao();
			ClienteDao cDao = new ClienteDao(gDao);
			
			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = cDao.inserir(c);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = cDao.atualizar(c);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = cDao.excluir(c);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				c = cDao.buscar(c);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				clientes = cDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				c = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				clientes = null;
			}
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("cliente", c);
			request.setAttribute("clientes", clientes);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("cliente.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
