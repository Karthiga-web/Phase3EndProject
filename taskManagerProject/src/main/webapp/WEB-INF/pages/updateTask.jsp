<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update the task</title>
</head>
<body>
	<div align="center">
		${message }
		<form id="updateDone" action="/updateDone" method="post">
			<table style="with: 50%">
				<tr>
					<td>Task ID</td>
					<td><input type="text" name="taskId" readonly value=${ taskId}></td>
				</tr>
				<tr>
					<td>Task Name</td>
					<td><input type="text" name="taskName" readonly
						value=${ taskName}></td>
				</tr>
				<tr>
					<td>Start Date</td>
					<td><input type="date" name="startDate" value=${ startDate}></td>
				</tr>
				<tr>
					<td>End Date</td>
					<td><input type="date" name="endDate" value=${ endDate}></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea id="description" name="description" rows="4"
							cols="50">${ description}</textarea></td>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value=${ email}></td>
				</tr>
				<tr>
					<td>Severity</td>
					<td><select name="severity" id="severity" readonly value=${ severity}>
							<option value="${ severity}">${ severity}</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" value="Update Task" />
		</form>
	</div>
</body>
</html>