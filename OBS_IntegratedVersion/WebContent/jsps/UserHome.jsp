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
			<h3 align="center">Welcome ${custDetails.custName} &nbsp</h3>
		</div>
		<div id="userHome">
		
			<%-- <h3 align="right">Welcome ${custDetails.custName} &nbsp <br></h3> --%>
			<ul class="cl-menu">
				<li><a href="getUserHomeFinal.do">Home</a></li>
				<li><a href="getViewStatement.do">View Mini/Detailed Statements</a></li>
				<li><a href="getChangeDetails.do">Change Communication Details</a></li>
				<li><a href="getRaiseRequest.do">Raise Service Requests</a></li>
				
				<li id="addAcc">
					<a href="#">Track your Requests</a>
					<ul>
						<li><a href="getTrackByAcc.do">Track By Acc</a></li>
						<li><a href="getTrackById.do">Track By Id</a></li>
					</ul>
				</li>
				<li><a href="getFundsTransfer.do">Funds Transfer</a></li>
				<li><a href="getChangePassword.do">Change Password</a></li>
				<li><a href="logOut.do">Logout</a></li>
			</ul>
		</div>
	</body>
</html>