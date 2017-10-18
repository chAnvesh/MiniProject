<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="HomePage.jsp" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="js/p2.js" type="text/javascript"></script>
	<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="" align="center">
	
	<div id="Login">
	<h3 align="center">${message}</h3>
	<table align="center">
			<form action="forgotPassword.do" method="post">
				<tr align="left">
				<td><b>Enter UserId:</b></td>
				<td><input type="text" name="userId" required/></td>
				</tr>
				<tr align="left">
				<td><b>Enter Your Secret Question :</b></td>
				<td><input type="text" name="secretQuestion" required/></td>
				</tr>
				<tr align="left">
				<td><b>Enter Your Secret answer :</b></td>
				<td><input type="text" name="secretAns" required/></td>
				</tr>
				<tr align="left">
				<td colspan="2" align="center"><input type="submit" value="Reset Password"/></td>
				</tr>
			</form>
		</table>
		</div>
</body>
	
</html>