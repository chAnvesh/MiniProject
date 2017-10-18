<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="UserHome.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/p2.js" type="text/javascript"></script>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">


	<center>
	<c:if test="${Request ne null}">
		<br/>
		<br/>
		<h3 align="center">Your Request has been raised Successfully with RequestID : ${Request.serviceId}</h3><br/>
	
	</c:if>
	</center><center>
	<c:if test="${requstLst ne null}">
		<br/>
		<br/>
	<table border="1" align="center" cellpadding="0" cellspacing="0">
		
		<c:forEach items="${requstLst}" var="reqs">
			<tr><td><b>Service ID : </b></td><td>${reqs.serviceId}</td></tr>
			<tr><td><b>Description : </b></td><td>${reqs.description}</td></tr>
			<tr><td><b>Account No : </b></td><td>${reqs.accountId}</td></tr>
			<tr><td><b>Request date : </b></td><td>${reqs.raisedDate}</td></tr>
			<tr><td><b>Status : </b></td><td>${reqs.status}</td></tr>	
		</c:forEach>
		</table>
	</c:if>
	</center>
</body>
</html>