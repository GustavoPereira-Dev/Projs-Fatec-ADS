<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Agendamento de Consultas</title>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <div class="text-center mt-4">
        <h1>Agendamento de Consultas</h1>
    </div>

    <form action="consulta" method="post" class="mt-4 p-4 border rounded shadow-sm">
        <input type="hidden" id="id" name="id" value='<c:out value="${consulta.id}"/>'>
        
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
                <label for="medico" class="form-label">Médico</label>
                <select id="medico" name="medico" class="form-select" required>
                    <option value="">--Selecione o Médico--</option>
                    <c:forEach var="m" items="${medicos}">
                        <option value="${m.id}">${m.nome} - (${m.especialidade.nome})</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
             <div class="col-md-12">
                 <label for="dataHora" class="form-label">Data e Hora da Consulta</label>
                 <input type="datetime-local" id="dataHora" name="dataHora" class="form-control" required>
            </div>
        </div>

        <div class="d-flex justify-content-center gap-2 mt-4">
            <input type="submit" name="botao" value="Agendar" class="btn btn-primary">
            <input type="submit" name="botao" value="Excluir" class="btn btn-danger" formnovalidate>
            <input type="submit" name="botao" value="Listar" class="btn btn-dark" formnovalidate>
        </div>
    </form>
    
    <div class="text-center mt-4">
        <c:if test="${not empty saida}"><h3 class="text-success"><c:out value="${saida}"/></h3></c:if>
        <c:if test="${not empty erro}"><h3 class="text-danger"><c:out value="${erro}"/></h3></c:if>
    </div>

    <c:if test="${not empty consultas}">
        <h2 class="mt-5 text-center">Consultas Agendadas</h2>
        <table class="table table-striped table-bordered mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Paciente</th>
                <th>Médico</th>
                <th>Especialidade</th>
                <th>Data e Hora</th>
                <th>Dias Restantes</th>
                <th>Ação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${consultas}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.paciente.nome}</td>
                    <td>${c.medico.nome}</td>
                    <td>${c.medico.especialidade.nome}</td>
                    <td>${c.dataHora}</td>
                    <td>${c.diasRestantes}</td>
                    <td>
                        <form action="consulta" method="post" style="display:inline;">
                             <input type="hidden" name="id" value="${c.id}">
                             <input type="hidden" name="paciente" value="${c.paciente.id}">
                             <input type="hidden" name="medico" value="${c.medico.id}">
                             <input type="hidden" name="dataHora" value="${c.dataHora}">
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