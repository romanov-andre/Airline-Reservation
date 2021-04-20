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
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

import static org.mockito.Mockito.*;

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


	@BeforeEach
	public void setUp() throws Exception {



		closeable = MockitoAnnotations.openMocks(this);

		Assertions.assertNotNull(ds);

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

	@AfterEach
	public void teardown() throws Exception {
		System.out.println("Closing");
		closeable.close();
	}

	@Test
	public void mockBrowseValidFileChooserTest() {
		File newFile = new File("img/testphoto.jpg");

		when(mockChooser.showOpenDialog(null)).thenReturn(0);
		when(mockChooser.getSelectedFile()).thenReturn(newFile);

		customerTester.jButtonBrowseActionPerformed(null);

		verify(mockChooser, times(2)).getSelectedFile();
	}

	@Test
	public void mockBrowseInvalidFileChooserTest() {
		File newFile = new File("img/testphot.jpg");

		when(mockChooser.showOpenDialog(null)).thenReturn(0);
		when(mockChooser.getSelectedFile()).thenReturn(newFile);

		Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));
	}


	@Test
	public void mockFindCustomerParseExceptionTest() throws SQLException {

			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeQuery()).thenReturn(rs);
			when(rs.next()).thenReturn(true);
			when(rs.getString("dob")).thenThrow(new ParseException("invalid date", 0));

}

	@Test
	public void mockFindCustomerSqlExceptionTest() throws SQLException {

		try {
			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));

			customerTester.jButtonFindActionPerformed(null);

		} catch (SQLException e) {
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");


		}

	}



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

	@Test
	public void mockInvalidFindCustomerTest() throws SQLException {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		customerTester.jButtonFindActionPerformed(null);

		verify(rs, times(0)).getString(any(String.class));

	}

	@Test
	public void mockValidCustomerUpdateTest() throws IOException, SQLException {



		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);

		customerTester.jButtonUpdateActionPerformed(null);

		verify(stmt, times(1)).executeUpdate();

	}

	@Test
	public void mockInvalidCustomerUpdateTest() throws IOException, SQLException {
		customerTester.setTxtfirstname("");

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);

		customerTester.jButtonUpdateActionPerformed(null);

		verify(stmt, times(0)).executeUpdate();
	}

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
