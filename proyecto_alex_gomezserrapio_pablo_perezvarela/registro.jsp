
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

	<script type="text/javascript">
	function validar() {
		var mail = document.getElementById("form_mail").value;
		var password = document.getElementById("form_password").value;
		var password2 = document.getElementById("form_password2").value;
		var username = document.getElementById("form_username").value;
		var msg = "Se han producido errores:";
		var error = 0;

		if (!isEmail(mail) || isEmpty(mail)) {
			msg += "\nIntroduce un correo valido";
			error = 1;
		}

		if (isEmpty(username)) {
			msg += "\nIntroduce un nombre de usuario valido";
			error = 1;
		}

		if (!isEmpty(password)) {
			if (password !== password2) {
				msg += "\nLas contraseñas no coinciden";
				error = 1;
			}
		} else {
			msg += "\nLa contraseña no puede estar vacia";
			error = 1;
		}

		if (error) {
			alert(msg);
			return false;
		}

		return true;
	}

	function isEmail(mail) {
		var re= /^[a-z0-9_\-\.]+@[a-z0-9\-\.]+\.[a-z]{2,}$/i;
		if (re.test(mail)) {
			return true;
		}
		return false;
	}

	function isEmpty(cadena){
		if((cadena == null) || (cadena == "")) {
			return true;
		} else {
			return false;
		}
	}
	</script>

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
			<a href="registro.jsp"><li>Registro</li></a>
			<a href="iniciar_sesion.jsp"><li class="lista_ultimo">Iniciar sesión</li></a>
		</div>
		<form action="/proyecto_alex_gomezserrapio_pablo_perezvarela/GestionUsuarios" method="post">
			<br><br>
			<input type="hidden" value="" name="registro"/>
			<label>Username</label>
			<input type="text" name="form_username"><br>
			<label>Password</label>
			<input type="password" name="form_password"><br>
			<label>Confirmar Password</label>
			<input type="password" name="form_password2"><br>
			<label>Correo</label>
			<input type="text" name="form_mail"><br>
			<input type="submit" value="Registrarse">
		</form>

		<footer>
			<span style="float:left">Copyright 2014 ©</span>
			<span style="float:right">Alex Serrapio y Pablo Varela</span>
		</footer>

	</center>
</body>
</html>
