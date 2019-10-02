<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="backend.*"%>
	<!DOCTYPE html>
<html lang="es-gt">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Revista</title>
    <%@include file = "/style/style.html" %>
  </head>
  <body>
  <%@include file = "encabezado.html" %>
  <% Revista revistaSeleccionada = (Revista) session.getAttribute("revista");
  System.out.println("Codigo de la revista " + revistaSeleccionada);
  %>
    <h2><% out.println(String.valueOf(revistaSeleccionada.getNombre())); %></h2>
    <p><strong>Descripcion: </strong><% out.println(String.valueOf(revistaSeleccionada.getDescripcion())); %></p>
    <p><strong>etiquetas: </strong><% out.println(String.valueOf(revistaSeleccionada.getEtiquetas())); %></p>
    <p><strong>cuota de suscripcion: </strong><% out.println(String.valueOf(revistaSeleccionada.getCuotaSuscripcion())); %></p>
     <form action="carga-revista" method="post" enctype="multipart/form-data" accept="pdf">
    <input type="file" name="file" />
    <input type="date" name="date" />
	<button type="submit" class="btn btn-primary">Subir nueva edicion</button> 
	</form>   
  </body>
</html>