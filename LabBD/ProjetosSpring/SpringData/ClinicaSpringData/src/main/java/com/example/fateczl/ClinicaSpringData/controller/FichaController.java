package com.example.fateczl.ClinicaSpringData.controller;

import com.example.fateczl.ClinicaSpringData.model.Especialidade;
import com.example.fateczl.ClinicaSpringData.model.FichaInformacoes;
import com.example.fateczl.ClinicaSpringData.model.Paciente;
import com.example.fateczl.ClinicaSpringData.repository.IEspecialidadeRepository;
import com.example.fateczl.ClinicaSpringData.repository.IFichaInformacoesRepository;
import com.example.fateczl.ClinicaSpringData.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
public class FichaController {

    @Autowired
    private IFichaInformacoesRepository fRep;
    @Autowired
    private IPacienteRepository pRep;
    @Autowired
    private IEspecialidadeRepository eRep;

    @RequestMapping(name = "ficha", value = "/ficha", method = RequestMethod.GET)
    public ModelAndView fichaGet(ModelMap model) {
        carregarDados(model);
        model.addAttribute("ficha", new FichaInformacoes());
        return new ModelAndView("ficha");
    }

    @RequestMapping(name = "ficha", value = "/ficha", method = RequestMethod.POST)
    public ModelAndView fichaPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        String id = params.get("id");
        String pacienteId = params.get("paciente");
        String especialidadeId = params.get("especialidade");
        String observacoes = params.get("observacoes");

        String saida = "";
        String erro = "";
        FichaInformacoes f = new FichaInformacoes();

        try {
            if (!cmd.equalsIgnoreCase("Listar")) {
                if (id != null && !id.isEmpty()) {
                    f.setId(Long.parseLong(id));
                }
                
                Optional<Paciente> pOpt = pRep.findById(Long.parseLong(pacienteId));
                Optional<Especialidade> eOpt = eRep.findById(Long.parseLong(especialidadeId));
                
                if (pOpt.isPresent() && eOpt.isPresent()){
                    f.setPaciente(pOpt.get());
                    f.setEspecialidade(eOpt.get());
                    f.setObservacoes(observacoes);
                } else {
                    throw new Exception("Paciente ou Especialidade não encontrados.");
                }
            }

            if (cmd.equalsIgnoreCase("Salvar")) {
                fRep.save(f);
                saida = "Ficha salva com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                fRep.deleteById(f.getId());
                saida = "Ficha excluída com sucesso!";
            }
        } catch (Exception e) {
            erro = "Erro ao processar ficha: " + e.getMessage();
        } finally {
            carregarDados(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            model.addAttribute("ficha", new FichaInformacoes());
        }
        return new ModelAndView("ficha");
    }

    private void carregarDados(ModelMap model) {
        model.addAttribute("pacientes", pRep.findAll());
        model.addAttribute("especialidades", eRep.findAll());
        model.addAttribute("fichas", fRep.findAll());
    }
}