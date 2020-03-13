<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Base Salary</title>
<script>
			function validate()
		     { 
			  	var y = document.forms["details"]["id"].value;
		      	var x = document.forms["details"]["pay"].value;
		    	if(y<1000)
		      	{
		      		alert("Not a valid Id");
		      		return false;
		      	}
		      	if(isNaN(y))
		      	{
		      		alert('Please input Numbers only');
		      		return false;
		      	}
		      	if(isNaN(x))
		      	{
		      		alert('Please input Numbers only');
		      		return false;
		      	}
		      	if(x<10000)
		      	{
		      		alert("Not a valid Amount");
		      		return false;
		      	}
		      	else{
	      			return loadProducts();
	      		}
		     }
		</script>
<script>
    function loadProducts() {
        var id = document.getElementById("value").value;
        console.log(id);
        event.preventDefault()
        //Ajax call for rest api
        let url = "http://localhost:8089/api/displayid";
        //Ajax call for rest api
        $.getJSON(url, function (data) {
            console.log(data);
            for (var i of data) {
                console.log(i, id)
                if (i == id) {
                	$("#demo").empty();
                	document.forms["details"].submit();
                	return true;
                } else {
                   continue;
                }
            }
            $("#demo").html("ID doesn't exist");
        });
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

label {
	width: 60%;
	padding: 12px 0px 0px 0px;
	display: inline-block;
	margin-left: 150px;
	font-size: 120%;
}

#demo {
	color: red;
}
</style>
</head>
<body>
	<center>
		<h3>Basepay Update</h3>
	</center>
	<form action="AddbasePayServlet" name="details"
		onsubmit="return validate()" method="GET">
		<label for="Reason">ID</label> <input type="text" name="id" id="value"
			placeholder="Enter the Id" min="1000" maxlength="4" required
			autofocus /><span id="demo"></span> <label for="Reason">Base
			Salary</label> <input type="text" name="pay" placeholder="Enter the basepay"
			min="10000" maxlength="7" required autofocus /> <input type="submit"
			name="Submit" value="Update">
	</form>
</body>
</html>