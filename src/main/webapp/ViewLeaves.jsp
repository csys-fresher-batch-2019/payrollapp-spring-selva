<%@page import="com.chainsys.taskpayrollapp.model.HrModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<title>View Leaves</title>
		
		<style type="text/css">
			body
			{
				font-family: Arial, Helvetica, sans-serif;
  				background-color: #D1CDCD;
 			 	background-repeat: no-repeat;
			}
			.container{
				width:67%;
				height:auto;
 			 	display: block;
 			 	 background-color: #EFEAEA;
  				border-radius: 15px;
  				margin-left: 200px;
				margin-top: 50px;
				}
				th
				{
				color:#083056;
				}
				
		</style>	
	</head>
	<body>
	<div class = "container">
		<%
		ArrayList<HrModel> list = new ArrayList<HrModel>();
		list = (ArrayList)request.getAttribute("LeaveDetails");
		if(list.size()>0){%>
		<form action = "AcceptLeaveServlet">
		<table cellspacing="40">
			<tr>
				<th>Employee Id</th>
				<th>From Date</th>
				<th>To Date</th>
				<th>Reason</th>
				<th>Accept</th>
				<th>Reject</th>
			</tr>
				<%for(HrModel h:list){ %>
			<tr>
				<td><%=h.getID()%></td>	
				<td><%=h.getFromDate() %></td>
				<td><%=h.getToDate() %></td>
				<td><%=h.getReason()%></td>
				
				<td><button  name="Accept" id ="btn" value=<%=h.getID() %> style="background-color:#EFEAEA;color:#24C352;"><i class="fa fa-check"></i> </button></td>
				<td><button  name="Reject" id ="btn1" value=<%=h.getID() %> formaction = "RejectLeaveServlet" style="background-color:#EFEAEA;color:#DC3006;"><i class="fa fa-close"></i> </button></td>
			</tr>
				<%}}else{ %>
					<center><h3>No Leave Applications</h3></center>
				<%} %>
		</table>
		</form>
		</div>

	</body>
</html>