<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Another User?</title>
</head>
<body>
	<form id="deleteAnotherUser" action="/deleteAnotherUser" method="post">
		<div align="center">
			${message } <br></br>
			<input type="submit" value="Click Here to Delete Another User" />
		</div>
	</form>
</body>
</html>