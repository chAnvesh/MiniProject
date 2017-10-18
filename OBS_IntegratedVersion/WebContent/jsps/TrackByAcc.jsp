<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div id="trackrequest">
<center>
<table>
<form method="post" action="trackRequestByAcc.do">
			<tr><td>
			<b>Enter Your Account Number:</b>
			</td></tr><tr><td>
			<!-- <input type="text" name="acc" required/> -->
			
			<select name="acc" id="acc">
			<c:forEach items="${accLst}" var="acc">
				<option value="${acc}">${acc}</option>
			</c:forEach>
			</select>
			
			</td></tr>
			  <tr><td>
			  <input type="submit" value="Track Request"/></td></tr>
		</form>
		</table>
</center>
</div>
</body>
</html>