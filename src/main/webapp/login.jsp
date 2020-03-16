<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="LoginStyle.css">
<title>XYZ Pvt.LTD</title>
<script type="text/javascript">
	function validateForm() {
		var username = document.forms["Loginform"]["username"].value;
		var password = document.forms["Loginform"]["password"].value;
		if (username == "") {
			alert("Username must be filled out");
			return false;
		}
		if (isNaN(username)) {
			alert('Not a Valid Id');
			return false;
		}
		if (password == "") {
			alert("Password must be filled out");
			return false;
		}
		event.preventDefault()
	    //Ajax call for rest api
	    let url = "http://localhost:8089/api/displayid";
	    //Ajax call for rest api
	    $.getJSON(url, function (data) {
	        for (var i of data) {
	            if (i == username) {
	            	document.forms["Loginform"].submit();
	            	return true;
	            } else {
	               continue;
	            }
	        }
	        alert("Not an employee ID");
	    });	
	}
</script>
<script type="text/javascript">
function loadProducts() {
    var id = document.forms["Loginform"]["username"].value;
    
}

</script>
</head>
<body>
	<center>
		<h1>Welcome</h1>
	</center>
	<div class="container">

		<form action="LoginServlet" name="Loginform"
			onsubmit="return validateForm()">
			<div class="row">
				<div class="col-25">
					<label for="uid">User ID</label>
				</div>
				<div class="col-75">
					<input type="text" name="username" placeholder="Enter the ID"
						min="1000" maxlength="4">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="password">Password</label>
				</div>
				<div class="col-75">
					<input type="password" name="password"
						placeholder="Enter the password">
				</div>
			</div>
			<div class="row">
				<input type="submit" value="submit">
				<c:if test="${not empty param.result}">
					<center>
						<p>
						<p style="color: red;">${param.result}</p>
						</p>
					</center>
				</c:if>
			</div>
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>
</html>