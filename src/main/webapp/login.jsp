<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="LoginStyle.css">
<title>XYZ Pvt.LTD</title>
<%
	String value = (String) session.getAttribute("hello");
%>

<script type="text/javascript">
	function validateForm() {
		var x = document.forms["Loginform"]["username"].value;
		var y = document.forms["Loginform"]["password"].value;
		if (x == "") {
			alert("Username must be filled out");
			return false;
		}
		if (isNaN(x)) {
			alert('Not a Valid Id');
			return false;
		}
		if (y == "") {
			alert("Password must be filled out");
			return false;
		}
		if (x < 1000) {
			alert("Not a valid id");
			return false;
		}
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
				<%
					String result = (String) request.getParameter("result");
					if (result != null) {
						out.println("<p style=" + "color:red;" + ">" + result + "</p>");
					}
				%>
			</div>
		</form>
	</div>
</body>
</html>