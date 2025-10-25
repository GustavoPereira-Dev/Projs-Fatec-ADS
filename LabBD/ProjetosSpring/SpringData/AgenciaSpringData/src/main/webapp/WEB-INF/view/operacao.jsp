<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Operações Bancárias</title>
</head>
<body>
<div class="container">
    <div class="mt-4">
        <a href="./" class="btn btn-outline-primary">← Voltar ao Menu</a>
    </div>

    <div class="text-center mt-4">
        <h1>Operações Bancárias</h1>
    </div>

    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>
    
    <div class="row justify-content-center mt-4">
        <div class="col-lg-10 col-md-12">
            
            <form action="operacao" method="POST" class="p-4 border rounded shadow-sm">
                <h2 class="text-center mb-4">Saque e Depósito</h2>
                <div class="row">
                    <div class="col-md-7">
                        <label for="contas" class="form-label">Escolha a Conta</label>
                        <select id="contas" name="numConta" class="form-select">
                            <option value="">-- Selecione uma conta --</option>
                            <c:forEach var="conta" items="${contas}">
                                <option value="${conta.numConta}">
                                    ${conta.nomeCliente} (Nº: ${conta.numConta})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label for="valor" class="form-label">Valor (R$)</label>
                        <input type="text" id="valor" name="valor" class="form-control" placeholder="0.00">
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 mt-4">
                    <input type="submit" name="botao" value="Sacar" class="btn btn-danger" style="width: 120px;">
                    <input type="submit" name="botao" value="Depositar" class="btn btn-success" style="width: 120px;">
                </div>
            </form>

            <form action="operacao" method="POST" class="mt-4 p-4 border rounded shadow-sm">
                <h2 class="text-center mb-4">Rendimento Poupança</h2>
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <label for="taxa" class="form-label">Taxa (ex: 0.005 para 0.5%)</label>
                        <input type="text" id="taxa" name="taxaRendimento" class="form-control" placeholder="0.005">
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 mt-4">
                    <input type="submit" name="botao" value="Calcular Rendimento" class="btn btn-primary">
                </div>
            </form>
            
            <form action="operacao" method="POST" class="mt-4 p-4 border rounded shadow-sm">
                <h2 class="text-center mb-4">Consultar Saldo Disponível</h2>
                <div class="row justify-content-center">
                     <div class="col-md-7">
                        <label for="contasConsulta" class="form-label">Escolha a Conta</label>
                         <select id="contasConsulta" name="numConta" class="form-select">
                             <option value="">-- Selecione uma conta --</option>
                             <c:forEach var="conta" items="${contas}">
                                 <option value="${conta.numConta}">
                                     ${conta.nomeCliente} (Nº: ${conta.numConta})
                                 </option>
                             </c:forEach>
                         </select>
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 mt-4">
                    <input type="submit" name="botao" value="Consultar Saldo Disponível" class="btn btn-info">
                </div>
            </form>

            <c:if test="${not empty dadosCliente}">
                <div class="mt-4 p-4 border rounded shadow-sm bg-light">
                    <h3 class="text-center">Resultado da Consulta</h3>
                    <div class="row mt-3 fs-5">
                        <div class="col-md-6">
                            <p><strong>Cliente:</strong> ${dadosCliente.nomeCliente}</p>
                            <p><strong>Conta:</strong> ${dadosCliente.numConta}</p>
                        </div>
                        <div class="col-md-6">
                            <p><strong>Saldo Atual:</strong> R$ ${dadosCliente.saldoAtual}</p>
                            <p><strong>Saldo Disponível:</strong> R$ ${dadosCliente.saldoDisponível}</p>
                        </div>
                    </div>
                </div>
            </c:if>
            
        </div>
    </div>
    <div style="height: 50px;"></div> 
</div>
</body>
</html>