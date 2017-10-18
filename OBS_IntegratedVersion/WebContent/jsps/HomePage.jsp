<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="HeaderPage.jsp" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/p2.js" type="text/javascript"></script>
	</head>
	
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="" align="center" color="blue">
		<div id="home">
		<ul>
			<li><a href="getAdminLogin.do">Administrator Login</a></li>
			<li><a href="getUserLogin.do">User Login</a></li>
		</ul>
		</div>
		
	</body>
</html>