
<%
session.setAttribute("currentPage", "Manage Employees");
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
    align-items: center;
    display: flex;
    flex-direction: column;
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

form .inputbox {
    border: none;
    border-radius: 5px;
    box-shadow: 2px 2px 2px #888;
    color: #888;
    padding: 7px 10px;
    margin: 5px;
}

form .btn {
    margin: 5px;
    padding: 5px 10px;
    background: green;
    color: white;
    border: solid 1px green;
    border-radius: 5px;
    
}

form .btn:hover {
    color: green;
    background: white;
}


</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>


		<form class="card" action="add" method="post">
		
			Employee ID: <input name="empid" class="inputbox" type="text" ><br>
			
            First Name: <input name="firstname" class="inputbox"  type="text" ><br>
            
            Last Name: <input  name="lastname" class="inputbox" type="text" ><br>
            
            Email: <input  name="email" class="inputbox" type="text" ><br>
            
            Password: <input type="password" name="password" class="inputbox" type="text" ><br>
            
            Salary: <input  name="salary" class="inputbox" type="text" ><br>
            
            
            <input class="btn" type="submit">

		</div>
	</main>
</body>
</html>