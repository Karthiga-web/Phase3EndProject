<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<div align="center">
		<form id="index" action="/decide"
			method="post">
			<table style="with: 50%">
				<tr>
					<td>Login</td>
					<td><input type="submit" name = "button" value="Login" /></td>
					<td>Register</td>
					<td><input type="submit" name = "button" value="Registration" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>