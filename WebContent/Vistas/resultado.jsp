<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="perfil" class="backend.Perfil"></jsp:useBean>  
<jsp:setProperty property="*" name="perfil"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro exitoso</title>
<%@include file="/style/style.html"%>
</head>
<body>
		<%@include file = "encabezado.html" %>
        <h2>Registro realizado con exito</h2>
        <p> Ahora debe iniciar <a href="Login.jsp">sesion</a></p>
        <%
            out.print(session.getAttribute("user"));
        %>
</body>
</html>