
<%
session.setAttribute("currentPage", "Login");
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

	color: white;
}

form {

    margin: 30px auto;
    padding: 10px;
    width: 400px;
    
	display: flex;
	flex-direction: column;
	align-items: center;
	
	border-radius: 10px;
	box-shadow: 2px 2px 2px #888;
	
	background: #0804;

}




form label {
    margin: 5px;
    color: green
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


		<form action="login" method="post">
		     <c:if test="${loginFailed != null}">
           <span style="color: red;">Authentication Failed</span>
             </c:if>
			<label for="empid">Employee ID</label> <input class="inputbox"
				name="empid"> <label for="password">password</label> <input
				class="inputbox" type="password" name="password"> <input
				class="btn" type="submit">
		</form>
	</main>
</body>
</html>