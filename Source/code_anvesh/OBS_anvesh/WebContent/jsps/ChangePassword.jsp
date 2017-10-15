<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Change Password</title>
	</head>
	<body>
	
		<h3 align="right">Welcome ${cust.custName} <a href="logOut.do">Logout</a></h3>
		
		<form action="changePassword.do" method="post">
			Enter your old Password:
			<input type="password" name="oldPassword" required/><br/>
			Enter your new Password:
			<input type="password" name="newPassword" required/><br/>
			Re-enter your new Password:
			<input type="password" name="newPasswordAgain" required/><br/>
			<input type="submit" value="Change Password"/>
		</form>
		${message}
		
	</body>
</html>