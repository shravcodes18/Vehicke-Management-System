package empsite.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;

public class DBHelper {
	

	
	public static Connection getConnection(ServletContext application) {
		
		 
		String dbUserName = (String) application.getInitParameter("dbUserName");
		String dbPassword = (String) application.getInitParameter("dbPassword");
		String dbName = (String) application.getInitParameter("dbName");
		String dbUrl = "jdbc:mariadb://localhost:3306/" + dbName;
		
		
		System.out.println(dbUserName);
		System.out.println(dbPassword);
		System.out.println(dbName);
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("ERR: unable to connect to the database!");
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
}
