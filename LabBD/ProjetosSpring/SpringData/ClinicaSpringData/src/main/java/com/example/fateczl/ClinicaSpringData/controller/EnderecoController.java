package com.example.fateczl.ClinicaSpringData.controller;

import com.example.fateczl.ClinicaSpringData.model.Endereco;
import com.example.fateczl.ClinicaSpringData.repository.IEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EnderecoController {

    @Autowired
    private IEnderecoRepository eRep;

    @RequestMapping(name = "endereco", value = "/endereco", method = RequestMethod.GET)
    public ModelAndView enderecoGet(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("cmd");
        String cep = params.get("cep");

        Endereco e = new Endereco();
        String erro = "";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            if (cmd != null && cep != null && !cep.isEmpty()) {
                if (cmd.equalsIgnoreCase("excluir")) {
                    eRep.deleteById(cep);
                } else if (cmd.equalsIgnoreCase("editar")) {
                    Optional<Endereco> opt = eRep.findById(cep);
                    e = opt.orElse(new Endereco());
                }
            }
            enderecos = eRep.findAll();
        } catch (Exception ex) {
            erro = "Erro ao carregar dados: " + ex.getMessage();
        } finally {
            model.addAttribute("erro", erro);
            model.addAttribute("endereco", e);
            model.addAttribute("enderecos", enderecos);
        }
        return new ModelAndView("endereco");
    }

    @RequestMapping(name = "endereco", value = "/endereco", method = RequestMethod.POST)
    public ModelAndView enderecoPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        String cep = params.get("cep");
        String rua = params.get("rua");
        String numero = params.get("numero");
        String complemento = params.get("complemento");

        String saida = "";
        String erro = "";
        Endereco e = new Endereco();
        List<Endereco> enderecos = new ArrayList<>();

        try {
            if (!cmd.equalsIgnoreCase("Listar")) {
                e.setCep(cep);
                e.setRua(rua);
                e.setNumero(numero);
                e.setComplemento(complemento);
            }

            if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
                eRep.save(e);
                saida = "Endereço " + (cmd.equalsIgnoreCase("Inserir") ? "inserido" : "atualizado") + " com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                eRep.delete(e);
                saida = "Endereço excluído com sucesso!";
            }
             if (cmd.equalsIgnoreCase("Buscar")) {
                Optional<Endereco> opt = eRep.findById(e.getCep());
                e = opt.orElse(new Endereco());
            }
            if (cmd.equalsIgnoreCase("Listar")) {
                enderecos = eRep.findAll();
            }
        } catch (Exception ex) {
            erro = "Erro ao executar operação: " + ex.getMessage();
        } finally {
            if (!cmd.equalsIgnoreCase("Buscar")) {
                e = new Endereco();
            }
            if (!cmd.equalsIgnoreCase("Listar")) {
                enderecos = null;
            }
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            model.addAttribute("endereco", e);
            model.addAttribute("enderecos", enderecos);
        }
        return new ModelAndView("endereco");
    }
}