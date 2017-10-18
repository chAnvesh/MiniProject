<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ include file="AdminHome.jsp" %>
     <%--  <%@ include file="Home.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script src="js/p2.js" type="text/javascript"></script>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div id="addnewAccount">
	<h3>${message}</h3>
		<form action="addNewAccount.do">
			<table align="center">
				<tr align="left">
					<td><b>Enter UserId:</b></td>
					<td>
						<select name="userId">
							<option value="nothing">Select UserId</option> 
							<c:forEach var="userId" items="${userList}">
								<option value="${userId}">${userId}</option>
						    </c:forEach>   
						</select>
					</td>
				</tr>
				<tr align="left">
					<td><b>Type ofAccount:</b></td>
					<td><input type="radio" name="typeAcc" value="Current" required> Current</td>
					<td><input type="radio" name="typeAcc" value="Salary" required> Salary</td>
				</tr>
				<tr align="left">
					<td><b>Opening Balance:</b></td>
					<td><input type="number" name="opBal" required  min="0"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" value="Create Acc"></td>
				</tr>
			</table>
		</form>
		
		</div>
		
		
	</body>
</html>