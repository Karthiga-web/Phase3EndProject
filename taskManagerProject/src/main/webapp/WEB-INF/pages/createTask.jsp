<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a task</title>
</head>
<body>
	<div align="center">
		<form id="createTask"
			action="/createTask"
			method="post" modelAttribute="createTask">
			<table style="with: 50%">
				<tr>
					<td>Task Name</td>
					<td><input type="text" name="taskName" required /></td>
				</tr>
				<tr>
					<td>Start Date</td>
					<td><input type="date" name="startDate" required /></td>
				</tr>
				<tr>
					<td>End Date</td>
					<td><input type="date" name="endDate" required /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><input type="text" name="description" required /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" required /></td>
				</tr>
				<tr>
					<td>Severity</td>
					<td><select name="severity" id="severity" required>
							<option value="High">High</option>
							<option value="Medium">Medium</option>
							<option value="Low">Low</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" value="Create Task" />
		</form>
	</div>
</body>
</html>