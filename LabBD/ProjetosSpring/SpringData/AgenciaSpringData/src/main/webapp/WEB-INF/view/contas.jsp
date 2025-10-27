<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Gerenciamento de Contas</title>
</head>
<body>
<div class="container">
    <div class="mt-4">
        <a href="./" class="btn btn-outline-primary">← Voltar ao Menu</a>
    </div>
    
    <div class="text-center mt-4">
        <h1>Gerenciamento de Contas</h1>
    </div>

    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>

    <div class="row mt-4 g-5">
        <div class="col-md-6">
            <form action="contas" method="POST" class="p-4 border rounded shadow-sm">
                <h2 class="text-center mb-4">Conta Poupança</h2>

                <div class="row">
                    <div class="col-md-5">
                        <label for="numContaPoupanca" class="form-label">Nº Conta</label>
                        <input type="number" id="numContaPoupanca" name="numContaPoupanca" class="form-control"
                               value="<c:out value="${poupanca.numConta != 0 ? poupanca.numConta : ''}"/>" required>
                    </div>
                    <div class="col-md-7">
                        <label for="nomeClientePoupanca" class="form-label">Cliente</label>
                        <input type="text" id="nomeClientePoupanca" name="nomeClientePoupanca" class="form-control"
                               value="<c:out value="${poupanca.nomeCliente}"/>" required>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-7">
                        <label for="saldoPoupanca" class="form-label">Saldo</label>
                        <input type="text" id="saldoPoupanca" name="saldoPoupanca" class="form-control"
                               value="<c:out value="${poupanca.saldo}"/>" placeholder="0.00">
                    </div>
                    <div class="col-md-5">
                        <label for="diaRendimento" class="form-label">Dia Rend.</label>
                        <input type="number" id="diaRendimento" name="diaRendimento" class="form-control"
                               value="<c:out value="${poupanca.diaDeRendimento != 0 ? poupanca.diaDeRendimento : ''}"/>">
                    </div>
                </div>

                <div class="d-flex justify-content-center gap-2 mt-4">
                    <input type="submit" name="botao" value="Inserir Poupanca" class="btn btn-primary">
                    <input type="submit" name="botao" value="Atualizar Poupanca" class="btn btn-secondary"> <input type="submit" name="botao" value="Excluir Poupanca" class="btn btn-danger">
                    <input type="submit" name="botao" value="Buscar Poupanca" class="btn btn-info">
                </div>
            </form>
			<h3 class="mt-5 text-center">Contas Poupança Ativas</h3>
            <table class="table table-striped table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Nº Conta</th>
                        <th>Cliente</th>
                        <th>Saldo</th>
                        <th>Dia Rend.</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cp" items="${contasPoupanca}">
                        <tr>
                            <td>${cp.numConta}</td>
                            <td>${cp.nomeCliente}</td>
                            <td><fmt:formatNumber value="${cp.saldo}" type="currency" currencySymbol=" R$"/></td>
                            <td>${cp.diaDeRendimento}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>

        <div class="col-md-6">
            <form action="contas" method="POST" class="p-4 border rounded shadow-sm">
                <h2 class="text-center mb-4">Conta Especial</h2>

                <div class="row">
                    <div class="col-md-5">
                        <label for="numContaEspecial" class="form-label">Nº Conta</label>
                        <input type="number" id="numContaEspecial" name="numContaEspecial" class="form-control"
                               value="<c:out value="${especial.numConta != 0 ? especial.numConta : ''}"/>" required>
                    </div>
                    <div class="col-md-7">
                        <label for="nomeClienteEspecial" class="form-label">Cliente</label>
                        <input type="text" id="nomeClienteEspecial" name="nomeClienteEspecial" class="form-control"
                               value="<c:out value="${especial.nomeCliente}"/>" required>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-7">
                        <label for="saldoEspecial" class="form-label">Saldo</Saldobr></label>
                        <input type="text" id="saldoEspecial" name="saldoEspecial" class="form-control"
                               value="<c:out value="${especial.saldo}"/>" placeholder="0.00">
                    </div>
                    <div class="col-md-5">
                        <label for="limiteEspecial" class="form-label">Limite</label>
                        <input type="text" id="limiteEspecial" name="limiteEspecial" class="form-control"
                               value="<c:out value="${especial.limite}"/>">
                    </div>
                </div>

                <div class="d-flex justify-content-center gap-2 mt-4">
                    <input type="submit" name="botao" value="Inserir Especial" class="btn btn-primary">
                    <input type="submit" name="botao" value="Atualizar Especial" class="btn btn-secondary"> <input type="submit" name="botao" value="Excluir Especial" class="btn btn-danger">
                    <input type="submit" name="botao" value="Buscar Especial" class="btn btn-info">
                </div>
            </form>
			<h3 class="mt-5 text-center">Contas Especiais Ativas</h3>
            <table class="table table-striped table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Nº Conta</th>
                        <th>Cliente</th>
                        <th>Saldo</th>
                        <th>Limite</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ce" items="${contasEspecial}">
                        <tr>
                            <td>${ce.numConta}</td>
                            <td>${ce.nomeCliente}</td>
                            <td><fmt:formatNumber value="${ce.saldo}" type="currency" currencySymbol=" R$"/></td>
                            <td><fmt:formatNumber value="${ce.limite}" type="currency" currencySymbol=" R$"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
    </div>
    <div style="height: 50px;"></div> </div>
</body>
</html>