package com.example.fateczl.ClinicaSpringData.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.fateczl.ClinicaSpringData.model.Endereco;
import com.example.fateczl.ClinicaSpringData.model.Especialidade;
import com.example.fateczl.ClinicaSpringData.model.Medico;
import com.example.fateczl.ClinicaSpringData.repository.IEnderecoRepository;
import com.example.fateczl.ClinicaSpringData.repository.IEspecialidadeRepository;
import com.example.fateczl.ClinicaSpringData.repository.IMedicoRepository;

@Controller
public class MedicoController {

    @Autowired
    private IMedicoRepository mRep;

    @Autowired
    private IEnderecoRepository eRep;

    @Autowired
    private IEspecialidadeRepository espRep;

    @RequestMapping(name = "medico", value = "/medico", method = RequestMethod.GET)
    public ModelAndView medicoGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        String idStr = params.get("id");

        Medico m = new Medico();
        String erro = "";
        List<Medico> medicos = new ArrayList<>();
        List<Especialidade> especialidades = new ArrayList<>();

        try {
            if (acao != null && idStr != null) {
                long id = Long.parseLong(idStr);
                if (acao.equalsIgnoreCase("excluir")) {
                    mRep.deleteById(id);
                } else if (acao.equalsIgnoreCase("editar")) {
                    Optional<Medico> opt = mRep.findById(id);
                    m = opt.orElse(new Medico());
                }
            }
            medicos = mRep.findAll();
            especialidades = espRep.findAll();
        } catch (Exception e) {
            erro = e.getMessage();
        } finally {
            model.addAttribute("erro", erro);
            model.addAttribute("medico", m);
            model.addAttribute("medicos", medicos);
            model.addAttribute("especialidades", especialidades);
        }
        return new ModelAndView("medico");
    }

    @RequestMapping(name = "medico", value = "/medico", method = RequestMethod.POST)
    public ModelAndView medicoPost(@RequestParam Map<String, String> params, ModelMap model) {
        String saida = "";
        String erro = "";
        List<Medico> medicos = new ArrayList<>();
        List<Especialidade> especialidades = new ArrayList<>();
        Medico m = new Medico();
        String cmd = params.get("botao");

        try {
            especialidades = espRep.findAll();

            // Endereco
            String cep = params.get("cep");
            String rua = params.get("rua");
            String numero = params.get("numero");
            String complemento = params.get("complemento");
            Endereco e = new Endereco(cep, rua, numero, complemento);

            // Medico
            String id = params.get("id");
            String nome = params.get("nome");
            String contato = params.get("contato");
            String especialidadeId = params.get("especialidade");

            if (!cmd.equalsIgnoreCase("Listar")) {
                if (id != null && !id.isEmpty()) {
                    m.setId(Long.parseLong(id));
                }
            }

            if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
                m.setNome(nome);
                m.setContato(contato);
                m.setEndereco(e);
                
                Optional<Especialidade> optEsp = espRep.findById(Long.parseLong(especialidadeId));
                if (optEsp.isPresent()) {
                    m.setEspecialidade(optEsp.get());
                } else {
                    throw new Exception("Especialidade não encontrada!");
                }
            }

            if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
                eRep.save(e);
                mRep.save(m);
                saida = cmd.equals("Inserir") ? "Médico inserido com sucesso!" : "Médico atualizado com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                mRep.delete(m);
                saida = "Médico excluído com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Buscar")) {
                Optional<Medico> opt = mRep.findById(m.getId());
                m = opt.orElse(new Medico());
            }
            if (cmd.equalsIgnoreCase("Listar")) {
                medicos = mRep.findAll();
            }

        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        } finally {
            if (!cmd.equalsIgnoreCase("Buscar")) {
                m = new Medico();
            }
            if (!cmd.equalsIgnoreCase("Listar")) {
                medicos = null;
            }

            model.addAttribute("erro", erro);
            model.addAttribute("saida", saida);
            model.addAttribute("medico", m);
            model.addAttribute("medicos", medicos);
            model.addAttribute("especialidades", especialidades);
        }

        return new ModelAndView("medico");
    }
}