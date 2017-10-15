<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<table>
	<tr>
		<th>Account Number</th>
		<th>Account Type</th>
		<th>Balance</th>	
	</tr>
<c:forEach items="${accList}" var="acc" >
	<tr>
		<td>${acc.accountId }</t>
		<th>${acc.accountType }</th>
		<th>${acc.accountBalance }</th>	
	</tr>



</c:forEach>

</table>


</body>
</html>