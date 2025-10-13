<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Cadastro de Médicos</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Cadastro de Médicos</h1>
    </div>

    <form action="medico" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <div class="row">
            <div class="col-md-2">
                <label for="id" class="form-label">ID</label>
                <input type="text" id="id" name="id" class="form-control"
                       value='<c:out value="${medico.id}"/>' readonly>
            </div>
            <div class="col-md-10">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" id="nome" name="nome" placeholder="Nome completo do médico"
                       value='<c:out value="${medico.nome}"/>' class="form-control">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-6">
                <label for="contato" class="form-label">Contato (Telefone)</label>
                <input type="text" id="contato" name="contato" placeholder="(11) 99999-9999"
                       value='<c:out value="${medico.contato}"/>' class="form-control">
            </div>
            <div class="col-md-6">
                <label for="especialidade" class="form-label">Especialidade</label>
                <select id="especialidade" name="especialidade" class="form-select">
                    <option value="">--Selecione--</option>
                    <c:forEach var="esp" items="${especialidades}">
                        <option value="${esp.id}" 
                                <c:if test="${medico.especialidade.id == esp.id}">selected</c:if>>
                            <c:out value="${esp.nome}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <h4 class="mt-4">Endereço</h4>
        <div class="row mt-3">
            <div class="col-md-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" id="cep" name="cep" placeholder="00000-000"
                       value='<c:out value="${medico.endereco.cep}"/>' class="form-control">
            </div>
            <div class="col-md-9">
                <label for="rua" class="form-label">Rua</label>
                <input type="text" id="rua" name="rua"
                       value='<c:out value="${medico.endereco.rua}"/>' class="form-control">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-3">
                <label for="numero" class="form-label">Número</label>
                <input type="text" id="numero" name="numero"
                       value='<c:out value="${medico.endereco.numero}"/>' class="form-control">
            </div>
            <div class="col-md-9">
                <label for="complemento" class="form-label">Complemento</label>
                <input type="text" id="complemento" name="complemento" placeholder="Sala / Consultório"
                       value='<c:out value="${medico.endereco.complemento}"/>' class="form-control">
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

    <c:if test="${not empty medicos}">
        <h2 class="mt-5 text-center">Lista de Médicos</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Contato</th>
                <th>Especialidade</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="m" items="${medicos}">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.nome}</td>
                    <td>${m.contato}</td>
                    <td>${m.especialidade.nome}</td>
                    <td>
                        <a href="medico?acao=editar&id=${m.id}" class="btn btn-sm btn-warning">Editar</a>
                        <a href="medico?acao=excluir&id=${m.id}" class="btn btn-sm btn-danger">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>