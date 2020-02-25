<%@page import="com.chainsys.payrollapp.model.AdminModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<style type="text/css">
			body
			{
				font-family: Arial, Helvetica, sans-serif;
  				background-color: #D1CDCD;
 			 	background-repeat: no-repeat;
			}
			.container{
				width:80%;
				height:auto;
 			 	display: block;
 			 	 background-color: #EFEAEA;
  				border-radius: 15px;
  				margin-left: 150px;
				margin-top: 50px;
				}
				th
				{
				color:#083056;
				}
				
		</style>	
</head>
<body>
<center><h3>Employee Details</h3></center>
<div class = "container">
		<%
		ArrayList<AdminModel> list = new ArrayList<AdminModel>();
		list = (ArrayList)request.getAttribute("EmployeeDetails");
		if(list.size()>0){%>
		<form action = "AcceptLeaveServlet">
		<table cellspacing="40">
			<tr>
				<th>Employee Id</th>
				<th>Employee name</th>
				<th>Designation</th>
				<th>Email Address</th>
				<th>Pan Number</th>
			</tr>
				<%for(AdminModel h:list){ %>
			<tr>
				<td><%=h.getEmpId()%></td>	
				<td><%=h.getEmpName() %></td>
				<td><%=h.getDesignation()%></td>
				<td><%=h.getEmail() %></td>
				<td><%=h.getPan()%></td>
			</tr>
				<%}} %>
		</table>
		</form>
	</div>
	</body>
</html>