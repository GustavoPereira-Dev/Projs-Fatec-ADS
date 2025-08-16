<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="icon" href="${pageContext.request.contextPath}/car.png">
<title>Cadastro Carro</title>
</head>
<body>
	<br />
	<div class="conteiner" align="center">
		<h1>Cadastro de Carros</h1>
		<br />
		<form action="carro" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type="text" min="1" step="1"
						id="placa" name="placa" placeholder="#Placa"
						value='<c:out value="${carro.placa}"/>'
						class="input-group input-group-lg" >
					</td>
					<td colspan="1">
						<input type="submit"
						id="botao" name="botao" value="Buscar"
						class="btn btn-dark">
					</td>				
				</tr>		
				<tr>
					<td colspan="4">
						<input type="text" 
						id="marca" name="marca" placeholder="Marca"
						value='<c:out value="${carro.marca}"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="modelo" name="modelo"
						placeholder="Modelo"
						value='<c:out value="${carro.modelo}"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="number" min="1900" max="2100"
						id="ano" name="ano" placeholder="Ano"
						value='<c:out value="${carro.ano}"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="cor" name="cor" placeholder="Cor"
						value='<c:out value="${carro.cor}"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Inserir"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Atualizar"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Excluir"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Listar"
						class="btn btn-dark">
					</td>								
				</tr>
			</table>
		</form>
	</div>
	<br />
	<div class="conteiner" align="center">
		<c:if test="${not empty saida}">
			<h2 style="color: blue;"><c:out value="${saida}" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty erro}">
			<h2 style="color: red;"><c:out value="${erro}" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty carros}">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>#Placa</th>
						<th>Marca</th>
						<th>Modelo</th>
						<th>Ano</th>
						<th>Cor</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${carros}">
						<tr>
							<td>${c.placa}</td>
							<td>${c.marca}</td>
							<td>${c.modelo}</td>
							<td>${c.ano}</td>
							<td>${c.cor}</td>
							<td><a href="${pageContext.request.contextPath }/carro?acao=editar&placa=${c.placa}">EDITAR</a></td>
							<td><a href="${pageContext.request.contextPath }/carro?acao=excluir&placa=${c.placa}">EXCLUIR</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>