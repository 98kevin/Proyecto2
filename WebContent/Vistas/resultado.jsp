<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="perfil" class="backend.Perfil"></jsp:useBean>  
<jsp:setProperty property="*" name="perfil"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <h2>registro completo</h2>
        Nombre: <%=perfil.getNombre()%>
        <br>
        Gustos: <%=perfil.getGustos()%>
        <br>
        <%
            //out.print(session.getAttribute("user"));
        %>
</body>
</html>