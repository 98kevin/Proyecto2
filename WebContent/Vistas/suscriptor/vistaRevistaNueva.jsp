<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Ver revista</title>
</head>
<body>
<h1>Carga de mi revista</h1>
<embed src="../../RevistasCargadas/${sessionScope.path}" width="1300" height="500">
<%@include file="panel-revista.jsp" %>
<footer>
<br><br><br><br>
<h5>Copyright R 2019</h5>
</footer>
</body>
</html>