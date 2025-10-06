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
<title>Cadastro Produto</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div class="conteiner" align="center">
		<h1>Listagem de Compras</h1>
		<br />
		<form action="compra" method="post">
			<table>
				<tr>
					<td colspan="4">
						<input type="text" min="1" step="1"
						id="nome_cliente" name="nome_cliente" placeholder="Nome Cliente"
						value='<c:out value="${compra.nomeCliente}"/>'
						class="input-group input-group-lg" >
					</td>			
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" min="1" step="1"
						id="nome_produto" name="nome_produto" placeholder="Nome Produto"
						value='<c:out value="${compra.Produto}"/>'
						class="input-group input-group-lg" >
					</td>			
				</tr>		
				<tr>
					<td colspan="4">
						<input type="number" min="1" step="1"
						id="quantidade" name="quantidade" placeholder="Quantidade"
						value='<c:out value="${compra.quantidade}"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="number"
						id="valor_total" name="valor_total" step="0.01" placeholder="Valor"
						value='<c:out value="${compra.valorTotal}"/>'
						class="input-group input-group-lg" >
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="date"
						id="data" name="data" placeholder="Data de Compra"
						value='<c:out value="${compra.dataCompra}"/>'
						class="input-group input-group-lg" >
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
		<c:if test="${not empty compras}">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>Nome Cliente</th>
						<th>Nome Produto</th>
						<th>Quantidade</th>
						<th>Valor Total</th>
						<th>Date de Compra</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="${compras}">
						<tr>
							<td>${c.nomeCliente}</td>
							<td>${c.nomeProduto}</td>
							<td>${c.quantidade}</td>
							<td>${c.valorTotal}</td>
							<td>${c.dataCompra}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>