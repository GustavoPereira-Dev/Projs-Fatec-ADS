package br.edu.fateczl.AgenciaSpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.AgenciaSpringData.persistence.ContaBancariaRepository;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Controller
public class OperacaoController {

    @Autowired
    private ContaBancariaRepository cRep;

    @RequestMapping(name = "operacao", value = "/operacao", method = RequestMethod.GET)
    public ModelAndView operacaoGet(ModelMap model) {
        carregarContas(model);
        return new ModelAndView("operacao");
    }

    @RequestMapping(name = "operacao", value = "/operacao", method = RequestMethod.POST)
    public ModelAndView operacaoPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao"); 
        String saida = "";
        String erro = "";

        try { 
            if (cmd.equalsIgnoreCase("Sacar") || cmd.equalsIgnoreCase("Depositar")) {
                int numConta = Integer.parseInt(params.get("numConta"));
                float valor = Float.parseFloat(params.get("valor"));

                if (cmd.equalsIgnoreCase("Sacar")) {
                    cRep.sacar(numConta, valor);
                    saida = "Saque de R$ " + valor + " realizado com sucesso!";
                }
                if (cmd.equalsIgnoreCase("Depositar")) {
                    cRep.depositar(numConta, valor);
                    saida = "Depósito de R$ " + valor + " realizado com sucesso!";
                }
            }

            else if (cmd.equalsIgnoreCase("Calcular Rendimento")) {
                float taxa = Float.parseFloat(params.get("taxaRendimento"));
                cRep.calcularRendimento(taxa);
                saida = "Rendimento de " + (taxa * 100) + "% aplicado a todas as Contas Poupança.";
            }

            else if (cmd.equalsIgnoreCase("Consultar Saldo Disponível")) {
                int numConta = Integer.parseInt(params.get("numConta"));
                Optional<ContaBancariaRepository.SaldoDisponivelDTO> dtoOpt = 
                    cRep.getSaldoDisponivelCliente(numConta);
                
                if (dtoOpt.isPresent()) {
                    model.addAttribute("dadosCliente", dtoOpt.get()); 
                    saida = "Consulta de saldo realizada.";
                } else {
                    throw new Exception("Conta não encontrada para consulta.");
                }
            }

        } catch (NumberFormatException e) {
            erro = "Os campos devem ser valores válidos.";
        
        } catch (Exception e) {
            String mensagem = null;
            Throwable cause = e.getCause();

            while (cause != null) {
                if (cause instanceof SQLException) {
                	mensagem = cause.getMessage();
                    break; 
                }
                cause = cause.getCause();
            }

            if (mensagem != null) {
                erro = mensagem;
            } else {
                erro = e.getMessage();
            }
        } finally {
            carregarContas(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
        }

        return new ModelAndView("operacao");
    }
    
    private void carregarContas(ModelMap model) {
        model.addAttribute("contas", cRep.findAll());
    }
}