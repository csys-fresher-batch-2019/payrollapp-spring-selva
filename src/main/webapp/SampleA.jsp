<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#d").click(function(){
    $("#div1").load("LeaveForm.jsp");
  });
});
</script>
<jsp:include page="headerNav.jsp" />
</head>
<body>

<div id="div1"><h2>Let jQuery AJAX Change This Text</h2></div>

<button >Get External Content</button>
<div class="btn-group">

<button id = "d">s</button>
<button>a</button>

</div>

<a href="/login.jsp">pdf</a>

</body>
</html>