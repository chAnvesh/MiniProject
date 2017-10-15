<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Payee</title>
</head>
<body><center>

<h1>**Online Banking System**</h1><br>
<sf:form action="getAddPayee.do" modelAttribute="PayeeBean" method="Post">

 		 <label for="payeeAccountId">Enter Payee Account Number:</label>
		 <sf:input type="text" path="payeeAccountId" id="payeeAccountId"/><br/>
		
		
		<label for="payeeName">Enter Payee Name:</label>
		<sf:input type="text" path="payeeName" id="payeeName"/><br/>
		
		
		<input type="submit" value="submit"/>

</sf:form>
</center>
</body>
</html>