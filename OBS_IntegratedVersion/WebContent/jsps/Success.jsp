<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ include file="AdminHome.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<script src="js/p2.js" type="text/javascript"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="success">
		<h1>${message}</h1>
		<a href="getHomePage.do">Go to Home</a>
		</div>
	</body>
</html>