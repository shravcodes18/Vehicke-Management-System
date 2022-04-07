
<%
session.setAttribute("currentPage", "Manage Employees");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="common.css">
<title>Login</title>
<style>

.message {
    text-align: center;
    width: 100%;
    height: 30px;
    color: green;
    font-size: 20px;
    padding: 20px;
}

main {
	display: flex;
	flex-wrap: wrap;
}

.card {
	flex-basis: 300px;
	margin: 10px;
	padding: 10px;
	width: 400px;
	position: relative;
	border-radius: 10px;
	box-shadow: 2px 2px 2px #888;
	background: #0804;
	margin: 10px;
}

.x {
	position: absolute;
	border-radius: 0 5px 0 5px;
	padding: 5px;
	top: 0;
	right: 0;
	text-decoration: none;
	color: green;
	background: white;
}

.y {
	position: absolute;
	border-radius: 5px 0 5px 0;
	padding: 5px;
	bottom: 0;
	right: 0;
	text-decoration: none;
	color: green;
	background: white;
	right: 0;
}

.addnew {
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 60px;
	color: white;
	text-decoration: none;
}

</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="message"><c:out value="${message}" /></div></class>
	<main>

		<a href="add" class="card addnew">+</a>

		<c:forEach var="emp" items="${employees}">

			<div class="card">
				<a href='edit?empid=${emp.get("empid")}' class="x">edit</a> <a
					href='remove?empid=${emp.get("empid")}' class="y">x</a>
				Employee ID: ${emp.get("empid")}<br> First Name:
				${emp.get("firstname")}<br> Last Name: ${emp.get("lastname")}<br>
				Email: ${emp.get("email")}<br> Salary: ${emp.get("salary")}<br>

			</div>
		</c:forEach>
	</main>
</body>
</html>