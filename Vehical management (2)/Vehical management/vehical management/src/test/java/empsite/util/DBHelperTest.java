package empsite.util;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.servlet.ServletContext;

@RunWith(MockitoJUnitRunner.class)
public class DBHelperTest {

	@Mock
	ServletContext application;
	
	@Test
	public void test() {
		
		
		when(application.getInitParameter("dbName")).thenReturn("empsite");
		when(application.getInitParameter("dbUserName")).thenReturn("root");
		when(application.getInitParameter("dbPassword")).thenReturn("");
		
		Connection conn = DBHelper.getConnection(application);
		
		assertNotEquals(conn, null);
	}

}
