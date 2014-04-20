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
		display: inline-block;
		background-color: white;
		margin: 20px;
		border-radius: 5px;
	}

	.cuadro_producto img {
		margin-top: 10px;
		width: 150px;
		border-radius: 5px;
	}

	.cuadro_producto .titulo {
		font-weight: bold;
	}

	.cuadro_producto .autor {
		color: rgb(255,135,3);
	}

	.cuadro_producto .stock {
		font-size: 10px;
		display: inline;
	}

	.cuadro_producto .cantidad_comprar {
		display: inline;
		width: 40px;
		margin-bottom: 10px;
	}

	.button {
		width: 150px;
		display: block;
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
			<a href="perfil.jsp"><li class="lista_ultimo">Volver</li></a>
		</div>
		
		<h3 id="texto_bienvenida">Gracias por comprar en Musica para DAA. Te hemos enviado un correo de confirmación a <span style="color:#0BD318">${sessionScope.user.mail}</span>.</h3>

		<footer>
			<span style="float:left">Copyright 2014 ©</span>
			<span style="float:right">Alex Serrapio y Pablo Varela</span>
		</footer>

	</center>
</body>
</html>