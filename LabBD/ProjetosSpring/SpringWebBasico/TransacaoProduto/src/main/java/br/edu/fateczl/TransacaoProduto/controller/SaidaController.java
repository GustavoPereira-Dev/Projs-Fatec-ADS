package br.edu.fateczl.TransacaoProduto.controller;

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

import br.edu.fateczl.TransacaoProduto.model.Transacao;
import br.edu.fateczl.TransacaoProduto.persistence.SaidaDao;

@Controller
public class SaidaController {

	@Autowired
	private SaidaDao sDao;

	@RequestMapping(name = "saida", value = "/saida", method = RequestMethod.GET)
	public ModelAndView clienteGet(
			@RequestParam Map<String, String> 
			params, 
			ModelMap model) {
			String acao = params.get("acao");
			String codigo = params.get("codigo");

			Transacao t = new Transacao();
			String erro = "";
			List<Transacao> transacoes = new ArrayList<>();

				try {
					if (acao != null) {
						t.setCodigo(Long.parseLong(codigo));

						if (acao.equalsIgnoreCase("excluir")) {
							sDao.excluir(t);
							transacoes = sDao.listar();
							t = null;
						} else {
							t = sDao.buscar(t);
							transacoes = null;
						}
					}
				} catch (SQLException | ClassNotFoundException e) {
					erro = e.getMessage();
				} finally {
					model.addAttribute("erro", erro);
					model.addAttribute("transacao", t);
					model.addAttribute("transacoes", transacoes);
				}
			return new ModelAndView("saida");
	}

	@RequestMapping(name = "saida", value = "/saida", method = RequestMethod.POST)
	public ModelAndView clientePost(
			@RequestParam Map<String, String> params, 
			ModelMap model) {
		String saida = "";
		String erro = "";
		List<Transacao> transacoes = new ArrayList<Transacao>();
		Transacao t = new Transacao();
		String cmd = "";

		try {
			String codigo = params.get("codigo");
			String codigoProduto = params.get("codigo_produto");
			String quantidade = params.get("quantidade");
			String valorTotal = params.get("valor_total");
			cmd = params.get("botao");
			
			if (!cmd.equalsIgnoreCase("Listar")) {
				t.setCodigo(Long.parseLong(codigo));
			}
			if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
				t.setCodigoProduto(Long.parseLong(codigoProduto));
				t.setQuantidade(Integer.parseInt(quantidade));
				t.setValorTotal(Float.parseFloat(valorTotal));
			}

			if (cmd.equalsIgnoreCase("Inserir")) {
				saida = sDao.inserir(t);
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				saida = sDao.atualizar(t);
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				saida = sDao.excluir(t);
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				t = sDao.buscar(t);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				transacoes = sDao.listar();
			}

		} catch (SQLException | ClassNotFoundException | NumberFormatException e) {
			saida = "";
			erro = e.getMessage();
			if (erro.contains("input string")) {
				erro = "Preencha os campos corretamente";
			}
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				t = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				transacoes = null;
			}
			
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("transacao", t);
			model.addAttribute("transacoes", transacoes);
			
		}

		return new ModelAndView("saida");
	}
}