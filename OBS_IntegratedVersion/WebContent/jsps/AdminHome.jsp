<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
      <%@include file="HeaderPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<script src="js/p2.js" type="text/javascript"></script>
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="userHeader">
			<h3 align="right">Welcome Administrator</h3>
	</div>
	 
	<div id="userHome">
	<ul class="cl-menu">
	<li><a href="getHomePage.do">Home</a></li>
	<li><a href="getAddNewAccount.do">Add New Account</a></li>
	<li id="addAcc">
	 <a href="#">View All Transactions</a>
	 
		<ul >
			<li><a href="getViewDailyPage.do?test=0">View Daily transactions</a></li>
			<li><a href="getViewMonthlyPage.do?test=0">View Monthly transactions</a></li>
			<li><a href="getViewYearlyPage.do?test=0">View Yearly transactions</a></li>
			
			
		</ul>
	</li>
	<li><a href="getHomePage.do">Logout</a></li>
	
	</ul>
	</div>
	</body>
</html>