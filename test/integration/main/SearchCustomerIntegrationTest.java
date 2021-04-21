package main;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.text.ParseException;

import static org.mockito.Mockito.*;

/**
 * Class for testing the integration of SearchCustomer
 * Created By: Alan Norman
 */
public class SearchCustomerIntegrationTest {

	@Mock
	private MysqlDataSource ds;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private JFileChooser mockChooser;

	private SearchCustomer customerTester;
	private AutoCloseable closeable;


	/**
	 * @throws Exception
	 * Sets up mocks and creates a valid customer for testing
	 */
	@BeforeEach
	public void setUp() throws Exception {

		//init mocks
		closeable = MockitoAnnotations.openMocks(this);

		Assertions.assertNotNull(ds);

		//pass mock source to search customer
		customerTester = new SearchCustomer(ds, mockChooser);

		customerTester.setTxtfirstname("Alan");
		customerTester.setTxtlastname("Norman");
		customerTester.setTxtnic("111111111B");
		customerTester.setTxtpassport("768994");
		customerTester.setTxtaddress("US");
		String dd = "1997-08-02";
		Date date = Date.valueOf(dd);
		customerTester.setTxtdob(date);
		customerTester.setRadioButtonMale(true);
		customerTester.setTxtcontact("715");
		customerTester.setUserImageWithPath("img/testphoto.jpg");

		System.out.println("Before");

	}

	/**
	 * @throws Exception
	 * Closes the mocks after each test
	 */
	@AfterEach
	public void teardown() throws Exception {
		System.out.println("Closing");
		closeable.close();
	}

	/**
	 * Mock a valid user of file browse
	 */
	@Test
	public void mockBrowseValidFileChooserTest() {
		File newFile = new File("img/testphoto.jpg");

		when(mockChooser.showOpenDialog(null)).thenReturn(0);
		when(mockChooser.getSelectedFile()).thenReturn(newFile);

		customerTester.jButtonBrowseActionPerformed(null);

		verify(mockChooser, times(2)).getSelectedFile();
	}

	/**
	 * Mock an invalid use of file browse
	 */
	@Test
	public void mockBrowseInvalidFileChooserTest() {
		File newFile = new File("img/testphot.jpg");

		when(mockChooser.showOpenDialog(null)).thenReturn(0);
		when(mockChooser.getSelectedFile()).thenReturn(newFile);

		Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));
	}


	/**
	 * @throws SQLException
	 * Mock the parseException when finding a customer with invalid date
	 */
	@Test
	public void mockFindCustomerParseExceptionTest() throws SQLException {

			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeQuery()).thenReturn(rs);
			when(rs.next()).thenReturn(true);
			when(rs.getString("dob")).thenThrow(new ParseException("invalid date", 0));

}

	/**
	 *
	 * Mock the exceptions when finding a customer
	 */
	@Test
	public void mockFindCustomerSqlExceptionTest() {

		try {
			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));

			customerTester.jButtonFindActionPerformed(null);

		} catch (SQLException e) {
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");


		}

	}


	/**
	 * @throws SQLException
	 * Mock finding a valid customer
	 */
	@Test
	public void mockValidFindCustomerTest() throws SQLException {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		when(rs.getString("dob")).thenReturn("1997-08-02");

		customerTester.jButtonFindActionPerformed(null);

		verify(rs, times(8)).getString(any(String.class));
		verify(rs, times(1)).getBlob(any(String.class));

	}

	/**
	 * @throws SQLException
	 * Mock and invalid customer update
	 */
	@Test
	public void mockInvalidFindCustomerTest() throws SQLException {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		customerTester.jButtonFindActionPerformed(null);

		verify(rs, times(0)).getString(any(String.class));

	}

	/**
	 *
	 * @throws SQLException
	 * Mock a valid customer update
	 */
	@Test
	public void mockValidCustomerUpdateTest() throws SQLException {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);

		customerTester.jButtonUpdateActionPerformed(null);

		verify(stmt, times(1)).executeUpdate();

	}

	/**
	 *
	 * @throws SQLException
	 * Mock updating customer with invalid credentials
	 */
	@Test
	public void mockInvalidCustomerUpdateTest() throws SQLException {
		customerTester.setTxtfirstname("");

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);

		customerTester.jButtonUpdateActionPerformed(null);

		verify(stmt, times(0)).executeUpdate();
	}

	/**
	 * Mock the exceptions when updating a customer
	 */
	@Test
	public void  mockCustomerUpdateExceptionTest() {

		try {
			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));

			customerTester.jButtonUpdateActionPerformed(null);

		} catch(SQLException e) {
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
		}

	}


}
