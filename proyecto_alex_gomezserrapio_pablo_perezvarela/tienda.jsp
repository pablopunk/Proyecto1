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
	.cuadro_producto {
		width: 200px;
		height: 200px;
		display: inline-block;
		background-color: white;
		margin: 20px;
		border-radius: 5px;
	}
	</style>

</head>

<body>
	<center>

		<header>
			<a href="tienda.jsp"><img id="logo" src="img/itunes_logo.png" onmouseover="this.src='img/itunes_logo2.png'" onmouseout="this.src='img/itunes_logo.png'"/></a>
			<h1>Música para DAA</h1>
		</header>
		<div id="menu">
			<a href="proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios"></a>

			<form id="formulario_ver_tienda" method="post" action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda">
				<input type="hidden" name="mostrar_tienda">
				<a href="javascript:{}" onclick="document.getElementById('formulario_ver_tienda').submit();"><li>Tienda</li></a>
			</form>

			<c:choose>
			<c:when test="${sessionScope.username != null}">
			<a href="perfil.jsp"><li class="lista_ultimo" style="color:#0BD318">${sessionScope.username}</li></a>
		</c:when>
		<c:otherwise>
		<a href=""><li>Registro</li></a>
		<a href="iniciar_sesion.jsp"><li class="lista_ultimo">Iniciar sesión</li></a>
	</c:otherwise>
</c:choose>
</div>
		
		<c:forEach var="iterator" items="${sessionScope.productos}">
			<div class="cuadro_producto">
				<img src=""/>
				<h3>${iterator.cd.nombre}</h3>
				<h4>${iterator.cd.autor}</h4>
				<p>${iterator.cd.precio}</p>
			</div>
		</c:forEach>

	<footer>
		<span style="float:left">Copyright 2014 ©</span>
		<span style="float:right">Alex Serrapio y Pablo Varela</span>
	</footer>

</center>
</body>
</html>