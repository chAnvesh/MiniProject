<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
       <%@ include file="UserHome.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/p2.js" type="text/javascript"></script>
<title>Select Account</title>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="fundTransfer">
<center>
<br><br>
<form action="getPayeeList.do">
       <b> Choose Account:</b>
        <label for="accNo"></label>
			<select name="accNo" id="accNo">
				<c:forEach items="${accList}" var="accNo">
					<option value="${accNo}">${accNo}</option>
				</c:forEach>
			</select>
			<br>
		<h3>Select Transaction Type</h3>
		
		<b>Self Transfer:</b><input type="radio" name="transType" value="SelfBank"/>
		<b>Transfer to others:</b><input type="radio" name="transType" value="InterBank"/>
		<br><br>
		<input type="submit" value="Go">
</form>		
</center>
</div>
</body>
</html>