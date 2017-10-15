<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Forgot Password</title>
	</head>
	<body>
		<form action="forgotPassword.do" method="post">
		Enter userId:
		<input type="text" name="userId"/><br/>
		Enter Your Secret Question :
		<input type="text" name="secretQuestion"/><br/>
		Enter Your Secret answer :
		<input type="text" name="secretAnswer"/><br/>
		<input type="submit" value="Reset Password"/>
		</form>
	</body>
</html>