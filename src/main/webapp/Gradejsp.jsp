<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Grade</title>
<script>
function validate()
	      	{ 
		  		var y = document.forms["details"]["id"].value;
	      		var z = document.forms["details"]["grade"].value;
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
	      			alert("Not a valid id");
	      			return false;
	      		}
	      		if((z>10 || z<0))
	      		{
	      			alert("Not a valid grade");
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
	<center><h3> Grade Update</h3></center>
	<form action="AddgradeServlet" name="details"
		onsubmit="return validate()" method="GET">
		<label for="Reason">ID</label> <input type="text" name="id" id="value"
			placeholder="Enter the Id" min="1000" maxlength="4" required
			autofocus /> <span id="demo"></span> <label for="Reason">Grade</label>
		<input type="text" name="grade" placeholder="Enter the grade" required
			autofocus /> <input type="submit" name="Submit" value="Update">
	</form>
</body>
</html>