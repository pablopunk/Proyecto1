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
	.button {
		width: 200px;
		border-radius: 5px;
	}
	.button_small {
		width: 120px;
		border-radius: 5px;
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
			<form id="formulario_ver_tienda" method="post" action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda">
				<input type="hidden" name="mostrar_tienda">
				<a href="javascript:{}" onclick="document.getElementById('formulario_ver_tienda').submit();"><li>Tienda</li></a>
			</form>
			<a href="buscar.jsp"><li>Buscar</li></a>
			<a href="perfil.jsp"><li class="lista_ultimo" style="color:#0BD318">${sessionScope.user.username}</li></a>
		</div>

		<br><br>
		<p>${sessionScope.user.username} - ${sessionScope.user.mail}</p>

		<c:choose>
		<c:when test="${sessionScope.user.vip == true}">
		<h3 style="color:#0BD318">Eres usuario VIP</h3>
	</c:when>
</c:choose>

<br><br>

<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios" method="post">
	<input type="hidden" name="mostrar_historial_compras"/>
	<input type="image" src="img/mostrar_historial_compras.png" class="button"> 
</form>

<br><br>

<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios" method="post">
	<input type="hidden" name="cerrar_sesion"/>
	<input type="image" src="img/cerrar_sesion.png" class="button_small"> 
</form>

<footer>
	<span style="float:left">Copyright 2014 ©</span>
	<span style="float:right">Alex Serrapio y Pablo Varela</span>
</footer>

</center>
</body>
</html>