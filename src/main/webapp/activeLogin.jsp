<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="LoginStyle.css">
<title>XYZ Pvt.LTD</title>
<%
int value=(int)session.getAttribute("value");
%>
<script type="text/javascript">
function validateForm() {
	  var y = document.forms["Loginform"]["npassword"].value;
	  var z = document.forms["Loginform"]["cpassword"].value;
	  if (y == "") {
	    alert("Password must be filled out");
	    return false;
	  }
	  if (z == "") {
		    alert("Password must be filled out");
		    return false;
		  }
	  if (y!=z) {
		    alert("Passwords must be same");
		    return false;
		  }

	}
</script>
</head>
<body> 
	<center><h1>Welcome</h1></center>
			<div class="container">
				
				<form action = "UpdatePasswordServlet" name = "Loginform" onsubmit="return validateForm()" >
					<div class="row">
      					<div class="col-25">
        					<label for="password">New Password</label>
      					</div>
      					<div class="col-75">
        					<input type = "password"  name="npassword" placeholder="Enter new password" >
      					</div>
    				</div>
    				<div class="row">
      					<div class="col-25">
        					<label for="password">Confirm Password</label>
      					</div>
      					<div class="col-75">
        					<input type = "password"  name="cpassword" placeholder="Renter the new password" >
      					</div>
    				</div>
    				<div class="row">
						<input type = "submit" value = "submit"> 
					</div>
				</form>
			</div>
</body>
</html>