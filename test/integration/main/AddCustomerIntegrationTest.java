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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

  @BeforeEach
  public void setUp() throws Exception {

    closeable = MockitoAnnotations.openMocks(this);

    Assertions.assertNotNull(ds);

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

  @AfterEach
  public void teardown() throws Exception {
    System.out.println("Closing");
    closeable.close();
  }


  @Test
  public void invalidDateTest() {



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

  @Test
  public void mockAutoIdNullMaxIdTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery(any(String.class))).thenReturn(rs);
    when(rs.next()).thenReturn(true);

    customerTester.autoID();

    verify(rs, times(2)).getString(any(String.class));
  }


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

  @Test
  public void mockAddValidCustomerTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeUpdate()).thenReturn(1);

    customerTester.jButtonAddActionPerformed(null);

    verify(stmt, times(1)).executeUpdate();
  }

  @Test
  public void mockAddInvalidCustomerTest() throws SQLException {
    when(ds.getConnection()).thenReturn(c);
    when(c.prepareStatement(any(String.class))).thenReturn(stmt);
    when(stmt.executeUpdate()).thenReturn(1);

    customerTester.setTxtfirstname("");

    Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));


  }

}
