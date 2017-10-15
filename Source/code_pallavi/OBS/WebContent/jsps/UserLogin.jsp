<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME TO USER LOGIN PAGE</title>
</head>
<body>

<form action="getUserHome.do" method="post">

	<label for="id">User ID : </label>
	<input type="text" id="id" name="userid" placeholder="Please Enter your user ID" required pattern="[0-9]*"/><br/>
	
	<label for="pswd">User ID : </label>
	<input type="password" id="pswd" name="pass" placeholder="Please Enter your Password" required pattern="[a-zA-Z0-9]*"/><br/>

	<input type="submit" value="Login" />

</form>

</body>
</html>