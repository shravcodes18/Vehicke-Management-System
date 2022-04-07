package empsite.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import empsite.util.DBHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;

	@Override
	public void init() {
		conn = DBHelper.getConnection(getServletContext());
		try {
			stmt = conn.prepareStatement("select * from employees where empid=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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

		}

		int loggedInUser = (int) session.getAttribute("loggedInUser");
		System.out.println("user: " + loggedInUser + " logged in");
		try {
			stmt.setInt(1, loggedInUser);
			ResultSet rs = stmt.executeQuery();

			HashMap<String, String> empinfo = new HashMap<>();

			while (rs.next()) {
				empinfo.put("empid", ((Integer) rs.getInt(1)).toString());
				empinfo.put("firstname", rs.getString(2));
				empinfo.put("lastname", rs.getString(3));
				empinfo.put("email", rs.getString(4));
				empinfo.put("salary", ((Integer) rs.getInt(6)).toString());
				empinfo.put("admin",((Boolean) rs.getBoolean(7)).toString());
				
				
				request.setAttribute("empinfo", empinfo);

				rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
