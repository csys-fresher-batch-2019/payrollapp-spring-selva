<%@page import="com.chainsys.taskpayrollapp.model.HrModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>View Leaves</title>

<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #D1CDCD;
	background-repeat: no-repeat;
}

.container {
	width: 67%;
	height: auto;
	display: block;
	background-color: #EFEAEA;
	border-radius: 15px;
	margin-left: 200px;
	margin-top: 30px;
}

th {
	color: #083056;
}
</style>
</head>
<body>
<center><p><font size=6>Pending Leave Applications</font></p></center>
	<div class="container">
		<form action="AcceptLeaveServlet">
			<c:choose>
				<c:when test="${empty LeaveDetails}">
					<center>
						<h3>No Leave Applications</h3>
					</center>
				</c:when>
				<c:otherwise>
					<table cellspacing="40">
						<tr>
							<th>Employee Id</th>
							<th>From Date</th>
							<th>To Date</th>
							<th>Reason</th>
							<th>Accept</th>
							<th>Reject</th>
						</tr>
						<c:forEach items="${LeaveDetails}" var="l">
							<tr>
								<td>${l.empId}</td>
								<td>${l.fromDate}</td>
								<td>${l.toDate}</td>
								<td>${l.reasonForLeave}</td>

								<td><button name="Accept" id="btn" value="${l.empId}"
										style="background-color: #EFEAEA; color: #24C352;">
										<i class="fa fa-check"></i>
									</button></td>
								<td><button name="Reject" id="btn1" value="${l.empId}"
										formaction="RejectLeaveServlet"
										style="background-color: #EFEAEA; color: #DC3006;">
										<i class="fa fa-close"></i>
									</button></td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</form>
	</div>

</body>
</html>