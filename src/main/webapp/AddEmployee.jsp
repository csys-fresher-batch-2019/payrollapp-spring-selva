<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Employee Details</title>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
			function validate()
	      	{ 
				var x = document.forms["details"]["Name"].value;
		  		var y = document.forms["details"]["id"].value;
	      		if(!isNaN(x))
	     		{
	     			alert('Please input alphabet characters only');
	      			return false;
	      		}
	      	}
		</script>
		<style>
			input[type=text],input[type=password],input[type=email],input[type=submit],select {
				width: 60%;
			  	padding: 12px 0px 0px 0px;;
			  	border: 5px solid #ccc;
			  	border-radius: 5px;
			  	margin-left: 150px;
			  	margin-top:20px;
			}
			label {
				width: 60%;
  				padding: 12px 0px 0px 0px;
  				display: inline-block;
  				margin-left: 150px;
  				font-size: 120%;
			}
		</style>
	</head>
<body>
	<form action = "AddDetailServlet" name = "details" onsubmit="return validate()">
		<label for="Reason">Name</label>
		<input type = "text" name = "Name" placeholder = "Enter the Name" required autofocus />
		<label for="Reason">Designation</label>		
		<select required autofocus name = "Designation">
			<option value="">select</option>
	  		<option value="Admin">Admin</option>
	  		<option value="HR">HR</option>
	  		<option value="Accountant">Accountant</option>
	  		<option value="Consultant">Consultant</option>
	  		<option value="Developer">Developer</option>
		</select>
		<label for="Reason">PAN Number</label>
		<input type = "text" name = "pan" placeholder = "Enter the PAN" minlength="8" maxlength="8" required autofocus />
		<label for="Reason">Food Subscription</label>
		<select required autofocus name = "food">
			<option value="">Select</option>
	  		<option value="Y">Yes</option>
	  		<option value="N">No</option>
		</select>
		<label for="Reason">Cab Subscription</label>
			<select required autofocus name = "cab">
			<option value="">Select</option>
	  		<option value="Y">Yes</option>
	  		<option value="N">No</option>
		</select>
		<label for="Reason">Email</label>
		<input type = "email" name = "Email" placeholder = "Enter the Email" required autofocus />
		<input type = "submit" name = "Submit" value = "ADD">
	</form>
</body>
</html>