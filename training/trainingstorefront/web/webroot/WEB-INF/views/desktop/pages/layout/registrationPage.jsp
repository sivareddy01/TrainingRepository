<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<script type="text/javascript">
	function validateform() {

		var mobile = document.forms["registrationForm"]["mobile"];
		var password = document.forms["registrationForm"]["password"];
		var email = document.forms["registrationForm"]["email"];
		if (mobile.value == "") {
			window.alert("Please enter your mobile number.");
			mobile.focus();
			return false;
		}
		if (mobile.value<10) {
			window.alert("Mobile number must be 10 digits");
			mobile.focus();
			return false;
		}
		if (mobile.value>10) {
			window.alert("Mobile number must be 10 digits");
			mobile.focus();
			return false;
		}
		else
			{
			return true;
			}
		if (password.value == "") {
			window.alert("Please enter your password");
			password.focus();
			return flase;
		}
		if (email.value == "")                                  
	    {
	        window.alert("Please enter e-mail address.");
	        email.focus();
	        return false;
	    }
	  
	    if (email.value.indexOf("@", 0) < 0)                
	    {
	        window.alert("Please enter a valid e-mail address.");
	        email.focus();
	        return false;
	    }
	  
	    if (email.value.indexOf(".", 0) < 0)                
	    {
	        window.alert("Please enter a valid e-mail address.");
	        email.focus();
	        return false;
	    }

	}
</script>















<body>
	<h1 align="center">
		<form action="register" method=post name="registrationForm"
			onsubmit="return validateform()">
			MobileNumber:<input type="text" name="mobile"><br />
			Password:<input type="password" name="password"><br>
			Email:<input type="text" name="email"><br> DateofBirth:<input
				type="text" name="dob"><br> <input type="submit"
				value="Register">
		</form>
	</h1>
</body>
</html>