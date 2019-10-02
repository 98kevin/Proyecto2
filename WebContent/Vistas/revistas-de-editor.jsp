<%@page import="backend.SqlConection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="java.util.ArrayList"%>
<%@ page import ="backend.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Revistas del editor</title>
<%@include file = "/style/style.html" %>
</head>
<body>
<%@include file = "encabezado.html" %>
	<% 
		int codigo=(Integer) session.getAttribute("codigo"); 
		Revista revistaSeleccionada = null;
		ArrayList<Revista> revistas  = SqlConection.leerRevistasDeEditor(codigo);
		if(revistas!=null){	
		   for(int i = 0; i<revistas.size(); i++){
				    %>
				 <form name="formulario-seleccion-revista" action="controlador-revista" method="post" >
				<select class="form-control" name="seleccion-revista">
				    <option value="<% out.println(revistas.get(i).getCodigo());%>">
				     <% out.println(String.valueOf(revistas.get(i).getNombre())); %></option>
					</select>
					<button type="submit" class="btn btn-primary">Siguiente</button> 
				    </form>
				    <%
			}
		} else
		    out.println("<p> No cuenta con revistas para mostrar </p>");
		 %>
</body>
</html>