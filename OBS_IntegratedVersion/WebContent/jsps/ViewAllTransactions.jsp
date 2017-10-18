<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@include file="header.html" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ include file="AdminHome.jsp" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
	<script src="js/p2.js" type="text/javascript"></script>
</head>
<body align="center" onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="viewAll">
<form method="post" action="getViewAllTransactions.do" align="center">
			
			<input type="radio" value="1" name="req" />Daily Transactions &nbsp
			
			<input type="radio" value="2" name="req" />Monthly Transaction &nbsp
			
			<input type="radio" value="3" name="req" /> Yearly Transaction <br>

		<c:if test="${req eq 1}">
				<input type="date" name="res" max="2017-10-17" required><br>
				
		</c:if>

		<c:if test="${req eq 2}">
			
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
			
			<select name="year" id="year" >
				<c:forEach items="${yearList}" var="list">
							<option value=${list }>${list }</option>
				</c:forEach>
			</select>
				
		</c:if>

		<c:if test="${req eq 3}">
			
				<select name="year" id="year">
				<c:forEach items="${yearList}" var="list">
							<option value=${list }>${list }</option>
				</c:forEach>
				</select>
				
		</c:if>

	  <input type="submit" value="Submit"/>
</form>

	<c:if test="${t eq 0}">
		<h2>${msg }</h2>
	</c:if>

	<c:if test="${t eq 1}">
		<table align="center" cellpadding="1" cellspacing="1" bgcolor="grey" border="1" >
				<tr>
					<th>Transaction Id</th>
					<th>Transaction Description</th>
					<th>Transaction Type</th>
					<th>Account Id</th>
				</tr>
			<c:forEach items="${transList}" var="list">
				<tr>
					<td>${list.transactionId}</td>
					<td>${list.transactionDesc}</td>
					<td>${list.transactionType}</td>
					<td>${list.accountId}</td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>
</div>
</body>
</html>