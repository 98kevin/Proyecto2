<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<%@ page import ="backend.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Previsualizacion de la revista</title>
		<%@include file = "../style/style.html" %>
</head>
<body>
		<%@include file = "encabezado.html" %>
		<%@include file = "../encabezados/encabezado-suscriptor.html" %>
		<form name="formSuscripcion" action="nueva-suscripcion" method="post" >
		    <h3><strong style="color: #24357a;">Previsualizacion de la Revista<br>
      </strong></h3>
	    <p><strong style="color: #24357a;">Codigo</strong><span style="color: #151f47;">:</span>&nbsp;
	      ${sessionScope.revistaSuscripcion.codigo}</p>
	    <p><strong style="color: #24357a;">Nombre</strong><span style="color: #24357a;">:</span>&nbsp;
	     ${sessionScope.revistaSuscripcion.nombre}</p>
	    <p><strong style="color: #24357a;">Etiquetas</strong><span style="color: #24357a;">:</span>
	      ${sessionScope.revistaSuscripcion.etiquetas}</p>
	    <p><strong style="color: #24357a;">Cuota de suscripcion:</strong>
	      ${sessionScope.revistaSuscripcion.cuotaSuscripcion}</p>
	    <p><strong style="color: #24357a;">Categoria</strong><span style="color: #24357a;">:</span>
	      ${sessionScope.revistaSuscripcion.categoria} </p>
	    <p><strong style="color: #24357a;">Editor</strong><span style="color: #24357a;">:</span>
	    ${requestScope.autor}</p>
	   	<p><strong style="color: #24357a;">Canitdad de Me Gusta</strong><span style="color: #24357a;">:</span>
	    ${requestScope.cant}</p>
	    <p><strong style="color: #24357a;">Inicio de la suscripcion</strong><span style="color: #24357a;">:</span></p>
	    <input type = "date" name="date" required /><br>
	    <button type="submit" class="btn btn-primary"> Siguiente </button> <br>
    </form>
</body>
</html>