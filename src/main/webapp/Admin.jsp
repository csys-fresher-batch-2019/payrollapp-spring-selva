<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
			$(document).ready(function(){
  				$("#d").click(function(){
    				$("#div1").load("demo.jsp");
  				});
  				$("#a").click(function(){
    				$("#div1").load("AddEmployee.jsp");
  				});
  				$("#b").click(function(){
    				$("#div1").load("RemoveDetail.jsp");
  				});
  				$("#e").click(function(){
    				$("#div1").load("Swipe.jsp");
  				});
  				$("#c").click(function(){
    				$("#div1").load("ResetPass.jsp");
  				});
			});
		</script>
	
		<title>Admin</title>
		<jsp:include page="headerNav.jsp" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<style>
			body
			{
				background-color: lightblue;
  				background-repeat: no-repeat;
			}
			.act button
			{
 				background-color: #0C2844; /* Green background */
  				border: 0px solid blue ; /* Green border */
  				color: #FFFFFF; /* White text */
  				padding: 10px 24px; /* Some padding */
  				cursor: pointer; /* Pointer/hand icon */
			  	width: 100%; /* Set a width if needed */
			  	display: block; /* Make the buttons appear below each other */
			  	margin-left: 10px;
			  	margin-top: 20px;
			}
			.act button:not(:last-child) 
			{
  				border-bottom: none; /* Prevent double borders */
			}
			/* Add a background color on hover */
			.act button:hover {
		  		background-color: #1F476D;
			}
			.container {
				display: block;
			  	border-radius: 0px 0px 0px 0px;
			  	background-color: #0C2844;
			  	overflow: auto;
			  	width: 25%;
				margin-left: 0px;
				margin-top: 0px;
				float:left;
				height: 780px;
			}	
			.box{
				display: block;
				height : auto;
				width :100%;	
			}
			.element
			{
				display: inline;
				height : auto;
				width :100%;
				background-color: lightblue;	
			}
			#div1{
				display: block;
			  	width: 75%;
				float:right;
				background-color: lightblue;
			}
		</style>
	</head>
<%
	int EmpId = (int)session.getAttribute("value");
	String desg =(String)session.getAttribute("desg");
%>
	<body>
	<div class = "element">
		<div id = "div1">
		<%
    		String result =(String)request.getParameter("result");
    		if(result != null){
    			out.println("<center><p><font size=6"+">" + result + "</font></p></center>");
    		}
    		%>
		</div>
		<div class = "container">
			<div class = "box">
				<center><i class="fa fa-user-circle-o" style="font-size:120px;color:#FFFFFF"></i>
					<h4 style="color:#FFFFFF"><%=desg %></h4>
					<h5 style="color:#FFFFFF"><%=EmpId %></h5>
				</center>
			</div>
			<div class="act">
				<button id = "a">Add Employee Details</button>	
				<button id = "b">Delete Employee Details</button>
				<form action = "CalcLopServlet">
				<button type = "submit">Calculate LOP</button>
				<button type = "submit" formaction = "DisplayServlet">View Employee Details</button>
				</form>
				<button id = "c">Reset Password</button>
				<button id = "d">Apply Leave</button>
				<button><a href = "salary<%=EmpId%>.pdf" >View Pay Slip</a></button>
				<button id = "e">Swipe</button>
			</div>
		</div>
	</div>
	</body>
</html>