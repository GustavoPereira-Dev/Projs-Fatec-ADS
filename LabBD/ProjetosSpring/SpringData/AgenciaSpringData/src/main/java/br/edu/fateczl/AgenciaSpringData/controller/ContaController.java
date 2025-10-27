package br.edu.fateczl.AgenciaSpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.AgenciaSpringData.model.ContaEspecial;
import br.edu.fateczl.AgenciaSpringData.model.ContaPoupanca;
import br.edu.fateczl.AgenciaSpringData.model.ContaBancaria;
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
        ContaBancaria contaFormPoupanca = new ContaPoupanca();
        ContaBancaria contaFormEspecial = new ContaEspecial();

        try {

            if (cmd.contains("Poupanca")) {
                ContaPoupanca cp = preencherPoupanca(params); 

                if (cmd.equalsIgnoreCase("Inserir Poupanca")) {
                    if (cRep.existsById(cp.getNumConta())) {
                        throw new Exception("Conta com este número já existe. Use 'Atualizar'.");
                    }
                    cRep.save(cp);
                    saida = "Conta Poupança inserida com sucesso!";
                }
                
                if (cmd.equalsIgnoreCase("Atualizar Poupanca")) {
                    if (!cRep.existsById(cp.getNumConta())) {
                        throw new Exception("Conta não encontrada. Use 'Inserir'.");
                    }
                    cRep.save(cp);
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

            else if (cmd.contains("Especial")) {
                ContaEspecial ce = preencherEspecial(params);

                if (cmd.equalsIgnoreCase("Inserir Especial")) {
                    if (cRep.existsById(ce.getNumConta())) {
                        throw new Exception("Conta com este número já existe. Use 'Atualizar'.");
                    }
                    cRep.save(ce);
                    saida = "Conta Especial inserida com sucesso!";
                }

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
        } catch (NumberFormatException e) {
            erro = "Verifique se os campos numéricos (Nº Conta, Saldo, Limite, Dia) foram preenchidos corretamente.";
        } catch (DataIntegrityViolationException e) {
            String causeMessage = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

            if (causeMessage.contains("PRIMARY KEY") || causeMessage.contains("PK_ContaBancaria")) {
                erro = "O Número da Conta informado já existe no sistema.";
            } else if (causeMessage.contains("FOREIGN KEY") || causeMessage.contains("REFERENCE constraint")) {
                erro = "Esta conta não pode ser excluída pois possui dados (como movimentações) associados a ela.";
            } else if (causeMessage.contains("NOT NULL") || causeMessage.contains("NULL")) {
                erro = "Todos os campos obrigatórios (como Nome do Cliente) devem ser preenchidos.";
            } else {
                erro = "Os dados informados violam uma regra do banco de dados.";
            }

        } catch (Exception e) {
            erro = e.getMessage();
        
        } finally {
            carregarContas(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
            model.addAttribute("poupanca", contaFormPoupanca);
            model.addAttribute("especial", contaFormEspecial);
        }

        return new ModelAndView("contas");
    }
    
    private ContaPoupanca preencherPoupanca(Map<String, String> params) throws NumberFormatException {
        ContaPoupanca cp = new ContaPoupanca();
        cp.setNumConta(Integer.parseInt(params.get("numContaPoupanca"))); 
        cp.setNomeCliente(params.get("nomeClientePoupanca"));
        String saldo = params.get("saldoPoupanca");
        String dia = params.get("diaRendimento");
		cp.setSaldo((saldo != null && !saldo.isEmpty()) ? Float.parseFloat(saldo) : 0f);
        cp.setDiaDeRendimento((dia != null && !dia.isEmpty()) ? Integer.parseInt(dia) : 0);
        return cp;
    }
    
    private ContaEspecial preencherEspecial(Map<String, String> params) throws NumberFormatException {
        ContaEspecial ce = new ContaEspecial();
        ce.setNumConta(Integer.parseInt(params.get("numContaEspecial")));
        ce.setNomeCliente(params.get("nomeClienteEspecial"));
        String saldo = params.get("saldoEspecial");
        String limite = params.get("limiteEspecial");
        ce.setSaldo((saldo != null && !saldo.isEmpty()) ? Float.parseFloat(saldo) : 0);
        ce.setLimite((limite != null && !limite.isEmpty()) ? Float.parseFloat(limite) : 0f);
        return ce;
    }
    
    private void carregarContas(ModelMap model) {
        model.addAttribute("contasPoupanca", cRep.findAllPoupanca());
        model.addAttribute("contasEspecial", cRep.findAllEspecial());
    }
}