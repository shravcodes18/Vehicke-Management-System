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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;
	PreparedStatement stmt2;
	
	@Override
	public void init() {
		ServletContext application = getServletContext();
		
		conn = DBHelper.getConnection(application);
		try {
			stmt = conn.prepareStatement("select * from employees where empid=?");
			stmt2 = conn.prepareStatement("update employees set firstname=?, lastname=?, email=?, password=? where empid=?");
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
		if (loggedInUser == null) {
			System.out.println("user is not logged in, redirecting to home.");
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
			
		int editTarget = Integer.parseInt(request.getParameter("empid"));
		request.setAttribute("editTarget", editTarget);
		
		try {
			stmt.setInt(1, editTarget);
			ResultSet rs = stmt.executeQuery();

			HashMap<String, String> empinfo = new HashMap<>();

			while (rs.next()) {
				empinfo.put("empid", ((Integer) rs.getInt(1)).toString());
				empinfo.put("firstname", rs.getString(2));
				empinfo.put("lastname", rs.getString(3));
				empinfo.put("email", rs.getString(4));
				empinfo.put("password", rs.getString(5));
				empinfo.put("salary", ((Integer) rs.getInt(6)).toString());
				empinfo.put("admin",((Boolean) rs.getBoolean(7)).toString());
				
				
				request.setAttribute("empinfo", empinfo);

				rd = request.getRequestDispatcher("edit.jsp");
				rd.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} 



	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
		
		int empid = Integer.parseInt(request.getParameter("empid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int salary = Integer.parseInt(request.getParameter("salary"));
		Boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		
	
		try {
			
			stmt2.setString(1, firstname);
			stmt2.setString(2, lastname);
			stmt2.setString(3, email);
			stmt2.setString(4, password);
			stmt2.setInt(5, empid);
			int result = stmt2.executeUpdate();
			
			

			
			if(result > 0) {
				// edit successful
				System.out.println("edit success");
				RequestDispatcher rd = request.getRequestDispatcher("manage");
				request.setAttribute("message", "Employee Record Updated Successfully");
		
			
				rd.forward(request, response);
			
				
			} else {
				// authentication failure
				System.out.println("Employee Record Update Failed");
				request.getRequestDispatcher("home").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
