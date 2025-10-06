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

import br.edu.fateczl.LojaSpring.model.Compra;
import br.edu.fateczl.LojaSpring.model.Produto;
import br.edu.fateczl.LojaSpring.persistence.CompraDao;
import br.edu.fateczl.LojaSpring.persistence.ProdutoDao;

@Controller
public class CompraController {

	@Autowired
	private CompraDao cDao;

	@RequestMapping(name = "compra", value = "/compra", method = RequestMethod.GET)
	public ModelAndView compraGet(
			@RequestParam Map<String, String> 
			params, 
			ModelMap model) {
			String acao = params.get("acao");
			String codigo = params.get("codigo");

			Compra c = new Compra();
			String erro = "";
			List<Compra> compras = new ArrayList<>();

				try {
					if (acao != null) {
						c.setCodigo(Long.parseLong(codigo));

						if (acao.equalsIgnoreCase("excluir")) {
							cDao.excluir(c);
							compras = cDao.listar();
							c = null;
						} else {
							c = cDao.buscar(c);
							compras = null;
						}
					}
				} catch (SQLException | ClassNotFoundException e) {
					erro = e.getMessage();
				} finally {
					model.addAttribute("erro", erro);
					model.addAttribute("compra", c);
					model.addAttribute("compras", compras);
				}
			return new ModelAndView("compra");
	}

	@RequestMapping(name = "compra", value = "/compra", method = RequestMethod.POST)
	public ModelAndView compraPost(
			@RequestParam Map<String, String> params, 
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Compra> compras = new ArrayList<Compra>();
		Compra c = new Compra();
		String cmd = "";

		try {
			String codigo = params.get("codigo");
			String codigoCliente = params.get("codigo_cliente");
			String codigoProduto = params.get("codigo_produto");
			String quantidade = params.get("quantidade");
			cmd = params.get("botao");

			if (!cmd.equalsIgnoreCase("Listar")) {
				c.setCodigo(Long.parseLong(codigo));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				c.setCodigoCliente(Long.parseLong(codigoCliente));
				c.setCodigoProduto(Long.parseLong(codigoProduto));
				c.setQuantidade(Integer.parseInt(quantidade));

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
				compras = cDao.listar();
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
				compras = null;
			}
			
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("compra", c);
			model.addAttribute("compras", compras);
			
		}

		return new ModelAndView("compra");
	}
}