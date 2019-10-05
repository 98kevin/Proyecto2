<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<%@ page import ="backend.Pago"%>
<%@ page import ="backend.SqlConection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seleccion de cantidad de pagos </title>
    <%@include file = "/style/style.html" %>
   <link rel=StyleSheet href="../../style/admin-style.css" type="text/css" >
</head>
<body>
  <%@include file = "../encabezado.html" %>
  <%@include file = "../../encabezados/encabezado-suscriptor.html" %>
	      
	<p><span style="color: #196b8a;"><strong>Historial de pagos</strong></span></p>
	<ul>
	<c:forEach var="pago" items="${pagos}">
		    <li><strong><span style="color: #196b8a;">codigo:  </span></strong>"${pago.idPago }"<strong><br>
		      </strong>
		    <strong><span style="color: #196b8a;">fecha:  </span></strong>"${pago.fecha }"<strong><br>
		      </strong>
		      </li><br>
	</c:forEach>
	</ul>
	
	<form name="formulario-suscripcion" action="controlador-pagos" method="post"> 
	Cantidad de pagos: 
  	<input class="form-control" type="number" id="cantidad-de-pagos" name="cantidad-de-pagos" 
  	onchange="calcularTotal();"  required/><br/>
  	<input class="form-control" type="hidden" id="codigo-revista" value= "${requestScope.codigoRevista}"/>
  	 <input class="form-control" type="hidden" id="precioRevista" value= "${requestScope.codigoRevista.cuotaSuscripcion}"/>
	<button type="submit" class="btn btn-primary">Pagar</button> <br><br>
	</form>
	
</body>
	<script src="../../js/script-pagos.js"></script>

</html>