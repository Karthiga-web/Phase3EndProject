<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<div align="center">
	${message }
		<form id="welcome" action="/welcome" method="post">
			<table style="with: 50%">
				<tr>
					<td>Create Task</td>
					<td><input type="submit" name="button" value="CreateTask" /></td>
					<td>View Tasks</td>
					<td><input type="submit" name="button" value="ViewTasks" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><a href="<c:url value="/logout"/>">Logout</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>