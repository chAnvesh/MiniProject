<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ include file="UserHome.jsp" %>
     
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
<script src="js/p2.js" type="text/javascript"></script>
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="trackrequest">
<center><table>
		<form method="post" action="getTrackRequest.do">
			<tr><td>
			<input type="radio" value="reqId" name="req" /> Request ID</td>
			<td>
			<input type="radio" value="accNm" name="req" /> Account Number</td></tr>
			
			  <!-- <input path="description" id="desc" type="text"/><br/> -->
			 <tr><td colspan="2"> 
			  <input type="submit" value="Submit"/></td></tr>
		</form>
</table>
</center>
<%-- 
 <c:if test="${res eq null}">

		<form method="post" action="getTrackRequest.do">
			
			<input type="radio" value="reqId" name="req" /> Request ID<br/>
			
			<input type="radio" value="accNm" name="req" /> Account Number<br/>  
			
			  <input path="description" id="desc" type="text"/><br/>
			  
			  <input type="submit" value="Submit"/>
		</form>

</c:if>
		
<c:if test="${requst eq reqType}">

	<c:set value="" var="requst" value="reqId" scope="session"></c:set>
	<form method="post" action="trackRequest.do">
			
			Enter Your Request ID: <br/>
			
			<input type="text" name="id" required/><br/>
			  
			  <input type="submit" value="Track Request"/>
		</form>

</c:if>		

		
<c:if test="${requst eq accType}">
	
	<c:set value="" var="requst" value="accNm" scope="session"></c:set>
	<form method="post" action="trackRequest.do">
			
			Enter Your Account Number: <br/>
			
			<input type="text" name="id" required/><br/>
			  
			  <input type="submit" value="Track Request"/>
		</form>

</c:if>		

		<sf:radiobutton path="" value="fdfdfg" id="${fgh}"/>
		<sf:form method="post" modelAttribute="raiseReq" action="raiseRequest.do">
			<label for="accNm">Enter Name:</label>
			  <sf:input path="accNo" id="accNm" type="text"/><br/>
			  
			  <label for="desc">Enter Salary:</label>
			  <sf:input path="description" id="desc" type="text"/><br/>
			  
			  <input type="submit" value="Submit Request"/>
		</sf:form>
 --%>
 </div>
</body>
</html>