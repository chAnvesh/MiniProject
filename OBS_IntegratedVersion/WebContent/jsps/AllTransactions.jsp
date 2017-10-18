<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@include file="HomePage.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/p2.js" type="text/javascript"></script>
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="" align='center'>
	<div id="viewAll">
	<h2>${msg }</h2>
	<c:if test="${t eq 1}">
		<table align="center" cellpadding="1" cellspacing="1" color="grey">
				<tr>
					<th>Transaction Id</th>
					<th>Transaction Desc</th>
					<th>Transaction Type</th>
					<th>Account Id</th>
				</tr>
			<c:forEach items="${transList}" var="list">
				<tr>
					<td>${list.transactionId}</td>
					<td>${list.transactionDesc}</td>
					<td>${list.transactionType}</td>
					<td>${list.accountId}</td>
					
				</tr>	
			</c:forEach>
		</table>
	</c:if>
</div>
</body>
</html>