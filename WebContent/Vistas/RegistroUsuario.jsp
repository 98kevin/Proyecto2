<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro de nuevo usuario</title>
		<%@include file="../js/scripts.html" %>
		 <link rel="stylesheet" type="text/css" href="../style/style-registro.css">
	</head>
	<body>
	<%@include file="encabezado.html" %>
		<form name="formulario-registro" action="Controlador-Registro" method="post" enctype="multipart/form-data">
			<p>Nombre<br>
			  	<input type="text" name="nombre" required><br>
			  	</p>
			  	<%@include file="credenciales.html" %>
			  	<%@include file="formulario-perfil.html" %>
		  		<button type="submit" class="btn btn-primary">Registrar</button> 
		</form><br><br><br><br>

		   <c:if test="${requestScope['error'] != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#InfoModal').modal('show');
                });
            </script>
        </c:if>

        <div class="modal fade" id="InfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Error en la creacion del cliente
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
		
		
		<%@include file="pie-de-pagina.html" %>
		</body>
		<script src="../js/script-registro.js"></script>
</html>