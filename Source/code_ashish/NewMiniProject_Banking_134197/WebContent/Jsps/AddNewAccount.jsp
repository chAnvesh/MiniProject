<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="addNewAccount.do">
			<table>
				<tr>
					<td>Type ofAccount: </td>
					<td><input type="radio" name="typeAcc" value="Savings" required> Savings</td>
					<td><input type="radio" name="typeAcc" value="Salary" required> Salary</td>
				</tr>
				<tr>
					<td>Opening Balance: </td>
					<td><input type="number" name="opBal" required  min="1000"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create Acc"></td>
				</tr>
			</table>
		</form>
		<a href="getHomePage.do">Go to Home</a>
	</body>
</html>