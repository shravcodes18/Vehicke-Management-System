package empsite.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import empsite.util.DBHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;
	
	@Override
	public void init() {
		ServletContext application = getServletContext();
		
		conn = DBHelper.getConnection(application);
		try {
			stmt = conn.prepareStatement("insert into employees values(?,?,?,?,?,?,?);");
		} catch (SQLException e) {
			System.out.println("sql compliation error");
			e.printStackTrace();
		}
	
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher rd;
		if (session.getAttribute("loggedInUser") == null) {
			System.out.println("user is not logged in, redirecting to Index page.");
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			return;

		} else 
			request.getRequestDispatcher("add.jsp").forward(request, response);
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Object loggedInUser = session.getAttribute("loggedInUser");

		RequestDispatcher rd;
		if (loggedInUser == null) {
			System.out.println("user is logged in, redirecting to home.");
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
			

		
		int empid = Integer.parseInt(request.getParameter("empid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int salary = Integer.parseInt(request.getParameter("salary"));

		
		try {
			stmt.setInt(1, empid);
			stmt.setString(2, firstname);
			stmt.setString(3, lastname);
			stmt.setString(4, email);
			stmt.setString(5, password);
			stmt.setInt(6, salary);
			stmt.setBoolean(7, false);
			int result = stmt.executeUpdate();
								
			if(result > 0) {
				request.setAttribute("message", "Employee Added");
						
			rd = request.getRequestDispatcher("manage");
			rd.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		


	}

}
