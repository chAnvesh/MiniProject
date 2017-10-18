<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ include file="UserHome.jsp" %> 
     <%-- <%@include page="CommonPage.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Change Password</title>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<script src="js/p2.js" type="text/javascript"></script>
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
		<div id="change">
		<h3>${message}</h3>
		<%-- <h3 align="right">Welcome ${cust.custName} <a href="logOut.do">Logout</a></h3> --%>
		
		<%-- <jsp:include page="CommonPage.jsp"></jsp:include> --%>
		
		<form action="changePassword.do" method="post" align="center">
			<table border="1" align="center" cellpadding="0" cellspacing="0">
				<tr align="left">
					<td><b>Enter your old Password:</b></td>
					<td><input type="password" name="oldPassword" required/></td>
				</tr>
				<tr align="left">
					<td><b>Enter your new Password:</b></td>
					<td><input type="password" name="newPassword" required/></td>
				</tr>
				<tr align="left">
					<td><b>Re-enter your new Password:</b></td>
					<td><input type="password" name="newPasswordAgain" required/></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" value="Change Password"/></td>
				</tr>
			</table>
		</form>
	
		</div>
	</body>
</html>

		
		
			