<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="AdminHome.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/p2.js" type="text/javascript"></script>
</head>
<body align="center" onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">

<c:if test="${rest eq 0}">
<br>
<br>
<form action="getViewDailyPage.do">

	<b>Select Date:</b> <input type="date" name="res" max="${sysdate}"  required placeholder="${sysdate}"> &nbsp
	
	<input type="submit" value="Go"/>
</form>

</c:if>
	
<c:if test="${rest eq 1}">
<br>
<br>
<form action="getViewMonthlyPage.do">

			 <b>Select Month:</b>  <select name="month" id="month"><br>
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
			
			<b>Select Year:</b>  <select name="year" id="year" ><br>
							<c:forEach items="${yearList}" var="list">
								<option value=${list }>${list }</option>
							</c:forEach>
						  </select>
	<input type="submit" value="Go"/>

</form>

</c:if>


	<c:if test="${rest eq 2}">
	<br>
<br>
		<form action="getViewYearlyPage.do">
	
					Select Year: <select name="year" id="year" > &nbsp
										<c:forEach items="${yearList}" var="list">
											<option value=${list }>${list }</option>
										</c:forEach>
								 </select>
					
					<input type="submit" value="Go"/>
		</form>
		
	</c:if>

<h2>${msg}</h2>

	<c:if test="${t eq 1}">
		<table align="center" cellpadding="0" cellspacing="0" bgcolor="grey" border="0" >
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

</body>
</html>