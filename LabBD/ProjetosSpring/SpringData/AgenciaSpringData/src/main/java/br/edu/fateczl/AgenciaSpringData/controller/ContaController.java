package br.edu.fateczl.AgenciaSpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.AgenciaSpringData.model.ContaEspecial;
import br.edu.fateczl.AgenciaSpringData.model.ContaPoupanca;
import br.edu.fateczl.AgenciaSpringData.persistence.ContaBancariaRepository;

import java.util.Map;
import java.util.Optional;

@Controller
public class ContaController {

    @Autowired
    private ContaBancariaRepository cRep;

    @RequestMapping(name = "contas", value = "/contas", method = RequestMethod.GET)
    public ModelAndView contasGet(ModelMap model) {
        carregarContas(model);
        model.addAttribute("poupanca", new ContaPoupanca());
        model.addAttribute("especial", new ContaEspecial());
        return new ModelAndView("contas");
    }

    @RequestMapping(name = "contas", value = "/contas", method = RequestMethod.POST)
    public ModelAndView contasPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao");
        String saida = "";
        String erro = "";
        ContaPoupanca contaFormPoupanca = new ContaPoupanca();
        ContaEspecial contaFormEspecial = new ContaEspecial();

        try {
            // Se o botão for de Conta Poupança
            if (cmd.contains("Poupanca")) {
                ContaPoupanca cp = preencherPoupanca(params);

                if (cmd.equalsIgnoreCase("Inserir Poupanca")) {
                    // Verifica se a conta já existe
                    if (cRep.existsById(cp.getNumConta())) {
                        throw new Exception("Conta com este número já existe. Use 'Atualizar'.");
                    }
                    cRep.save(cp);
                    saida = "Conta Poupança inserida com sucesso!";
                }
                
                // *** AÇÃO DE ATUALIZAR (UPDATE) ADICIONADA AQUI ***
                if (cmd.equalsIgnoreCase("Atualizar Poupanca")) {
                    // Verifica se a conta realmente existe antes de atualizar
                    if (!cRep.existsById(cp.getNumConta())) {
                        throw new Exception("Conta não encontrada. Use 'Inserir'.");
                    }
                    cRep.save(cp); // O save() faz o UPDATE aqui
                    saida = "Conta Poupança atualizada com sucesso!";
                }
                
                if (cmd.equalsIgnoreCase("Excluir Poupanca")) {
                    cRep.deleteById(cp.getNumConta());
                    saida = "Conta Poupança excluída com sucesso!";
                }
                if (cmd.equalsIgnoreCase("Buscar Poupanca")) {
                    Optional<ContaPoupanca> cpOpt = cRep.findPoupancaById(cp.getNumConta());
                    if (cpOpt.isPresent()) {
                        contaFormPoupanca = cpOpt.get();
                    } else {
                        throw new Exception("Conta Poupança não encontrada.");
                    }
                }
            } 
            // Se o botão for de Conta Especial
            else if (cmd.contains("Especial")) {
                ContaEspecial ce = preencherEspecial(params);

                if (cmd.equalsIgnoreCase("Inserir Especial")) {
                    if (cRep.existsById(ce.getNumConta())) {
                        throw new Exception("Conta com este número já existe. Use 'Atualizar'.");
                    }
                    cRep.save(ce);
                    saida = "Conta Especial inserida com sucesso!";
                }

                // *** AÇÃO DE ATUALIZAR (UPDATE) ADICIONADA AQUI ***
                if (cmd.equalsIgnoreCase("Atualizar Especial")) {
                    if (!cRep.existsById(ce.getNumConta())) {
                        throw new Exception("Conta não encontrada. Use 'Inserir'.");
                    }
                    cRep.save(ce);
                    saida = "Conta Especial atualizada com sucesso!";
                }
                
                if (cmd.equalsIgnoreCase("Excluir Especial")) {
                    cRep.deleteById(ce.getNumConta());
                    saida = "Conta Especial excluída com sucesso!";
                }
                if (cmd.equalsIgnoreCase("Buscar Especial")) {
                    Optional<ContaEspecial> ceOpt = cRep.findEspecialById(ce.getNumConta());
                    if (ceOpt.isPresent()) {
                        contaFormEspecial = ceOpt.get();
                    } else {
                        throw new Exception("Conta Especial não encontrada.");
                    }
                }
            }

        } catch (Exception e) {
            erro = "Erro ao processar: " + e.getMessage();
        } finally {
            carregarContas(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            // Popula os formulários (ou com dados da busca, ou vazios)
            model.addAttribute("poupanca", contaFormPoupanca);
            model.addAttribute("especial", contaFormEspecial);
        }

        return new ModelAndView("contas");
    }

    // Método auxiliar para carregar as listas
    private void carregarContas(ModelMap model) {
        model.addAttribute("contasPoupanca", cRep.findAllPoupanca());
        model.addAttribute("contasEspecial", cRep.findAllEspecial());
    }

    // Métodos auxiliares de preenchimento (parse dos formulários)
    private ContaPoupanca preencherPoupanca(Map<String, String> params) throws Exception {
        ContaPoupanca cp = new ContaPoupanca();
        cp.setNumConta(Integer.parseInt(params.get("numContaPoupanca"))); 
        cp.setNomeCliente(params.get("nomeClientePoupanca"));
        
        String saldo = params.get("saldoPoupanca");
        String dia = params.get("diaRendimento");
        
        cp.setSaldo((saldo != null && !saldo.isEmpty()) ? Float.parseFloat(saldo) : 0f);
        cp.setDiaDeRendimento((dia != null && !dia.isEmpty()) ? Integer.parseInt(dia) : 0);
        return cp;
    }
    
    private ContaEspecial preencherEspecial(Map<String, String> params) throws Exception {
        ContaEspecial ce = new ContaEspecial();
        ce.setNumConta(Integer.parseInt(params.get("numContaEspecial")));
        ce.setNomeCliente(params.get("nomeClienteEspecial"));

        String saldo = params.get("saldoEspecial");
        String limite = params.get("limiteEspecial");

        ce.setSaldo((saldo != null && !saldo.isEmpty()) ? Float.parseFloat(saldo) : 0f);
        ce.setLimite((limite != null && !limite.isEmpty()) ? Float.parseFloat(limite) : 0f);
        return ce;
    }
}