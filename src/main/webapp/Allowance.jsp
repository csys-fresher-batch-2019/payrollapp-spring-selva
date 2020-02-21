<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Allowance</title>
<script>
function validate()
	      	{ 
		  		var y = document.forms["details"]["id"].value;
	      		var z = document.forms["details"]["allowance"].value;
	      		if(isNaN(y))
	      		{
	      			alert('Please input Numbers only');
	      			return false;
	      		}
	      		if(isNaN(z))
	      		{
	      			alert('Please input Numbers only');
	      			return false;
	      		}
	      		if(y<1000)
	      		{
	      			alert("Not a valid Id");
	      			return false;
	      		}
	      		if(z<1000)
	      		{
	      			alert("Not a valid Amount");
	      			return false;
	      		}
	      	}
</script>
<style type="text/css">
input[type=text],input[type=submit] {
  width: 60%;
  padding: 12px;
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
<form action = "AddAllowanceServlet" name = "details" onsubmit="return validate()">
<label for="Reason">ID</label>
	<input type = "text" name = "id" placeholder = "Enter the Id" min="1000"  maxlength="4" required autofocus />
<label for="Reason">Allowance</label>
	<input type = "text" name = "allowance" placeholder = "Enter the allowance" min="1000"  maxlength="4" required autofocus />
	<input type = "submit" name = "Submit" value = "Update">
</form>
</body>
</html>