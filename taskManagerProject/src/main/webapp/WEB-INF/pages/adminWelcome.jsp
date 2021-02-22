<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Admin !</title>
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
		<form id="deleteuser" action="/deleteUser" method="post">
			<table>
				<tr>
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>Activity</th>
				</tr>
				<c:forEach var="task" items="${listUsers}">
					<tr>
						<td>${task.userId}</td>
						<td>${task.firstName}</td>
						<td>${task.lastName}</td>
						<td>${task.userName}</td>
						<td>${task.active}</td>
						<td><a href="deleteUser/${task.userId}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="<c:url value="/logout"/>">Logout</a>
		</form>
	</div>
</body>
</html>