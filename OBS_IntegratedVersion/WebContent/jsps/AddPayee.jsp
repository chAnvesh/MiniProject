<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ include file="UserHome.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
<title>Add Payee</title>
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="fundTransfer">
<center>
<br>
<br>
<sf:form action="getAddPayee.do" modelAttribute="PayeeBean" method="Post">
	<table>
 		 <tr><td><label for="payeeAccountId">Enter Payee Account Number:</label></td>
		<td> <sf:input type="text" path="payeeAccountId" id="payeeAccountId" required="true"/></td></tr>
		 <tr><errors name="error">${errMsg}</errors></tr>
		
		
		<tr><td><label for="payeeName">Enter Payee Name:</label></td>
		<td><sf:input type="text" path="payeeName" id="payeeName" required="true"/><br/></td></tr>
		</table><br/>
		
		<input type="submit" value="Add Payee"/>

</sf:form>
<br>

</center>
</div>
</body>
</html>