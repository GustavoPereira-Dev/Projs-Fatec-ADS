<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Cadastro de Especialidades</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Cadastro de Especialidades</h1>
    </div>

    <form action="especialidade" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <div class="row align-items-end">
            <div class="col-md-2">
                <label for="id" class="form-label">ID</label>
                <input type="text" id="id" name="id" class="form-control" value='<c:out value="${especialidade.id}"/>' readonly>
            </div>
            <div class="col-md-10">
                <label for="nome" class="form-label">Nome da Especialidade</label>
                <input type="text" id="nome" name="nome" placeholder="Ex: Cardiologia" value='<c:out value="${especialidade.nome}"/>' class="form-control">
            </div>
        </div>
        <div class="d-flex justify-content-center gap-2 mt-4">
            <input type="submit" name="botao" value="Inserir" class="btn btn-primary">
            <input type="submit" name="botao" value="Atualizar" class="btn btn-secondary">
            <input type="submit" name="botao" value="Excluir" class="btn btn-danger">
            <input type="submit" name="botao" value="Listar" class="btn btn-dark">
        </div>
    </form>

    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>

    <c:if test="${not empty especialidades}">
        <h2 class="mt-5 text-center">Lista de Especialidades</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="e" items="${especialidades}">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.nome}</td>
                    <td>
                        <a href="especialidade?cmd=editar&id=${e.id}" class="btn btn-sm btn-warning">Editar</a>
                        <a href="especialidade?cmd=excluir&id=${e.id}" class="btn btn-sm btn-danger">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>