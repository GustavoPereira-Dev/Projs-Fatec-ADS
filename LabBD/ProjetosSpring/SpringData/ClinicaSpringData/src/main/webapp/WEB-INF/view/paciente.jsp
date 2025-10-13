<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Cadastro de Pacientes</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Cadastro de Pacientes</h1>
    </div>

    <form action="paciente" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <div class="row">
            <div class="col-md-2">
                <label for="id" class="form-label">ID</label>
                <input type="text" id="id" name="id" class="form-control"
                       value='<c:out value="${paciente.id}"/>' readonly>
            </div>
            <div class="col-md-10">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" id="nome" name="nome" placeholder="Nome completo"
                       value='<c:out value="${paciente.nome}"/>' class="form-control">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-6">
                <label for="telefone" class="form-label">Telefone</label>
                <input type="text" id="telefone" name="telefone" placeholder="(11) 99999-9999"
                       value='<c:out value="${paciente.telefone}"/>' class="form-control">
            </div>
            <div class="col-md-6">
                <label for="numeroBeneficiario" class="form-label">Nº Beneficiário</label>
                <input type="text" id="numeroBeneficiario" name="numeroBeneficiario" placeholder="Número do convênio"
                       value='<c:out value="${paciente.numeroBeneficiario}"/>' class="form-control">
            </div>
        </div>

        <h4 class="mt-4">Endereço</h4>
        <div class="row mt-3">
            <div class="col-md-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" id="cep" name="cep" placeholder="00000-000"
                       value='<c:out value="${paciente.endereco.cep}"/>' class="form-control">
            </div>
            <div class="col-md-9">
                <label for="rua" class="form-label">Rua</label>
                <input type="text" id="rua" name="rua"
                       value='<c:out value="${paciente.endereco.rua}"/>' class="form-control">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-3">
                <label for="numero" class="form-label">Número</label>
                <input type="text" id="numero" name="numero"
                       value='<c:out value="${paciente.endereco.numero}"/>' class="form-control">
            </div>
            <div class="col-md-9">
                <label for="complemento" class="form-label">Complemento</label>
                <input type="text" id="complemento" name="complemento" placeholder="Apto / Bloco"
                       value='<c:out value="${paciente.endereco.complemento}"/>' class="form-control">
            </div>
        </div>
        <div class="d-flex justify-content-center gap-2 mt-4">
            <input type="submit" name="botao" value="Inserir" class="btn btn-primary">
            <input type="submit" name="botao" value="Atualizar" class="btn btn-secondary">
            <input type="submit" name="botao" value="Excluir" class="btn btn-danger">
            <input type="submit" name="botao" value="Buscar" class="btn btn-info">
            <input type="submit" name="botao" value="Listar" class="btn btn-dark">
        </div>
    </form>

    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>

    <c:if test="${not empty pacientes}">
        <h2 class="mt-5 text-center">Lista de Pacientes</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Beneficiário</th>
                <th>CEP</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${pacientes}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.nome}</td>
                    <td>${p.telefone}</td>
                    <td>${p.numeroBeneficiario}</td>
                    <td>${p.endereco.cep}</td>
                    <td>
                        <a href="paciente?acao=editar&id=${p.id}" class="btn btn-sm btn-warning">Editar</a>
                        <a href="paciente?acao=excluir&id=${p.id}" class="btn btn-sm btn-danger">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>