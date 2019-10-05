<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script> 
	 		$.get('testQuery',  function(data) { 
		 			alert(data);
		 			$('#data').text(data);
		 	}); 
 </script>
 <button id="boton2">try</button>
 <div id="data"></div> 
  <script> 
		 boton2.addEventListener("click", myFunction);
		 function myFunction() {
		   alert ("Hello World!");
		 } 
 </script>
</body>
</html>