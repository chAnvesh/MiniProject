<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="UserHome.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View Statements</title>
		
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
	
	<script src="js/p2.js" type="text/javascript"></script>
	
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="wrapper">

	<div id="viewStatement">
		
		<form action="viewStatement.do" >
			<table border="1" align="center" cellpadding="0" cellspacing="0">
				<tr align="left">
					<td><b>Account No: </b></td>
					<td>
						<select name="accNo" required readonly="readonly">
							<!-- <option value="Nothing">Select Account Number</option> -->
						
							<option value="Nothing" selected>Select Account Number</option>
						
							<c:forEach var="acc" items="${accList}">
							<c:if test="${accNo ne null}">
							<c:if test="${accNo eq acc.accountId}">
								<option value="${acc.accountId}"  selected>${acc.accountId}</option>
							</c:if>
							<c:if test="${accNo ne acc.accountId}">
								<option value="${acc.accountId}"  >${acc.accountId}</option>
							</c:if>
							</c:if>
							<c:if test="${accNo eq null}">
							<option value="${acc.accountId}"  >${acc.accountId}</option>
							</c:if>
						    </c:forEach>   
			    		</select>
			    	
			    	</td>
				</tr>
			    <tr align="left">
			    	<td><b>Select Option:</b> </td>
			    	<td>
			    		<c:if test="${flag eq 0}">
			    		<input type="radio" name="option" value="Mini" required > View MiniStatement
			    		<input type="radio" name="option" value="Detailed" required > View Detailed Statement
			    		</c:if>
			    		
			    		<c:if test="${flag eq 1}">
			    		<input type="radio" name="option" value="Mini" required checked> View MiniStatement
			    		<input type="radio" name="option" value="Detailed" required > View Detailed Statement
			    		</c:if>
			    		
			    		<c:if test="${flag eq 2 ||  flag eq 3}">
			    		<input type="radio" name="option" value="Mini" required > View MiniStatement
			    		<input type="radio" name="option" value="Detailed" required checked> View Detailed Statement
			    		</c:if>
			    	</td>
			    </tr>
			    <tr align="center">
			    	<td colspan="2"><input type="submit" value="submit"></td>
			    </tr>
			</table>
		</form>
		
		<c:if test="${flag eq 1 && marker eq 1}">
		<br/>
			<br/>
			<table border="1" align="center" cellpadding="0" cellspacing="0">
			<tr align="left">
				<td>Transaction Id</td>   <!-- CHANGE HERE  -->
				<td>Transaction Description</td>
				<td>Date of Transaction</td>
				<td>Transaction Type</td>
				<td>Transaction Amount</td>
			</tr>
			<c:forEach var="transaction" items="${transactionList}">
					<tr align="left">    
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
					<tr align="left">
						<td><b>Start Date: </b></td>
						<td><input type="Date" name="startDate"  required id="date" > </td>"
						<!-- <td><input type="date" name="startDate" required id="date"> </td> -->
					</tr>
					<tr>
						<td><b>End Date: </b></td>
						<td><input type="Date" name="endDate" required id="date"> </td>
					</tr align="left">
					<tr>
						<td><input type="hidden" value="${accNo}" id="accNo"  name="accNo"></td>
					</tr>
					<tr align="center">
						<td colspan="2"><input type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${flag eq 3}">
			<br/>
			<br/>
			<table border="1" align="center" cellpadding="0" cellspacing="0">
			<tr align="left">
				<td>Transaction Id</td>   <!-- CHANGE HERE  -->
				<td>Transaction Description</td>
				<td>Date of Transaction</td>
				<td>Transaction Type</td>
				<td>Transaction Amount</td>
			</tr>
			<c:forEach var="transaction" items="${transactionList}">
					<tr align="left">    
						<td>${transaction.transactionId}</td>   <!-- CHANGE HERE  -->
						<td>${transaction.transactionDesc}</td>
						<td>${transaction.transactionDate}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.transactionAmount}</td>
					</tr>
			   </c:forEach>
			 </table>
		</c:if>
		<h3 align="center">${message}</h3>
		
		</div>
		</div>
	</body>
</html>