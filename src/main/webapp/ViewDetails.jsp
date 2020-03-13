<%@page import="com.chainsys.taskpayrollapp.model.AdminModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #D1CDCD;
	background-repeat: no-repeat;
}

.container {
	width: 75%;
	height: auto;
	display: block;
	background-color: #EFEAEA;
	border-radius: 15px;
	margin-left: 150px;
	margin-top: 50px;
}

th {
	color: #083056;
}
</style>
</head>
<body>
	<center>
		<h3>Employee Details</h3>
	</center>
	<div class="container">
		<form action="AcceptLeaveServlet">
			<c:choose>
				<c:when test="${empty EmployeeDetails}">
					<center>
						<h3>No Details Found</h3>
					</center>
				</c:when>
				<c:otherwise>
					<table cellspacing="40">
						<tr>
							<th>Employee Id</th>
							<th>Employee name</th>
							<th>Designation</th>
							<th>Email Address</th>
							<th>Pan Number</th>
						</tr>
						<c:forEach items="${EmployeeDetails}" var="e">
							<tr>
								<td>${e.empId}</td>
								<td>${e.empName}</td>
								<td>${e.designation}</td>
								<td>${e.email}</td>
								<td>${e.panNumber}</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</body>
</html>