
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
<h1>Bienvenido</h1>
<h2> Inicio de Sesion</h2>
 <form action="Controlador-sesion" method="post">
<%@include file="credenciales.html" %>
  <input type="submit" value="Iniciar Sesion">
</form> 
</body>
</html>