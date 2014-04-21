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
	form {
		width: 400px;
	}

	form label {
		float: left;
	}

	form input {
		float: right;
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
			<c:choose>
				<c:when test="${sessionScope.user != null && sessionScope.admin != null}">
					<a href="insertar_producto.jsp"><li>Nuevo producto</li></a>
					<a href="perfil.jsp"><li class="lista_ultimo" style="color:#0BD318">${sessionScope.user.username}</li></a>
				</c:when>
				<c:otherwise>
					<a href="registro.jsp"><li>Registro</li></a>
					<a href="iniciar_sesion.jsp"><li class="lista_ultimo">Iniciar sesión</li></a>
				</c:otherwise>
			</c:choose>
		</div>
		
		<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda" method="post">
			<br><br>
			<input type="hidden" value="" name="insertar_producto"/>
			<label>ID</label>
			<input type="text" name="form_id"><br><br>
			<label>Título</label>
			<input type="text" name="form_titulo"><br><br>
			<label>Artista</label>
			<input type="text" name="form_artista"><br><br>
			<label>País</label>
			<input type="text" name="form_pais"><br><br>
			<label>Precio</label>
			<input type="text" name="form_precio"><br><br>
			<label>Cantidad</label>
			<input type="text" name="form_cantidad"><br><br>
			<input type="submit" value="Insertar producto">
		</form>

		<footer>
			<span style="float:left">Copyright 2014 ©</span>
			<span style="float:right">Alex Serrapio y Pablo Varela</span>
		</footer>

	</center>
</body>
</html>
