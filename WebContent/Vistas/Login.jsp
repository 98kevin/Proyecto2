<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio de Sesion</title>
<%@include file="/style/style.html"%>
</head>
<body>
<%@include file="encabezado.html" %>
<h1>Bienvenido</h1>
<h2> Inicio de Sesion</h2>
 <form action="Controlador-sesion" method="post">
			<%@include file="credenciales.html" %>
			<button type="submit" class="btn btn-primary">Iniciar sesion</button> 
</form> 
			<p>Si aun no tiene un perfil, puede  <a href="RegistroUsuario.jsp">registrarse</a> </p>
</body>
</html>