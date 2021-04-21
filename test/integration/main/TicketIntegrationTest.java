package main;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
public class TicketIntegrationTest {

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
    private ResultSetMetaData rsmd;




    private Ticket ticketTest;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() throws Exception {


        closeable = MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(ds);
        ticketTest = new Ticket(ds);
        ticketTest.setTicketid("F001");
        ticketTest.setTxtfirstname("Test");
        ticketTest.setTxtlastname("Test");
        ticketTest.setPassport("test");
        ticketTest.setFlightid("12");
        ticketTest.setId("CS003");
        ticketTest.setFlightClass("Economy");
        ticketTest.settxtprice("10");
        ticketTest.setTxtseats(10);
        ticketTest.setTxttotal(500);
        ticketTest.setDate("2020-09-29");

        System.out.println("Before");

    }

    @AfterEach
    public void teardown() throws Exception {
        System.out.println("Closing");
        closeable.close();
    }




    @Test
    public void testSearchTicketButtonNegative(){
        ticketTest.setTxtSource("Usa");
        ticketTest.setTxtDepart("Usa");
        boolean checkResult = ticketTest.jButton3ActionPerformed(null);
        assertFalse(checkResult);
    }


    @Test
    public void testSearchTicketButton() throws SQLException, ClassNotFoundException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.getMetaData()).thenReturn(rsmd);
        when(rsmd.getColumnCount()).thenReturn(1);
        when(rs.next()).thenReturn(true);
        when(rs.next()).thenReturn(true).thenReturn(false);
        ticketTest.jButton3ActionPerformed(null);
        verify(stmt, times(1)).executeQuery();
    }
    @Test
    public void falseTicketSearch() throws SQLException, ClassNotFoundException{
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        ticketTest.jButton3ActionPerformed(null);

        verify(rs, times(0)).getString(any(String.class));
    }

    @Test
    public void mockValidTicketTest() throws SQLException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
        ticketTest.jButton1ActionPerformed(null);
        verify(stmt, times(1)).executeUpdate();
    }
    @Test
    public void mockInvalidTicketTest() throws SQLException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        ticketTest.setDate("2020-0909");

        Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));
    }

    @Test
    public void mockInvalidCustomer() throws SQLException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        ticketTest.setId(null);

        Assertions.assertFalse(ticketTest.jButton4ActionPerformed(null));
    }
    @Test
    public void mockValidCustomerSearch() throws SQLException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.getString("firstname")).thenReturn("A");
        when(rs.getString("lastname")).thenReturn("A");
        when(rs.getString("passport")).thenReturn("A");
        when(rs.next()).thenReturn(true);
        ticketTest.jButton4ActionPerformed(null);

        verify(rs, times(3)).getString(any(String.class));


    }

    @Test
    public void mockTicketExceptionTest()  {

        try {
            when(ds.getConnection()).thenReturn(c);
            when(c.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
            when(rs.next()).thenReturn(false);

        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
        }


    }
    @Test
    public void mockFLightSqlExceptionTest() throws SQLException {

        try {
            when(ds.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
            ticketTest.jButton3ActionPerformed(null);
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
        }
    }
    @Test
    public void mockSearchCustomerSqlExceptionTest() throws SQLException {
        try {
            when(ds.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
            ticketTest.jButton4ActionPerformed(null);
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
        }
    }
    @Test
    public void mockTicketSqlExceptionTest() throws SQLException {
        try {
            when(ds.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
            ticketTest.jButton1ActionPerformed(null);
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
        }
    }




    @Test
    public void mockAutoIdExceptionTest()  {

        try {
            when(ds.getConnection()).thenReturn(c);
            when(c.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
            when(rs.next()).thenReturn(false);

            ticketTest.autoID();

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

        ticketTest.autoID();

        verify(rs, times(2)).getString(any(String.class));
    }
    @Test
    public void mockAutoIdTest() throws SQLException {

        when(ds.getConnection()).thenReturn(c);
        when(c.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(any(String.class))).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getString(any(String.class))).thenReturn("CS020");
        ticketTest.autoID();

        verify(rs, times(4)).getString(any(String.class));

    }

}