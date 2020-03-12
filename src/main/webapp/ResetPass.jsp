<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<script>
	function validate() {
		var y = document.forms["details"]["id"].value;
		var letters = /^[A-Za-z]+$/;
		if (isNaN(y)) {
			alert('Please input Numbers only');
			return false;
		}
	}
</script>
<style type="text/css">
input[type=text], input[type=submit] {
	width: 60%;
	padding: 12px;
	border: 5px solid #ccc;
	border-radius: 5px;
	margin-left: 150px;
	margin-top: 20px;
}
</style>
</head>
<body>
	<form action="ResetPassServlet" name="details"
		onsubmit="return validate()">
		<input type="text" name="id" placeholder="Enter the Id" min="1000"
			maxlength="4" required autofocus /> <input type="submit"
			name="Submit" value="Reset">
	</form>
</body>
</html>