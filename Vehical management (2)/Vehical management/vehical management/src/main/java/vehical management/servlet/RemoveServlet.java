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

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;

	@Override
	public void init() {
		conn = DBHelper.getConnection(getServletContext());
		try {
			stmt = conn.prepareStatement("delete from employees where empid=?;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		int empid = Integer.parseInt(request.getParameter("empid"));

		RequestDispatcher rd;

		if (session.getAttribute("loggedInUser") == null) {
			System.out.println("user is not logged in, redirecting to Index page.");
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			return;

		}

		try {
			stmt.setInt(1, empid);
			int result = stmt.executeUpdate();

			if (result > 0)
				request.setAttribute("message", "Employee Removed Successfully");
			else
				request.setAttribute("message", "Employee Remove Failed");

			rd = request.getRequestDispatcher("manage");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
