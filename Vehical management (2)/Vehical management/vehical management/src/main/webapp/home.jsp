
<%
session.setAttribute("currentPage", "Home");


%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="common.css">
<title>Login</title>
<style>
main {
	display: flex;
	justify-content: center;
	align-items: center;
}

.card {
	flex-basis: 300px;
	margin: 30px auto;
	padding: 10px;
	width: 400px;
	position: relative;
	border-radius: 10px;
	box-shadow: 2px 2px 2px #888;
	background: #0804;
}

.x {
	position: absolute;
	border-radius: 0 5px 0 0;
	padding: 5px;
	top: 0;
	right: 0;
	text-decoration: none;
	color: green;
	background: white;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>


		<div class="card">
			<c:if test="${message != null}">
				<span style="color: green;"> ${message} </span>
				<br>
			</c:if>
			<a href='edit?empid=${empinfo.get("empid")}' class="x">edit</a>
			Employee ID: ${empinfo.get("empid")}<br> First Name:
			${empinfo.get("firstname")}<br> First Name:
			${empinfo.get("lastname")}<br> Email: ${empinfo.get("email")}<br>
			Salary: ${empinfo.get("salary")}<br>

		</div>
	</main>
</body>
</html>