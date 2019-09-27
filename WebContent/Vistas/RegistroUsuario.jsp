<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro de nuevo usuario</title>
		<%@include file="../js/scripts.html" %>
		 <link rel="stylesheet" type="text/css" href="../style/style-registro.css">
		 <%@include file="../style/style.html" %>
	</head>
	<body>
	<%@include file="encabezado.html" %>
		<form name="formulario-registro" action="Controlador-Registro" method="post" enctype="multipart/form-data">
			Nombre
			  	<input class="form-control" type="text" name="nombre" required><br>
			  	<%@include file="credenciales.html" %>
			  	<%@include file="formulario-perfil.html" %>
			  	<p>Al hacer clic acepta nuestros <a href="terminos-y-condiciones.jsp">terminos y condiciones</a> </p>
		  		<button type="submit" class="btn btn-primary">Registrar</button> 
		</form><br><br><br><br>

		</body>
	<script src="../js/script-registro.js"></script>
</html>