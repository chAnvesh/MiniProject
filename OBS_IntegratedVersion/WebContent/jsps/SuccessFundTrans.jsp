<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ include file="UserHome.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<script src="js/p2.js" type="text/javascript"></script>
<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div id="fundTransfer">
<h1 align="center">Successful Transaction</h1><br>
<h2 align="center">Transaction Id: ${transId}</h2>

<div id="fundTransfer">
</body>
</html>