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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Class for testing the integration of AddCustomer
 * Created By: Alan Norman
 */
public class AddCustomerIntegrationTest {

  @Mock
  private MysqlDataSource ds;

  @Mock
  private Connection c;

  @Mock
  private PreparedStatement stmt;

  @Mock
  private ResultSet rs;

  @Mock
  private Statement mockStatement;

  @Mock
  private JFileChooser mockChooser;

  private AddCustomer customerTester;
  private AutoCloseable closeable;

  /**
   * @throws Exception
   * Sets up a valid customer for testing
   */
  @BeforeEach
  public void setUp() throws Exception {

    //init mocks
    closeable = MockitoAnnotations.openMocks(this);

    Assertions.assertNotNull(ds);

    //add the mock source to AddCustomer
    customerTester = new AddCustomer(ds, mockChooser);

    customerTester.setTxtfirstname("Alan");
    customerTester.setTxtlastname("Norman");
    customerTester.setTxtnic("111111111B");
    customerTester.setTxtpassport("768991");
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
   * Used to close the mocks after each test
   */
  @AfterEach
  public void teardown() throws Exception {
    System.out.println("Closing");
    closeable.close();
  }


  /**
   * Verifies exceptions on AutoID
   */
  @Test
  public void autoIDExceptionTest() {

    AddCustomer newTest = new AddCustomer();
    newTest.setStatementString("Select * ");

    Assertions.assertThrows(SQLException.class, () -> newTest.autoID());


  }

  /**
   * Used to mock a valid file being selected
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
   * Used to mock a invalid file selection
   */
  @Test
  public void mockBrowseInvalidFileChooserTest() {

    File newFile = new File("img/testphot.jpg");

      when(mockChooser.showOpenDialog(null)).thenReturn(0);
      when(mockChooser.getSelectedFile()).thenReturn(newFile);

      Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));

  }

  /**
   * Mock the exceptions thrown by AutoId
   */
  @Test
  public void mockAutoIdExceptionTest()  {

    try {
      when(ds.getConnection()).thenReturn(c);
      when(c.createStatement()).thenReturn(mockStatement);
      when(mockStatement.executeQuery(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
      when(rs.next()).thenReturn(false);

      customerTester.autoID();

    } catch (SQLException e) {
      Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
    }


  }

  /**
   * @throws SQLException
   * Mock the Id being null when executing on the database
   */
  @Test
  public void mockAutoIdNullMaxIdTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery(any(String.class))).thenReturn(rs);
    when(rs.next()).thenReturn(true);

    customerTester.autoID();

    verify(rs, times(2)).getString(any(String.class));
  }


  /**
   * @throws SQLException
   * mock the autoId method to throw an exception
   */
  @Test
  public void mockAutoIdTest() throws SQLException {

    when(ds.getConnection()).thenReturn(c);
    when(c.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery(any(String.class))).thenReturn(rs);
    when(rs.next()).thenReturn(true);
    when(rs.getString(any(String.class))).thenReturn("CS020");
    customerTester.autoID();

    verify(rs, times(4)).getString(any(String.class));

  }

  /**
   * Mock the exceptions when adding a customer
   */
  @Test
  public void mockAddCustomerExceptionTest() {

    try {
      when(ds.getConnection()).thenReturn(c);
      when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));

      customerTester.jButtonAddActionPerformed(null);

    } catch(SQLException | NullPointerException e) {
      Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
    }

  }

  /**
   * @throws SQLException
   * Mock adding a valid customer
   */
  @Test
  public void mockAddValidCustomerTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeUpdate()).thenReturn(1);

    customerTester.jButtonAddActionPerformed(null);

    verify(stmt, times(1)).executeUpdate();
  }

  /**
   * @throws SQLException
   * Mock adding an invalid customer
   */
  @Test
  public void mockAddInvalidCustomerTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeUpdate()).thenReturn(1);

    customerTester.setTxtfirstname("");

    Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));


  }

  /**
   * @throws SQLException
   * Mock adding an invalid nic or passport
   */
  @Test
  public void mockAddInvalidNicOrPassportCustomerTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeUpdate()).thenReturn(1);

    customerTester.setTxtnic("111");

    Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

    customerTester.setTxtnic("111111111B");

    customerTester.setTxtpassport("111");

    Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));


  }

  /**
   * @throws IOException
   * Valid customer test
   */
  @Test
  public void validCustomerTest() throws IOException {

    AddCustomer newTest = new AddCustomer();

    newTest.setTxtfirstname("Alan");
    newTest.setTxtlastname("Norman");
    newTest.setTxtnic("111111111B");
    newTest.setTxtpassport("768991");
    newTest.setTxtaddress("US");
    String dd = "1997-08-02";
    Date date = Date.valueOf(dd);
    newTest.setTxtdob(date);
    newTest.setRadioButtonMale(true);
    newTest.setTxtcontact("715");
    newTest.setUserImageWithPath("img/testphoto.jpg");

    Assertions.assertTrue(newTest.jButtonAddActionPerformed(null));

    newTest.setRadioButtonFemale(true);
    newTest.setRadioButtonMale(false);

    Assertions.assertTrue(newTest.jButtonAddActionPerformed(null));
  }

}
