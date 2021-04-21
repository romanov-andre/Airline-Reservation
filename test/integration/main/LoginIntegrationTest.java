package main;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

/**
 * Class for testing the integration of Login
 * Created By: Alan Norman
 */
public class LoginIntegrationTest {

	@Mock
	private MysqlDataSource ds;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private JOptionPane pane;

	private Login loginTester;
	private AutoCloseable closeable;
	private Login loginExample = new Login();


	/**
	 * @throws Exception
	 * Used to set up mocks and initialize the login
	 */
@BeforeEach
public void setUp() throws Exception {

	closeable = MockitoAnnotations.openMocks(this);

	Assertions.assertNotNull(ds);

	loginTester = new Login(ds);

	System.out.println("Before");

}

	/**
	 * @throws Exception
	 * Used to close the mocks after each test
	 */
	@AfterEach
	public void teardown() throws Exception {
		System.out.println("Closing");
		closeable.close();
	}

	/**
	 * Running Logins main function to assert it works without exception
	 */
	@Test
	public void loginMainTest() {
		Assertions.assertDoesNotThrow(() -> loginExample.main(null));
	}

	/**
	 * @throws Exception
	 * Testing a positive login works
	 */
	@Test
	void positiveLoginTest() throws Exception {

		loginExample.setUsername("alannorman00");
		loginExample.setPassword("alan1234");

		Assertions.assertTrue(loginExample.jButtonLoginActionPerformed(null));

	}


	/**
	 * @throws Exception
	 * Mock a valid login
	 */
	@Test
	void testValidLoginWithMock() throws Exception {

	when(ds.getConnection()).thenReturn(c);
	when(c.prepareStatement(any(String.class))).thenReturn(stmt);
	when(stmt.executeQuery()).thenReturn(rs);
	when(rs.next()).thenReturn(true);

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");

		loginTester.jButtonLoginActionPerformed(null);

		verify(rs, times(1)).next();


	}

	/**
	 * @throws Exception
	 * Mock an invalid login
	 */
	@Test
	void testInvalidLoginWithMock() throws Exception {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan123");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));


	}

	/**
	 * @throws Exception
	 * Mock logging in with empty fields
	 */
	@Test
	void testEmptyLoginWithMock() throws Exception {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));


	}

	/**
	 * Handle exceptions from mock database connection
	 */
	@Test
	void exceptionHandlerTest() {

		try {

			when(ds.getConnection()).thenReturn(c);
			doNothing().when(stmt).setString(any(Integer.class), any(String.class));
			when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));

			loginTester.setUsername("alannorman00");
			loginTester.setPassword("alan1234");
			loginTester.jButtonLoginActionPerformed(null);

		} catch (SQLException e) {
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
		}

	}

}
