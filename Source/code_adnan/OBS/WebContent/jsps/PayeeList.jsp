<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>**Online Banking System**</h1><br>
<form action="validatePassAndAmt.do">
		select Account For Transaction
        <label for="accNo"></label>
		<select name="accNo" id="accNo">
		<c:forEach items="${payeeList}" var="accNo">
		<c:if test="${accNo ne selfAccNo}">
		<option value="${accNo}">${accNo}</option>
		</c:if>
		</c:forEach>
		</select><br/>
		Transfer  Amount<input type="text" name="transAmount" required/><br/><br/>
		Transfer Password<input type="password" name="transPassword" required/><br/><br/>
		Description <input type="text" name="desc" required/><br/><br/>
		<input type="submit" value="submit"/>
</form>
</center>
</body>
</html>