<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View Statements</title>
		<script src="jslib/jquery-1.12.3.js" type="text/javascript"></script>
		<script src="jslib/MyJsLib.js" type="text/javascript"></script>
	</head>
	<body>
		<h3 align="center">${message}</h3>
		<form action="viewStatement.do">
			<table border="2" align="center">
				<tr>
					<td>Account No: </td>
					<td>
						<select name="accNo" required >
							<option value="Nothing">Select Account Number</option>
							<c:forEach var="acc" items="${accList}">
								<option value="${acc.accountId}"  >${acc.accountId}</option>
						    </c:forEach>   
			    		</select>
			    	</td>
				</tr>
			    <tr>
			    	<td>Select Option: </td>
			    	<td>
			    		<input type="radio" name="option" value="Mini" required> View MiniStatement
			    		<input type="radio" name="option" value="Detailed" required> View Detailed Statement
			    	</td>
			    </tr>
			    <tr>
			    	<td><input type="submit" value="submit"></td>
			    </tr>
			</table>
		</form>
		
		<c:if test="${flag eq 1}">
			<table border="2" align="center">
			<tr>
				<td>Transaction Id</td>   <!-- CHANGE HERE  -->
				<td>Transaction Description</td>
				<td>Date of Transaction</td>
				<td>Transaction Type</td>
				<td>Transaction Amount</td>
			</tr>
			<c:forEach var="transaction" items="${transactionList}">
					<tr>    
						<td>${transaction.transactionId}</td>   <!-- CHANGE HERE  -->
						<td>${transaction.transactionDesc}</td>
						<td>${transaction.transactionDate}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.transactionAmount}</td>
					</tr>
			   </c:forEach>
			 </table>
		</c:if>
		
		<c:if test="${flag eq 2}">
			<form action = "viewFinalStatement.do">
				<table border="2" align="center">
					<tr>
						<td>Start Date: </td>
						<td><input type="Date" name="startDate"  required id="date" > </td>"
						<!-- <td><input type="date" name="startDate" required id="date"> </td> -->
					</tr>
					<tr>
						<td>End Date: </td>
						<td><input type="Date" name="endDate" required id="date"> </td>
					</tr>
					<tr>
						<td><input type="hidden" value="${accNo}" id="accNo"  name="accNo"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${flag eq 3}">
			
			<table border="2" align="center">
				<tr>
					<td>Transaction Id</td>   <!-- CHANGE HERE  -->
					<td>Transaction Description</td>
					<td>Date of Transaction</td>
					<td>Transaction Type</td>
					<td>Transaction Amount</td>
				</tr>
			<c:forEach var="transaction" items="${transactionList}">
					<tr>    
						<td>${transaction.transactionId}</td>   <!-- CHANGE HERE  -->
						<td>${transaction.transactionDesc}</td>
						<td>${transaction.transactionDate}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.transactionAmount}</td>
					</tr>
			 </c:forEach>
			 </table>
		</c:if>
		
		<a href="getHomePage.do">Go to Home</a>
	</body>
</html>