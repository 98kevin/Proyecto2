<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<%@ page import ="backend.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagos</title>
    <%@include file = "/style/style.html" %>
   <link rel=StyleSheet href="../../style/admin-style.css" type="text/css" >
</head>
<body>
  <%@include file = "../encabezado.html" %>
  <%@include file = "../../encabezados/encabezado-suscriptor.html" %>
  
	      <% int codigoEditor =(Integer) session.getAttribute("codigo"); 
	      ArrayList<Revista> revistas =  SqlConection.leerRevistasSuscritas(codigoEditor);
	      //TODO seleccionar solo las suscritas
	      session.setAttribute("revistas", revistas); %>
	      
	<p><span style="color: #196b8a;"><strong>Listado completo de revistas</strong></span></p>
	
	<c:forEach var="revista" items="${revistas}">
		<form name="formulario-suscripcion" action="controlador-pagos" method="get"> 
		    <p><strong><span style="color: #196b8a;">Titulo: </span></strong>"${revista.nombre }"<strong><br>
		      </strong></p>
		    <p><strong><span style="color: #196b8a;">Descripcion: </span></strong>"${revista.descripcion }"<strong><br>
		      </strong></p>
		     <input class="form-control" type="hidden" value="${revista.codigo}" name="codigoRevista"/>
		     <button type="submit" class="btn btn-primary">Seleccionar</button> <br><br>
		      </form>
	</c:forEach>
  
</body>
</html>