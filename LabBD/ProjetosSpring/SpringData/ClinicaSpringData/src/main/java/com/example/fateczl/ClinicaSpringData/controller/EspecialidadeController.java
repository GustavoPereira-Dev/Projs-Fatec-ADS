package com.example.fateczl.ClinicaSpringData.controller;

import com.example.fateczl.ClinicaSpringData.model.Especialidade;
import com.example.fateczl.ClinicaSpringData.repository.IEspecialidadeRepository;
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
public class EspecialidadeController {

    @Autowired
    private IEspecialidadeRepository espRep;

    @RequestMapping(name = "especialidade", value = "/especialidade", method = RequestMethod.GET)
    public ModelAndView especialidadeGet(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("cmd");
        String idStr = params.get("id");

        Especialidade e = new Especialidade();
        String erro = "";
        List<Especialidade> especialidades = new ArrayList<>();

        try {
            if (cmd != null && idStr != null) {
                long id = Long.parseLong(idStr);
                if (cmd.equalsIgnoreCase("excluir")) {
                    espRep.deleteById(id);
                } else if (cmd.equalsIgnoreCase("editar")) {
                    Optional<Especialidade> opt = espRep.findById(id);
                    e = opt.orElse(new Especialidade());
                }
            }
            especialidades = espRep.findAll();
        } catch (Exception ex) {
            erro = "Erro ao carregar dados: " + ex.getMessage();
        } finally {
            model.addAttribute("erro", erro);
            model.addAttribute("especialidade", e);
            model.addAttribute("especialidades", especialidades);
        }
        return new ModelAndView("especialidade");
    }

    @RequestMapping(name = "especialidade", value = "/especialidade", method = RequestMethod.POST)
    public ModelAndView especialidadePost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        String id = params.get("id");
        String nome = params.get("nome");

        String saida = "";
        String erro = "";
        Especialidade e = new Especialidade();
        List<Especialidade> especialidades = new ArrayList<>();

        try {
            if (!cmd.equalsIgnoreCase("Listar")) {
                 if (id != null && !id.isEmpty()) {
                    e.setId(Long.parseLong(id));
                }
                e.setNome(nome);
            }

            if (cmd.equalsIgnoreCase("Inserir")) {
                espRep.save(e);
                saida = "Especialidade inserida com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Atualizar")) {
                espRep.save(e);
                saida = "Especialidade atualizada com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                espRep.delete(e);
                saida = "Especialidade excluída com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Listar")) {
                especialidades = espRep.findAll();
            }
        } catch (Exception ex) {
            erro = "Erro ao executar operação: " + ex.getMessage();
        } finally {
            if (!cmd.equalsIgnoreCase("Listar")) {
                especialidades = null;
            }
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            model.addAttribute("especialidade", new Especialidade());
            model.addAttribute("especialidades", especialidades);
        }
        return new ModelAndView("especialidade");
    }
}