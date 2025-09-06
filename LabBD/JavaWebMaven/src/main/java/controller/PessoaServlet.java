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
import model.Pessoa;
import persistence.GenericDao;
import persistence.PessoaDao;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PessoaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		
		Pessoa p = new Pessoa();
		String erro = "";
		List<Pessoa> pessoas = new ArrayList<>();
		
		try {
			
			GenericDao gDao = new GenericDao();
			PessoaDao pDao = new PessoaDao(gDao);
			pessoas = pDao.listar();
			if (acao != null) {
				p.setId(Integer.parseInt(id));
				

				
				if (acao.equalsIgnoreCase("excluir")) {
					pDao.excluir(p);
					pessoas = pDao.listar();
					p = null;
				} else {
					p = pDao.buscar(p);
					pessoas = null;
				}
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("pessoa", p);
			request.setAttribute("pessoas", pessoas);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("pessoa.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saida = "";
		String erro = "";
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa p = new Pessoa();
		String cmd = "";
		
		
		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String nascimento = request.getParameter("nascimento");
			String email = request.getParameter("email");
			cmd = request.getParameter("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				p.setId(Integer.parseInt(id));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				p.setNome(nome);
				p.setNascimento(LocalDate.parse(nascimento));
				p.setEmail(email);
			}
			
			GenericDao gDao = new GenericDao();
			PessoaDao pDao = new PessoaDao(gDao);
			
			if (cmd.equalsIgnoreCase("Inserir")) {
				pDao.inserir(p);
				saida = "Pessoa "+p.getNome()+" inserida com sucesso";
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				pDao.atualizar(p);
				saida = "Pessoa "+p.getNome()+" modifcada com sucesso";
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				pDao.excluir(p);
				saida = "Pessoa "+p.getId()+" excluida com sucesso";
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				p = pDao.buscar(p);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				pessoas = pDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				p = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				pessoas = null;
			}
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("pessoa", p);
			request.setAttribute("pessoas", pessoas);

			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("pessoa.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
