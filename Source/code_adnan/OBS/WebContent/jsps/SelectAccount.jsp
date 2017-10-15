<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Account</title>
</head>
<body>
<center>
<h1>**Online Banking System**</h1>
<form action="getPayeeList.do">
        Choose Account:
        <label for="accNo"></label>
		<select name="accNo" id="accNo">
		<c:forEach items="${accList}" var="accNo">
		<option value="${accNo}">${accNo}</option>
		</c:forEach>
		</select>
		<br>
		<h3>Select Account Type</h3>
		Self Transfer:<input type="radio" name="transType" value="SelfBank"/>
		Inter Bank Transfer:<input type="radio" name="transType" value="InterBank"/>
		<br>
		<input type="submit" value="submit">
</form>		
</center>
</body>
</html>