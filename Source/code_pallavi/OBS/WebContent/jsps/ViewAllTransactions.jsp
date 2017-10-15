<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">

${req}
<c:if test="${req eq 0}">
<form method="post" action="getViewAllTransactions.do">
			
			<input type="radio" value="1" name="req" />Daily Transactions<br/>
			
			<input type="radio" value="2" name="req" />Monthly Transaction<br/>  
			
			<input type="radio" value="3" name="req" /> Yearly Transaction<br/>
			  
			  <input type="submit" value="Submit"/>
		</form>

</c:if>


<c:if test="${req eq 1}">
	${req}
	<form action="viewDailyTransactions.do">
	
		<input type="date" name="res" required><br>
		<input type="submit" value="Submit"/>
	</form>
</c:if>

<c:if test="${req eq 2}">
	${req}
	<form action="viewMonthlyTransactions.do">
	
	<select name="month" id="month">
				<option value="JAN">January</option>
				<option value="FEB">February</option>
				<option value="MAR">March</option>
				<option value="APR">April</option>
				<option value="MAY">May</option>
				<option value="JUN">June</option>
				<option value="JUL">July</option>
				<option value="AUG">August</option>
				<option value="SEP">September</option>
				<option value="OCT">October</option>
				<option value="NOV">November</option>
				<option value="DEC">December</option>
	</select>
	
		
		<select name="year" id="year">
		<c:forEach items="${yearList}" var="list">
					<option value=${list }>${list }</option>
		</c:forEach>
		</select>
		
		
		<input type="submit" value="Submit"/>
	</form>
</c:if>

<c:if test="${req eq 3}">
	${req}
	<form action="viewYearlyTransactions.do">
	
		<select name="year" id="year">
		<c:forEach items="${yearList}" var="list">
					<option value=${list }>${list }</option>
		</c:forEach>
		</select>
		
		<input type="submit" value="Submit"/>
	</form>
</c:if>

</body>
</html>