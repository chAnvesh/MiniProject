<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ include file="UserHome.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/p2.js" type="text/javascript"></script>
<title>Insert title here</title>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="fundTransfer">
<center>
<br><br>
<c:if test="${flag eq 0}">
<h3>No Multiple Accounts are available for Transaction</h3>
</c:if>
<c:if test="${flag eq 1}">
<form action="validatePassAndAmt.do">
	<table border="1" align="center" cellpadding="0" cellspacing="0">
			<tr align="left"><td>Select Account For Transaction</td>
	        <td><label for="accNo"></label>
				<select name="accNo" id="accNo">
					<c:forEach items="${payeeList}" var="accNo">
						<c:if test="${accNo ne selfAccNo}">
							<option value="${accNo}">${accNo}</option>
						</c:if>
					</c:forEach>
				</select></td></tr>
			<tr align="left"><td><b>Transfer  Amount</b></td><td><input type="text" name="transAmount" pattern="[0-9]{1,15}" title="Please enter valid amount" required/></td></tr>
			<tr align="left"><td><b>Transfer Password</b></td><td><input type="password" name="transPassword" required/></td></tr>
			<tr align="left"><td><b>Description </b></td><td><input type="text" name="desc" required/></td></tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
	</table>
	
	
</form>
</c:if>
<br>

</center>
</div>
</body>
</html>