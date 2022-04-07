package empsite.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import empsite.util.DBHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;

	@Override
	public void init() {
		conn = DBHelper.getConnection(getServletContext());
		try {
			stmt = conn.prepareStatement("select * from employees;");
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

		try {
		
			ResultSet rs = stmt.executeQuery();

			ArrayList<HashMap<String, String>> employees = new ArrayList<>();

			while (rs.next()) {
				
				HashMap<String, String> employee = new HashMap<>();
				employee.put("empid", ((Integer) rs.getInt(1)).toString());
				employee.put("firstname", rs.getString(2));
				employee.put("lastname", rs.getString(3));
				employee.put("email", rs.getString(4));
				employee.put("salary", ((Integer) rs.getInt(6)).toString());
				employee.put("admin",((Boolean) rs.getBoolean(7)).toString());
				employees.add(employee);
				
			}
			
			request.setAttribute("employees", employees);
			rd = request.getRequestDispatcher("manage.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
