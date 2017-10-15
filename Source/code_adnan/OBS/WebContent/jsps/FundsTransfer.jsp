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

<form action="validatePassAndAmt.do">
        <label for="accNo"></label>
		<select name="accNo" id="accNo">
		<c:forEach items="${accList}" var="accNo">
		<option value="${accNo.payeeAccNo}">${accNo.payeeName}-${accNo.payeeAccNo}</option>
		</c:forEach>
		</select>
		Transfer  Amount<input type="text" name="transAmount" required/><br/>
		Transfer Password<input type="password" name="transPassword" required/><br/>
		<input type="submit" value="submit"/>
		
		<br/>
		<h3> <a href="getAddPayee.do">Add New Payee</a></h3>
		
</form>

</body>
</html>