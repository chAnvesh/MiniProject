<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="UserHome.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="/OBS_IntegratedVersion/css/style.css" rel="stylesheet" type="text/css">
		<script src="js/p2.js" type="text/javascript"></script>
	</head>
	<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
		<div id="changeDetails">
			<form action="changeUserDetails.do">
				<table border="1" align='center' cellpadding="0" cellspacing="0">
					<tr align="left">
						<td><b>Your Old Address:</b> </td>
						<td>${oldAddress}</td>
					</tr>
					<tr align="left">
						<td><b>Enter New Address: </b></td>
						<td><input type="textarea" name="address" required cols="20" rows="20" minlength="5"></td>
					</tr>
					<tr align="center">
						<td colspan="2"><input type="submit" value="Update"></td>
					</tr>
				</table>
			</form>
			
		</div>
	</body>
</html>