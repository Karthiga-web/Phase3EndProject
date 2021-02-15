<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Go To Tasks Page</title>
</head>
<body>
	<div align="center">
		<form id="welcome"
			action="/welcome"
			method="post">
			<table style="with: 50%">
				<tr>
					<td>Create Task</td>
					<td><input type="submit" name="button" value="CreateTask" /></td>
					<td>View Tasks</td>
					<td><input type="submit" name="button" value="ViewTasks" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>