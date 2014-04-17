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
			<a href="tienda.jsp"><img id="logo" src="img/itunes_logo.png" onmouseover="this.src='img/itunes_logo2.png'" onmouseout="this.src='img/itunes_logo.png'"/></a>
			<h1>Música para DAA</h1>
		</header>
		<div id="menu">
			<a href=""><li>Tienda</li></a>
			<a href=""><li>Registro</li></a>
			<a href=""><li class="lista_ultimo">Iniciar sesión</li></a>
		</div>
		
		<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios" method="post">
			<br><br>
			<input type="hidden" value="" name="iniciar_sesion"/>
			<label>Username</label>
			<input type="text" name="form_username"><br>
			<label>Password</label>
			<input type="password" name="form_password"><br>
			<input type="submit" value="Iniciar sesion">
		</form>

		<footer>
			<span style="float:left">Copyright 2014 ©</span>
			<span style="float:right">Alex Serrapio y Pablo Varela</span>
		</footer>

	</center>
</body>
</html>