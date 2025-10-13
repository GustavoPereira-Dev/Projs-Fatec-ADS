package com.example.fateczl.ClinicaSpringData.controller;

import com.example.fateczl.ClinicaSpringData.model.Consulta;
import com.example.fateczl.ClinicaSpringData.model.Medico;
import com.example.fateczl.ClinicaSpringData.model.Paciente;
import com.example.fateczl.ClinicaSpringData.repository.IConsultaRepository;
import com.example.fateczl.ClinicaSpringData.repository.IMedicoRepository;
import com.example.fateczl.ClinicaSpringData.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Controller
public class ConsultaController {

    @Autowired
    private IConsultaRepository cRep;
    @Autowired
    private IPacienteRepository pRep;
    @Autowired
    private IMedicoRepository mRep;

    @RequestMapping(name = "consulta", value = "/consulta", method = RequestMethod.GET)
    public ModelAndView consultaGet(ModelMap model) {
        carregarDados(model);
        model.addAttribute("consulta", new Consulta());
        return new ModelAndView("consulta");
    }

    @RequestMapping(name = "consulta", value = "/consulta", method = RequestMethod.POST)
    public ModelAndView consultaPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        String id = params.get("id");
        String pacienteId = params.get("paciente");
        String medicoId = params.get("medico");
        String dataHora = params.get("dataHora");

        String saida = "";
        String erro = "";
        Consulta c = new Consulta();

        try {
            if (!cmd.equalsIgnoreCase("Listar")) {
                if (id != null && !id.isEmpty()) {
                    c.setId(Long.parseLong(id));
                }
                
                Optional<Paciente> pOpt = pRep.findById(Long.parseLong(pacienteId));
                Optional<Medico> mOpt = mRep.findById(Long.parseLong(medicoId));
                
                if (pOpt.isPresent() && mOpt.isPresent()){
                    c.setPaciente(pOpt.get());
                    c.setMedico(mOpt.get());
                    c.setDataHora(LocalDateTime.parse(dataHora));
                } else {
                    throw new Exception("Paciente ou Médico não encontrado.");
                }
            }

            if (cmd.equalsIgnoreCase("Agendar")) {
                cRep.save(c);
                saida = "Consulta agendada com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Excluir")) {
                cRep.deleteById(c.getId());
                saida = "Consulta desmarcada com sucesso!";
            }
            if (cmd.equalsIgnoreCase("Listar")) {
                // A listagem é feita no 'carregarDados'
            }
        } catch (Exception e) {
            erro = "Erro ao processar consulta: " + e.getMessage();
        } finally {
            carregarDados(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            model.addAttribute("consulta", new Consulta());
        }
        return new ModelAndView("consulta");
    }

    private void carregarDados(ModelMap model) {
        model.addAttribute("pacientes", pRep.findAll());
        model.addAttribute("medicos", mRep.findAll());
        model.addAttribute("consultas", cRep.findAll());
    }
}