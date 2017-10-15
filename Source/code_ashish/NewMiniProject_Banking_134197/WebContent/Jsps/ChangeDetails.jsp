<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="changeUserDetails.do">
			<table border="2" align='center'>
				<tr>
					<td>Your Old Address: </td>
					<td>${oldAddress}</td>
				</tr>
				<tr>
					<td>Enter New Address: </td>
					<td><input type="textarea" name="address" required></td>
				</tr>
				<tr>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</form>
		<a href="getHomePage.do">Go to Home</a>
	</body>
</html>