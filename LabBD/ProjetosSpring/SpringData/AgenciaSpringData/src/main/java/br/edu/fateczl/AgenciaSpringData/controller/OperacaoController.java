package br.edu.fateczl.AgenciaSpringData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.AgenciaSpringData.persistence.ContaBancariaRepository;

import java.util.Map;
import java.util.Optional;

@Controller
public class OperacaoController {

    @Autowired
    private ContaBancariaRepository cRep;

    @RequestMapping(name = "operacao", value = "/operacao", method = RequestMethod.GET)
    public ModelAndView operacaoGet(ModelMap model) {
        // Carrega todas as contas (para popular dropdowns)
        carregarContas(model);
        return new ModelAndView("operacao");
    }

    @RequestMapping(name = "operacao", value = "/operacao", method = RequestMethod.POST)
    public ModelAndView operacaoPost(@RequestParam Map<String, String> params, ModelMap model) {
        String cmd = params.get("botao"); // Botões: "Sacar", "Depositar", "Calcular Rendimento", "Consultar Saldo"
        
        String saida = "";
        String erro = "";

        try {
            // Ações que exigem número da conta e valor
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
            // Ação de Calcular Rendimento (para todas as poupanças)
            else if (cmd.equalsIgnoreCase("Calcular Rendimento")) {
                float taxa = Float.parseFloat(params.get("taxaRendimento"));
                cRep.calcularRendimento(taxa);
                saida = "Rendimento de " + (taxa * 100) + "% aplicado a todas as Contas Poupança.";
            }
            // Ação de Consultar Saldo (UDF)
            else if (cmd.equalsIgnoreCase("Consultar Saldo Disponível")) {
                int numConta = Integer.parseInt(params.get("numConta"));
                Optional<ContaBancariaRepository.SaldoDisponivelDTO> dtoOpt = 
                    cRep.getSaldoDisponivelCliente(numConta);
                
                if (dtoOpt.isPresent()) {
                    model.addAttribute("dadosCliente", dtoOpt.get()); // Envia o DTO para a JSP
                    saida = "Consulta de saldo realizada.";
                } else {
                    throw new Exception("Conta não encontrada para consulta.");
                }
            }

        } catch (Exception e) {
            // PONTO CRÍTICO: Captura o erro da Stored Procedure
            // e.getCause().getMessage() pega o "RAISERROR" do SQL Server
            if (e.getCause() != null) {
                erro = "Falha na operação: " + e.getCause().getMessage();
            } else {
                erro = "Falha na operação: " + e.getMessage();
            }
        } finally {
            // Recarrega as contas (para dropdowns)
            carregarContas(model);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
        }

        return new ModelAndView("operacao");
    }
    
    // Método auxiliar para carregar todas as contas
    private void carregarContas(ModelMap model) {
        model.addAttribute("contas", cRep.findAll());
    }
}