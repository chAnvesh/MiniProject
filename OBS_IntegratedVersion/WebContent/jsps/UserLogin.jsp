<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@include file="HomePage.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<script src="js/p2.js" type="text/javascript"></script>
	</head>
	<body align="center" onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
		<div id="Login">
		<h2>	${loginErr}</h2>
		<h3>${message}</h3>
			<form action="getUserHome.do" method="post">
				<table align="center">
					<tr align="left">
						<td><label for="id"><b>Enter User-ID : </b></label></td>
						<td><input type="text" border="10" id="id" name="userId" placeholder="Please Enter your user ID" required pattern="[0-9]*"/></td>
					</tr>
					<tr align="left">
						<td><label for="pswd"><b>Enter Password : </b></label></td>
						<td><input type="password" border="10" id="pswd" name="password" placeholder="Please Enter your Password" required pattern="[a-zA-Z0-9#]*"/></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Login" /></td>
						
					</tr>
					<tr align="center">
						<td colspan="2">
							<ul>
								<li><a href="getForgotPassword.do">Forgot Password</a></li>
							</ul>
						</td>
					</tr>
					
				</table>
				
			</form>
		</div>
	</body>
</html>