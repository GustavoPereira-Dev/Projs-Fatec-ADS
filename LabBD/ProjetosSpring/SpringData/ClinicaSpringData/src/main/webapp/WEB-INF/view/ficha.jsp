<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Ficha de Informações</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Ficha de Informações por Especialidade</h1>
    </div>

    <form action="ficha" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <input type="hidden" id="id" name="id" value='<c:out value="${ficha.id}"/>'>

        <div class="row">
            <div class="col-md-6">
                <label for="paciente" class="form-label">Paciente</label>
                <select id="paciente" name="paciente" class="form-select" required>
                    <option value="">--Selecione o Paciente--</option>
                    <c:forEach var="p" items="${pacientes}">
                        <option value="${p.id}">${p.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="especialidade" class="form-label">Especialidade</label>
                <select id="especialidade" name="especialidade" class="form-select" required>
                    <option value="">--Selecione a Especialidade--</option>
                    <c:forEach var="e" items="${especialidades}">
                        <option value="${e.id}">${e.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
             <div class="col-md-12">
                 <label for="observacoes" class="form-label">Observações</label>
                 <textarea id="observacoes" name="observacoes" class="form-control" rows="4" placeholder="Digite as informações atualizadas do paciente para esta especialidade..."></textarea>
            </div>
        </div>

        <div class="d-flex justify-content-center gap-2 mt-4">
            <input type="submit" name="botao" value="Salvar" class="btn btn-primary">
            <input type="submit" name="botao" value="Excluir" class="btn btn-danger" formnovalidate>
            <input type="submit" name="botao" value="Listar" class="btn btn-dark" formnovalidate>
        </div>
    </form>
    
    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>

    <c:if test="${not empty fichas}">
        <h2 class="mt-5 text-center">Fichas Registradas</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Paciente</th>
                <th>Especialidade</th>
                <th>Observações</th>
                <th>Ação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="f" items="${fichas}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.paciente.nome}</td>
                    <td>${f.especialidade.nome}</td>
                    <td>${f.observacoes}</td>
                    <td>
                        <form action="ficha" method="post" style="display:inline;">
                             <input type="hidden" name="id" value="${f.id}">
                             <input type="hidden" name="paciente" value="${f.paciente.id}">
                             <input type="hidden" name="especialidade" value="${f.especialidade.id}">
                             <input type="submit" name="botao" value="Excluir" class="btn btn-sm btn-danger">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>