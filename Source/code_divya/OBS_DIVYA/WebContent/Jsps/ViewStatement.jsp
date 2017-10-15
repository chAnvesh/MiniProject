<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View Statements</title>
	</head>
	<body>
		<form action="">
			<table>
				<tr>
					<td>Account No: </td>
				</tr>
				<c:forEach var="acc" items="${accList}">
					<tr>    
						<td><input type="radio" name="accNo" value="${acc.accNo}"></td>   <!-- CHANGE HERE  -->
					</tr>
			    </c:forEach>
			    
			    <tr>
			    	<td>Select Option</td>
			    	<td>
			    		<input type="radio" name="option"> View MiniStatement
			    		<input type="radio" name="option"> View Detailed Statement
			    	</td>
			    </tr>
			    
			</table>
		</form>
		
		<c:if test="${flag eq 1}">
			<c:forEach var="transaction" items="${th}">
					<tr>    
						<td>${transaction.transactionId}</td>   <!-- CHANGE HERE  -->
						<td>${transaction.transactionDesc}</td>
						<td>${transaction.transactionDate}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.transactionAmount}</td>
					</tr>
			    </c:forEach>
		</c:if>
		
		<c:if test="${flag eq 2}">
			<form>
				<table>
					<tr>
						<td>Start Date: </td>
						<td><input type="date" name="startDate"> </td>
					</tr>
					<tr>
						<td>End Date: </td>
						<td><input type="date" name="endDate"> </td>
					</tr>
					<tr>
						<input type="submit" value="Submit">
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${flag eq 3}">
			<c:forEach var="transaction" items="${th}">
					<tr>    
						<td>${transaction.transactionId}</td>   <!-- CHANGE HERE  -->
						<td>${transaction.transactionDesc}</td>
						<td>${transaction.transactionDate}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.transactionAmount}</td>
					</tr>
			    </c:forEach>
		</c:if>
		
	</body>
</html>