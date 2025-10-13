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
import com.example.fateczl.ClinicaSpringData.model.Paciente;
import com.example.fateczl.ClinicaSpringData.repository.IEnderecoRepository;
import com.example.fateczl.ClinicaSpringData.repository.IPacienteRepository;

@Controller
public class PacienteController {

    @Autowired
    private IPacienteRepository pRep;

    @Autowired
    private IEnderecoRepository eRep;

    @RequestMapping(name = "paciente", value = "/paciente", method = RequestMethod.GET)
    public ModelAndView pacienteGet(@RequestParam Map<String, String> params, ModelMap model) {
        String acao = params.get("acao");
        String idStr = params.get("id");

        Paciente p = new Paciente();
        String erro = "";
        List<Paciente> pacientes = new ArrayList<>();

        try {
            if (acao != null && idStr != null) {
                long id = Long.parseLong(idStr);
                if (acao.equalsIgnoreCase("excluir")) {
                    pRep.deleteById(id);
                } else if (acao.equalsIgnoreCase("editar")) {
                    Optional<Paciente> opt = pRep.findById(id);
                    p = opt.orElse(new Paciente());
                }
            }
            pacientes = pRep.findAll();
        } catch (Exception e) {
            erro = e.getMessage();
        } finally {
            model.addAttribute("erro", erro);
            model.addAttribute("paciente", p);
            model.addAttribute("pacientes", pacientes);
        }
        return new ModelAndView("paciente");
    }

    @RequestMapping(name = "paciente", value = "/paciente", method = RequestMethod.POST)
    public ModelAndView pacientePost(@RequestParam Map<String, String> params, ModelMap model) {
        String saida = "";
        String erro = "";
        List<Paciente> pacientes = new ArrayList<>();
        Paciente p = new Paciente();
        String cmd = params.get("botao");

        try {
            // Endereco
            String cep = params.get("cep");
            String rua = params.get("rua");
            String numero = params.get("numero");
            String complemento = params.get("complemento");
            
            Endereco e = new Endereco(cep, rua, numero, complemento);
            
            // Paciente
            String id = params.get("id");
            String nome = params.get("nome");
            String telefone = params.get("telefone");
            String numeroBeneficiario = params.get("numeroBeneficiario");

            if (!cmd.equalsIgnoreCase("Listar")) {
                if (id != null && !id.isEmpty()) {
                    p.setId(Long.parseLong(id));
                }
            }
            
            if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
                p.setNome(nome);
                p.setTelefone(telefone);
                p.setNumeroBeneficiario(numeroBeneficiario);
                p.setEndereco(e);
            }

            if (cmd.equalsIgnoreCase("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
                eRep.save(e); // Salva ou atualiza o endereço primeiro
                pRep.save(p);
                saida = cmd.equals("Inserir") ? "Paciente inserido com sucesso!" : "Paciente atualizado com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                pRep.delete(p);
                saida = "Paciente excluído com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Buscar")) {
                Optional<Paciente> opt = pRep.findById(p.getId());
                p = opt.orElse(new Paciente());
            }
            if (cmd.equalsIgnoreCase("Listar")) {
                pacientes = pRep.findAll();
            }

        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        } finally {
            if (!cmd.equalsIgnoreCase("Buscar")) {
                p = new Paciente();
            }
            if (!cmd.equalsIgnoreCase("Listar")) {
                pacientes = null;
            }
            
            model.addAttribute("erro", erro);
            model.addAttribute("saida", saida);
            model.addAttribute("paciente", p);
            model.addAttribute("pacientes", pacientes);
        }

        return new ModelAndView("paciente");
    }
}