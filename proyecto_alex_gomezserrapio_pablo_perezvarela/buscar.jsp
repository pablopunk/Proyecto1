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
			<a href="registro.jsp"><li>Registro</li></a>
			<a href="iniciar_sesion.jsp"><li class="lista_ultimo">Iniciar sesión</li></a>
		</div>
		
		<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda" method="post">
			<br><br>
			<input type="hidden" value="" name="busqueda"/>
			<label>Título</label>
			<input type="text" name="form_titulo"><br>
			<label>Artista</label>
			<input type="text" name="form_artista"><br>
			<label>País</label>
			<input type="text" name="form_pais"><br>
			<label>Precio máximo</label>
			<input type="text" name="form_precio"><br>
			<input type="submit" value="Buscar">
		</form>

		<footer>
			<span style="float:left">Copyright 2014 ©</span>
			<span style="float:right">Alex Serrapio y Pablo Varela</span>
		</footer>

	</center>
</body>
</html>
