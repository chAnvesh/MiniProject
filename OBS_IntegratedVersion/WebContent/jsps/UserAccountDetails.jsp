<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="UserHome.jsp" %>  
    
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/p2.js" type="text/javascript"></script>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
<script src="js/p2.js" type="text/javascript"></script>
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="viewAll">
	<h3 align="center">${message}</h3>
	<table border="1" align="center" cellpadding="0" cellspacing="0">
		<tr><th>Account Number:</th>
		<th>Account Type</th>
		<th>Available Balance</th></tr>
		<c:forEach items="${accDet}" var="ac">
			<tr><td>${ac.accountId }</td>
			<td>${ac.accountType }</td>
			<td>${ac.accountBalance }</td></tr>	
		</c:forEach>
	</table>
</div>

</body>
</html>