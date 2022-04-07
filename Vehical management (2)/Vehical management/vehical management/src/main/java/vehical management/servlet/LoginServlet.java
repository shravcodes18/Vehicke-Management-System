package empsite.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import empsite.util.DBHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;
	
	@Override
	public void init() {
		ServletContext application = getServletContext();
		
		conn = DBHelper.getConnection(application);
		try {
			stmt = conn.prepareStatement("select admin from employees where empid=? and password=?;");
		} catch (SQLException e) {
			System.out.println("sql compliation error");
			e.printStackTrace();
		}
	
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Object loggedInUser = session.getAttribute("loggedInUser");

		RequestDispatcher rd;
		if (loggedInUser != null) {
			System.out.println("user is logged in, redirecting to home.");
			rd = request.getRequestDispatcher("home.jsp");

		} else {
			System.out.println("user not logged in, redirecting to login.jsp");
			rd = request.getRequestDispatcher("login.jsp");
		}

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int empid = Integer.parseInt(request.getParameter("empid"));
		String password = request.getParameter("password");
		
		System.out.println("got authentication request from empid: " + empid + ", password: " + password);
		
		try {
			
			stmt.setInt(1, empid);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			boolean admin = false;
			
			
			
			int recordCount = 0;
			while(rs.next()) {
				recordCount++;
				admin = rs.getBoolean(1);
				System.out.println("Admin::::" + admin);
				
			}
			
			if(recordCount > 0) {
				// authentication success
				System.out.println("auth success");
				request.getSession().setAttribute("loggedInUser", empid);
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("home");
			} else {
				// authentication failure
				System.out.println("auth failed");
				request.setAttribute("loginFailed", true);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
