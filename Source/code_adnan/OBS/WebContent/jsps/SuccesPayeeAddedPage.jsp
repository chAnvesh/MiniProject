<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>Payee Added Successfully</h2><br>
<h3>**Details**</h3>
<br>
Name:${payeeBean.payeeName}
<br>
Payee Account Number:${payeeBean.payeeAccountId}
<br><br>
<a href="getFundsTransfer.do">Back To Transaction</a>
</center>
</body>
</html>