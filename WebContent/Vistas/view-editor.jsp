<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>

<html>
	<head>
		<%@include file="../style/style.html" %>
		<%@include file = "encabezado.html" %>
		<link rel=StyleSheet href="../style/admin-style.css" type="text/css" >
		<title>Editor</title>
	</head>
	<body>
	<%@include file = "../encabezados/encabezado-editor.html" %>
	        <c:if test="${requestScope['exito'] == true}">
	            <script type="text/javascript">
	                alert("Registro realizado con exito");
	            </script>
        	</c:if>
        	
    <div id="tablaDeResultados"></div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	</body>
</html>