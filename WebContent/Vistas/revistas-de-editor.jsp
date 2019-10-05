<%@page import="backend.SqlConection"%>
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
<title>Revistas del editor</title>
<c:import url="/style/style.html" charEncoding="UTF-8" />
	 <link rel=StyleSheet href="../style/admin-style.css" type="text/css" >
</head>
<body>
<c:import url= "encabezado.html" />
	<%@include file = "../encabezados/encabezado-editor.html" %>
      <c:set var = "codigoRevista" scope ="session" value = "${session.codigo}"/>
      <% int codigoEditor =(Integer) session.getAttribute("codigo"); 
      ArrayList<Revista> revistas =  SqlConection.leerRevistasDeEditor(codigoEditor); 
      session.setAttribute("revistas", revistas);
      %>
		<c:if test="${revistas != null}">
			<form name="formulario-seleccion-revista" action="controlador-revista" method="post" >
			<select class="form-control" name="seleccion-revista">
				<c:forEach var="item" items="${revistas}">
				    <option value = <c:out value="${item.codigo}"/>>
				     ${item.nombre} </option>
			    </c:forEach>
			    </select>
			    <button type="submit" class="btn btn-primary">Siguiente</button> 
	</form>
		</c:if>
			<c:if test="${revistas == null}"> 
			   <c:out value="${'No cuenta con revistas para mostrar'}"/>
		</c:if>
</body>
</html>