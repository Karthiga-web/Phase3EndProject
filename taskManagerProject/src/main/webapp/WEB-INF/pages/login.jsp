<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Here!</title>
</head>
<body>
	<div align="center">
		<form id="login" action="/login"
			method="post" modelAttribute="login">
			<table style="with: 50%">
				<tr>
					<td>UserName:</td>
					<td><input type="text" name="userName" required /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="password" required /></td>
				</tr>
			</table>
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>