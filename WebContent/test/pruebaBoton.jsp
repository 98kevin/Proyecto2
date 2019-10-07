<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
 <script>
 function mostrarMensaje(){
	 alert("Me has pulsado");
 };
 </script>
 
 <link rel=StyleSheet href="../style/admin-style.css" type="text/css" >
	
	<div id="header">
			<ul class="nav">
				<li><button onclick="mostrarMensaje()">Click me</button> Inicio</li>
				<li><a href="">Revistas</a>
					<ul>
						<li><a href="">Todas las revistas</a></li>
						<li onclick="mostarMensaje('Mis revistas')"><a href="">Mis revistas</a></li>
					</ul>
				</li>
				<li><a href="">Suscripciones</a>
					<ul>
						<li><a href="nueva-suscripcion.jsp">Nueva Suscripcion</a></li>
						<li><a href="">Cancelar Suscripcion</a></li>
					</ul>
				</li>
				<li><a href="">Pagos</a>
					<ul>
						<li><a href="suscriptor/pagos.jsp">Nuevo Pago</a></li>
						<li><a href="">Historial</a></li>
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