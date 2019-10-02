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
	
	        <c:if test="${requestScope['exito'] == true}">
	            <script type="text/javascript">
	                alert("Registro realizado con exito");
	            </script>
        	</c:if>
	
		<div id="header">
			<ul class="nav">
				<li><a href="">Inicio</a></li>
				<li><a href="">Perfil</a>
					<ul>
						<li><a href="">Ver</a></li>
						<li><a href="">Editar</a></li>
					</ul>
				</li>
				<li><a href="">Revistas</a>
					<ul>
						<li><a href="creacion-de-revista.jsp">Crear nueva revista</a></li>
						<li><a href="revistas-de-editor.jsp">Mis revistas</a></li>
						<li><a href="">Buscar</a></li>
					</ul>
				</li>
				<li><a href="">Reportes</a>
					<ul>
						<li><a href="">Comentarios</a></li>
						<li><a href="">Suscripciones</a></li>
						<li><a href="">Me gustas</a></li>
						<li><a href="">Ganacias</a></li>
					</ul>
				</li>
				<li><a href="">${sessionScope.email} </a>
					<ul>
						<li><a href="">Ayuda</a></li>
						<li><a href="">Salir</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>