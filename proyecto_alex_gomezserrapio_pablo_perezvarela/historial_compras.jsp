<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false"%>

<html>
<head>
	<title>Música para DAA</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">

	<style type="text/css">
	#caratula {
		width: 100px;
	}
	div.compra {
		background-color: white;
		border-radius: 5px;
		width: 500px;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	</style>
</head>

<body>
	<center>

		<header>
			<a href="index.jsp"><img id="logo" src="img/itunes_logo.png" onmouseover="this.src='img/itunes_logo2.png'" onmouseout="this.src='img/itunes_logo.png'"/></a>
			<h1>Música para DAA</h1>
		</header>
		<div id="menu">
			<a href="proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios"></a>

			<form id="formulario_ver_tienda" method="post" action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda">
				<input type="hidden" name="mostrar_tienda">
				<a href="javascript:{}" onclick="document.getElementById('formulario_ver_tienda').submit();"><li>Tienda</li></a>
			</form>

			<c:choose>
			<c:when test="${sessionScope.user != null}">
			<a href="perfil.jsp"><li class="lista_ultimo" style="color:#0BD318">${sessionScope.user.username}</li></a>
		</c:when>
		<c:otherwise>
		<a href="registro.jsp"><li>Registro</li></a>
		<a href="iniciar_sesion.jsp"><li class="lista_ultimo">Iniciar sesión</li></a>
	</c:otherwise>
</c:choose>
</div>

<c:choose>
<c:when test="${not empty sessionScope.historial_compras}">

<c:forEach var="iterator_compra" items="${sessionScope.historial_compras}">
<div class="compra">
<table>
	<tr colspan="4" align="center"><h3>Fecha de pedido: ${iterator_compra.fecha}</h3></tr>
	<c:forEach var="iterator_producto" items="${iterator_compra.productos}">
	<tr>
		<td rowspan="5">
			<img id="caratula" src="tienda-img/${iterator_producto.cd.id}.jpg"/>
		</td>
	</tr>
	<tr>
		<td>${iterator_producto.cd.nombre}</td>
	</tr>
	<tr>
		<td>${iterator_producto.cd.autor}</td>
	</tr>
	<tr>
		<td>$${iterator_producto.cd.precio}</td>
	</tr>
	<tr>
		<td>${iterator_producto.cantidad} unidades</td>
	</tr>
</c:forEach>
<tr colspan="4" align="center">Precio total de la compra $${iterator_compra.precio}</tr>
</table>
</div>
</c:forEach>

</c:when>
<c:otherwise>

<h3>No hay compras</h3>

</c:otherwise>
</c:choose>

</center>
</body>
</html>