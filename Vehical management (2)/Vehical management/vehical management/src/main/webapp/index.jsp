<%@ page language="java"%>

<%

session.setAttribute("currentPage", "Home");


%>

<html>
<head>
<title>EmpSite</title>
<link rel="stylesheet" href="common.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <p>Welcome to EmpSite, please login to view, modify your employees details or even manage other
    employees if you have sufficient permission.</p>
</body>
</html>
