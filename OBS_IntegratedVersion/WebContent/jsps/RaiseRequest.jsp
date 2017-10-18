<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ include file="UserHome.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<script src="js/p2.js" type="text/javascript"></script>
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="raiseRequest">
		<center>	
	<table align="center">
		<sf:form method="post" modelAttribute="raiseReq" action="raiseRequest.do">
		<tr><td>	<label for="accNm">Select Account:</label></td>
				<td> <sf:select path="accountId" name="accNm" id="accNm" required="true">
					<c:forEach items="${reqList}" var="acc">
						<option value="${acc}">${acc}</option>
					</c:forEach>
					</sf:select></td></tr>
			  
			<tr><td>  <label for="desc">Enter Service-Request Description:</label></td>
			  <td><sf:input path="description" id="desc" type="text" required="true"/></td></tr><br>
			 <tr><td colspan="2"> 
			  <input type="submit" value="Raise Request"/></td></tr>
		</sf:form>
		</table>
	</center>
	</div>
	</body>
</html>