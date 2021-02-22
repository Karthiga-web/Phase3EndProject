<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Here!</title>
</head>
<body>
	<div align="center">
		${message }
		<form id="login" action="login" method="post">
			<table style="with: 50%">
				<tr>
					<td>UserName:</td>
					<td><input type="text" name="userName" required /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="password" required /></td>
				</tr>
				<c:if test="${param.error ne null}">
					<div>Invalid username and password.</div>
				</c:if>
			</table>
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>