<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>

<%

HashMap<String, String> navItems = new HashMap<>();

if(session.getAttribute("loggedInUser") != null) {
	boolean admin = (Boolean) session.getAttribute("admin");
	System.out.println("admin: " + admin);
	if (admin == true) {
		navItems.put("Manage Employees", "manage");
		
	}
	navItems.put("Logout", "logout");
    
} else {

	navItems.put("Login", "login");
	
}

navItems.put("Home", "home");

request.setAttribute("navItems", navItems);

%>

<nav>
	<h2>EmpSite</h2>
	<ul>
		<c:forEach items="${navItems.entrySet()}" var="item">
			<li><c:choose>
					<c:when test="${currentPage.equals(item.getKey())}">
						<a class="activelink" href="${item.getValue()}">${item.getKey()}</a>
					</c:when>
					<c:otherwise>
						<a href="${item.getValue()}">${item.getKey()}</a>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>
	</ul>
</nav>



