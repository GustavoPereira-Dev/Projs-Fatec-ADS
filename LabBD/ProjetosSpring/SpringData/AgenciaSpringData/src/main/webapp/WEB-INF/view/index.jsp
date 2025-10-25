<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Menu Principal - Banco</title>
</head>
<body>
<div class="container">
    <div class="text-center mt-5">
        <h1>Sistema Bancário</h1>
        <p class="lead">Selecione uma opção para continuar</p>
    </div>

    <div class="d-flex justify-content-center gap-3 mt-4 p-5 border rounded shadow-sm">
        <a href="contas" class="btn btn-primary btn-lg" style="width: 250px;">
            Gerenciar Contas (CRUD)
        </a>
        <a href="operacao" class="btn btn-success btn-lg" style="width: 250px;">
            Realizar Operações
        </a>
    </div>
</div>
</body>
</html>