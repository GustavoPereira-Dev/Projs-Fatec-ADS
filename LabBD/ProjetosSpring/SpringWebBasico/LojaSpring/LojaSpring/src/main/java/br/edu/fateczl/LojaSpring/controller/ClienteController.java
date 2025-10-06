package br.edu.fateczl.LojaSpring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.LojaSpring.model.Cliente;
import br.edu.fateczl.LojaSpring.persistence.ClienteDao;

@Controller
public class ClienteController {

	@Autowired
	private ClienteDao cDao;

	@RequestMapping(name = "cliente", value = "/cliente", method = RequestMethod.GET)
	public ModelAndView clienteGet(
			@RequestParam Map<String, String> 
			params, 
			ModelMap model) {
			String acao = params.get("acao");
			String codigo = params.get("codigo");

			Cliente c = new Cliente();
			String erro = "";
			List<Cliente> clientes = new ArrayList<>();

				try {
					if (acao != null) {
						c.setCodigo(Long.parseLong(codigo));

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
					model.addAttribute("erro", erro);
					model.addAttribute("cliente", c);
					model.addAttribute("clientes", clientes);
				}
			return new ModelAndView("cliente");
	}

	@RequestMapping(name = "cliente", value = "/cliente", method = RequestMethod.POST)
	public ModelAndView clientePost(
			@RequestParam Map<String, String> params, 
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c = new Cliente();
		String cmd = "";

		try {
			String codigo = params.get("codigo");
			String nome = params.get("nome");
			cmd = params.get("botao");

			if (!cmd.equalsIgnoreCase("Listar")) {
				c.setCodigo(Long.parseLong(codigo));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				c.setNome(nome);
			}

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
				System.out.println("Listando");
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
			
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("cliente", c);
			model.addAttribute("clientes", clientes);
			
		}

		return new ModelAndView("cliente");
	}
}