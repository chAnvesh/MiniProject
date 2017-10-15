<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${Request ne null}">
		
		Your Request has been raised Successfully with RequestID : ${Request.serviceId}<br/>
	
	</c:if>
	<c:if test="${requstLst ne null}">
	<table>
		<c:forEach items="requstLst" var="reqs">
			<tr><td>${reqs.serviceId}</td></tr>
			<tr><td>${reqs.description}</td></tr>
			<tr><td>${reqs.accNo}</td></tr>
			<tr><td>${reqs.raisedDate}</td></tr>	
			<tr><td>${reqs.status}</td></tr>		
		</c:forEach>
		</table>
	</c:if>
	
</body>
</html>