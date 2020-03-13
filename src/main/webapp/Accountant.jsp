<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$("#d").click(function() {
			$("#div1").load("demo.jsp");
		});
		$("#e").click(function() {
			$("#div1").load("Swipe.jsp");
		});
	});
</script>
<title>ACCOUNTANT</title>
<jsp:include page="headerNav.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	background-color: lightblue;
	background-repeat: no-repeat;
}

.uyt button {
	background-color: #0C2844; /* Green background */
	border: 0px solid blue; /* Green border */
	color: #FFFFFF; /* White text */
	padding: 10px 24px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 100%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
	margin-left: 10px;
	margin-top: 20px;
}

.btn-group


 


button




:not


 


(
:last-child


 


)
{
border-bottom




:


 


none




; /* Prevent double borders */
}
.btn-group button:hover {
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
	float: right;
	height: 700px;
}

.box {
	display: block;
	height: auto;
	width: 100%;
}

.element {
	display: inline;
	height: auto;
	width: 100%;
	background-color: #2F6EAA;
}

#div1 {
	display: block;
	width: 75%;
	float: left;
	background-color: lightblue;
}
</style>
</head>
<body>
	<div class="element">
		<div id="div1">
			<c:if test="${not empty param.result}">
				<center>
					<p>
						<font size=6> ${param.result}</font>
					</p>
				</center>
			</c:if>
		</div>
		<div class="container">
			<div class="box">
				<center>
					<i class="fa fa-user-circle-o"
						style="font-size: 120px; color: #FFFFFF"></i>
					<h4 style="color: #FFFFFF">${designation}</h4>
					<h5 style="color: #FFFFFF">${value}</h5>
				</center>
			</div>
			<div class="uyt">
				<form action="PFcalcServlet">
					<button>Calculate PF</button>
					<button type="submit" value="Calculate Increment"
						formaction="IncrementCalcSalary">Calculate Increment</button>
					<button type="submit" value="Mark Attendance"
						formaction="AttendanceServlet">Mark Attendance</button>
					<button type="submit" value="Calculate Salary"
						formaction="SalaryCalcServlet">Calculate Salary</button>
					<button type="submit" value="Generate Payslip"
						formaction="PayslipServlet">Generate Payslip</button>
				</form>
				<button id="d">Apply Leave</button>
				<button id="e">Swipe</button>
				<button>
					<a href="salary${value}.pdf">View Pay Slip</a>
				</button>
			</div>
		</div>
	</div>
</body>
</html>
