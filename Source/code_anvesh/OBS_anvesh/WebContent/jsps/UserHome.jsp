<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1>Welcome to Capgemini Banking Services</h1>
</head>
<body>

<h3 align="right">Welcome ${cust.custName} <a href="logOut.do">Logout</a></h3>

<a href="getViewStatement.do">View Mini/Detailed Statements</a><br>
<a href="getChangeDetails.do">Change Communication Details</a><br>
<a href="getRaiseRequest.do">Raise Service Requests</a><br>
<a href="getTrackRequest.do?req=anvesh">Track your Requests</a><br>
<a href="getFundsTransfer.do">Funds Transfer</a><br>
<a href="getChangePassword.do">Change Password</a>

</body>
</html>