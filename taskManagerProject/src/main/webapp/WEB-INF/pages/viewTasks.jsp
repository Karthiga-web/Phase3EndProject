<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Tasks</title>
<style>
#ex_table {
	table-layout: fixed !important;
}

#ex_table thead tr {
	width: 500px !important;
	position: fixed !important;
}

#ex_table tbody tr {
	width: 500px !important;
}

#ex_table th, td {
	width: 100px;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	text-align: center;
	vertical-align: text-bottom;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	vertical-align: bottom;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<div align="center">
		<form id="viewTasks" action="/viewTasks" method="post"
			modelAttribute="viewTasks">
			<table>
				<tr>
					<th>Task Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Description</th>
					<th>Email</th>
					<th>Severity</th>
					<th>Click to Update</th>
					<th>Click to Delete</th>
				</tr>
				<c:forEach var="task" items="${listTasks}">
					<tr>
						<td>${task.taskName}</td>
						<td>${task.startDate}</td>
						<td>${task.endDate}</td>
						<td>${task.description}</td>
						<td>${task.email}</td>
						<td>${task.severity}</td>
						<td><a href="updateTask/${task.taskId}">Edit</a></td>
						<td><a href="deleteTask/${task.taskId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" value="Create Task" />
		</form>
	</div>
</body>
</html>