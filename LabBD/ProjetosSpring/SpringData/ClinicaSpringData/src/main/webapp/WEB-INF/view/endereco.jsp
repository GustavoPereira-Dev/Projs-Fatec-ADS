<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Cadastro de Endereços</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Cadastro de Endereços (Base de CEPs)</h1>
        <p class="text-muted">Este formulário gerencia a base de endereços que podem ser utilizados por pacientes e médicos.</p>
    </div>

    <form action="endereco" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <div class="row">
            <div class="col-md-4">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" id="cep" name="cep" placeholder="00000000" maxlength="8"
                       value='<c:out value="${endereco.cep}"/>' class="form-control">
            </div>
            <div class="col-md-8">
                <label for="rua" class="form-label">Rua</label>
                <input type="text" id="rua" name="rua"
                       value='<c:out value="${endereco.rua}"/>' class="form-control">
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-4">
                <label for="numero" class="form-label">Número</label>
                <input type="text" id="numero" name="numero"
                       value='<c:out value="${endereco.numero}"/>' class="form-control">
            </div>
            <div class="col-md-8">
                <label for="complemento" class="form-label">Complemento</label>
                <input type="text" id="complemento" name="complemento" placeholder="Apto / Bloco"
                       value='<c:out value="${endereco.complemento}"/>' class="form-control">
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

    <c:if test="${not empty enderecos}">
        <h2 class="mt-5 text-center">Lista de Endereços Cadastrados</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>CEP</th>
                <th>Rua</th>
                <th>Número</th>
                <th>Complemento</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="e" items="${enderecos}">
                <tr>
                    <td>${e.cep}</td>
                    <td>${e.rua}</td>
                    <td>${e.numero}</td>
                    <td>${e.complemento}</td>
                    <td>
                        <a href="endereco?cmd=editar&cep=${e.cep}" class="btn btn-sm btn-warning">Editar</a>
                        <a href="endereco?cmd=excluir&cep=${e.cep}" class="btn btn-sm btn-danger">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>