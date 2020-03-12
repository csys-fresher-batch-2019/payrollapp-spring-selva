<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply Leave</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate : 0
		});
		$("#datepicer").datepicker({
			minDate : 0
		});
	});
</script>
<script type="text/javascript">
	function validateForm() {
		var x = document.forms["leaveform"]["fromdate"].value;
		var y = document.forms["leaveform"]["todate"].value;
		var z = document.forms["leaveform"]["Reason"].value;
		if (x == "") {
			alert("From date must be filled out");
			return false;
		}
		if (y == "") {
			alert("To date must be filled out");
			return false;
		}
		if (z == "") {
			alert("Reason must be filled out");
			return false;
		}
	}
</script>
<style>
label {
	width: 60%;
	padding: 12px 0px 0px 0px;
	display: inline-block;
	margin-left: 150px;
	font-size: 120%;
}

input[type=text] {
	width: 60%;
	padding: 12px;
	border: 5px solid #ccc;
	border-radius: 5px;
	margin-left: 150px;
	margin-top: 20px;
}

input[type=submit] {
	width: 20%;
	padding: 12px;
	border: 5px solid #ccc;
	border-radius: 5px;
	margin-left: 150px;
	margin-top: 20px;
}
</style>
</head>
<body>
	<center>
		<h2>LEAVE APPLICATION</h2>
	</center>
	<form name="leaveform" action="LeaveformServlet"
		onsubmit="return validateForm()">
		<label for="fdate">From_Date</label> <input type="text"
			id="datepicker" name="fromdate" placeholder="Select from date">
		<label for="fdate">To_Date</label> <input type="text" id="datepicer"
			name="todate" placeholder="Select to date"> <label
			for="Reason">Reason</label> <input type="text" id="reason"
			name="Reason" placeholder="Enter the reason"> <input
			type="submit" value="Submit">
	</form>
</body>
</html>