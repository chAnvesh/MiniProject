<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
     <%@include file="HomePage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/p2.js" type="text/javascript"></script>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
<!-- 	<style type="text/css"  src="/css/style.css">
		 #adminLogin{
			padding: 8px 16px;
				width: 75.2%;
				height: 500px;
			float:right;
			background-image:url("images/Desert.jpg");
			}  
	</style> -->
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload=""  align="center">
<div id="Login">
<form action="getAdminHome.do" method="post">
	
	<h2>${loginErr}</h2>
	<table align="center">
	<tr>
			<td align="left"><label for="id"><b>Enter ID :</b> </label></td>
			<td><input type="text" border="10"  id="id" name="adminId" placeholder="Please Enter your user ID" required pattern="[0-9]*"/></td>
		</tr>
		<tr>
			<td align="left"><label for="pswd"><b>Enter Password : </b></label></td>
			<td><input border="10" type="password" id="pswd" name="password" placeholder="Please Enter your Password" required pattern="[a-zA-Z0-9]*"/></td>
		</tr>
		<tr>	
		<td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>