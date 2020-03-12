<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Message from server</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #D1CDCD;
	background-repeat: no-repeat;
}

.container {
	width: 60%;
	height: auto;
	display: block;
	background-color: #EFEAEA;
	border-radius: 15px;
	margin-left: 250px;
	color: #0F5CA6;
	margin-top: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<%
			String result = (String) request.getParameter("result");
			if (result != null) {
				out.println("<center><p><font size=6" + ">" + result + "</font></p></center>");
			}
		%>
	</div>
</body>
</html>
