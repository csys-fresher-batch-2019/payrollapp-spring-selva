<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
</style>
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
</head>
<body>
	<div class="center">
		<div class="container">
			<form name="leaveform" action="" onsubmit="return validateForm()"
				method="post">
				<div class="row">
					<div class="col-25">
						<label for="fdate">From_Date</label>
					</div>
					<div class="col-75">
						<input type="text" id="datepicker" name="fromdate"
							placeholder="Select from date">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="fdate">To_Date</label>
					</div>
					<div class="col-75">
						<input type="text" id="datepicer" name="todate"
							placeholder="Select to date">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="Reason">Reason</label>
					</div>
					<div class="col-75">
						<input type="text" id="reason" name="Reason"
							placeholder="Enter the reason">
					</div>
				</div>
				<div class="row">
					<input type="submit" value="Submit">
				</div>
			</form>
		</div>
	</div>
</body>
</html>