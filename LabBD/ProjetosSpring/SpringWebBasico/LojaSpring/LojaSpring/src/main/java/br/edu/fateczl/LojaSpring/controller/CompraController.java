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
import br.edu.fateczl.LojaSpring.persistence.CompraDao;

@Controller
public class CompraController {

	@Autowired
	private CompraDao cDao;

	@RequestMapping(name = "compra", value = "/compra", method = RequestMethod.GET)
	public ModelAndView compraGet(
			@RequestParam Map<String, String> 
			params, 
			ModelMap model) {
			String erro = "";
			List<Compra> compras = new ArrayList<>();

				try {
					compras = cDao.listar();
				} catch (SQLException | ClassNotFoundException e) {
					erro = e.getMessage();
				} finally {
					model.addAttribute("erro", erro);
					model.addAttribute("compras", compras);
				}
			return new ModelAndView("compra");
	}

}