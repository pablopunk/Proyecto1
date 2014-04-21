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
			<a href="proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios"></a>

			<form id="formulario_ver_tienda" method="post" action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda">
				<input type="hidden" name="mostrar_tienda">
				<a href="javascript:{}" onclick="document.getElementById('formulario_ver_tienda').submit();"><li>Tienda</li></a>
			</form>

			<c:choose>
			<c:when test="${sessionScope.user != null && sessionScope.admin == null}">
			<a href="buscar.jsp"><li>Buscar</li></a>
			<a href="perfil.jsp"><li class="lista_ultimo" style="color:#0BD318">${sessionScope.user.username}</li></a>
		</c:when>
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
<form method="post" action="/proyecto_alex_gomezserrapio_pablo_perezvarela/Tienda">
	<c:forEach var="iterator" items="${sessionScope.productos}">
	<div class="cuadro_producto">
		<img src="tienda-img/${iterator.cd.id}.jpg"/>
		<p class="titulo">${iterator.cd.nombre}</p>
		<p class="autor">${iterator.cd.autor}</p>
		<p class="precio">$${iterator.cd.precio}</p>
		<c:choose>
		<c:when test="${iterator.stock == 0}">
		<p class="stock" style="color: red">No quedan unidades</p>
	</c:when>
	<c:otherwise>
	<p class="stock">${iterator.stock} restantes</p>
	<input type="number" name="cantidad_${iterator.cd.id}" value="0" min="0" max="${iterator.stock}"/>
</c:otherwise>	
</c:choose>
</div>
</c:forEach>

<input type="hidden" name="comprar">
<input type="image" src="img/comprar.png" class="button">

</form>

<footer>
	<span style="float:left">Copyright 2014 ©</span>
	<span style="float:right">Alex Serrapio y Pablo Varela</span>
</footer>

</center>
</body>
</html>