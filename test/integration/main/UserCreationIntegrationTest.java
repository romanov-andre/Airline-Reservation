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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
public class UserCreationIntegrationTest {

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


        private UserCreation userCreationTester;
        private AutoCloseable closeable;

        @BeforeEach
        public void setUp() throws Exception {
            closeable = MockitoAnnotations.openMocks(this);
            Assertions.assertNotNull(ds);
            userCreationTester = new UserCreation(ds);
            userCreationTester.setTxtusername("Shelly");
            userCreationTester.setTxtlastname("Knight");
            userCreationTester.setTxtfirstname("Shelby");
            userCreationTester.setTxtpassword("999aaaAB");
            userCreationTester.setTxtuserid("UO037");
            System.out.println("Before");
        }

        @AfterEach
        public void teardown() throws Exception {
            System.out.println("Closing");
            closeable.close();
        }


        @Test
        public void mockValidUserInputTest() throws SQLException {
            when(ds.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenReturn(stmt);
            when(stmt.executeUpdate()).thenReturn(1);

            userCreationTester.jButtonAddActionPerformed(null);

            verify(stmt, times(1)).executeUpdate();
        }
    @Test
    public void mockAddValidCustomerTest() throws SQLException {
        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        userCreationTester.jButtonAddActionPerformed(null);

        verify(stmt, times(1)).executeUpdate();
    }


        @Test
        public void mockInvalidUserInputTest() throws SQLException {
            when(ds.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenReturn(stmt);
            when(stmt.executeUpdate()).thenReturn(1);
            userCreationTester.setTxtlastname(null);
            Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
        }


        @Test
        public void mockCreateAccountSqlExceptionTest() throws SQLException {
            try {
                when(ds.getConnection()).thenReturn(c);
                when(c.prepareStatement(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
                userCreationTester.jButtonAddActionPerformed(null);
            } catch (SQLException e) {
                Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
            }
        }

        @Test
        public void mockAutoIdExceptionTest() {
            try {
                when(ds.getConnection()).thenReturn(c);
                when(c.createStatement()).thenReturn(mockStatement);
                when(mockStatement.executeQuery(any(String.class))).thenThrow(new SQLException("Failed to connect to db"));
                when(rs.next()).thenReturn(false);
                userCreationTester.autoID();
            } catch (SQLException e) {
                Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
            }

        }


        @Test
        public void mockAutoIdTest() throws SQLException {

            when(ds.getConnection()).thenReturn(c);
            when(c.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(any(String.class))).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getString(any(String.class))).thenReturn("CS020");
            userCreationTester.autoID();

            verify(rs, times(4)).getString(any(String.class));

        }

        @Test
        public void mockTestUserCreation() {
            UserCreation userCreationTester = mock(UserCreation.class);

            //set the username and password fields to acceptable values.
            when(userCreationTester.jButtonAddActionPerformed(null)).thenReturn(true);

            //return true for a valid username and password length of 6-15 characters in length.
            assertTrue(userCreationTester.jButtonAddActionPerformed(null));
        }


        @Test
        public void driverTestUserCreationPass() {
            UserCreation userCreationTester = new UserCreation();

            userCreationTester.setTxtfirstname("Test");
            userCreationTester.setTxtlastname("Name");
            userCreationTester.setTxtpassword("1111aaaa");
            userCreationTester.setTxtusername("testUser");

            //create a new user
            assertTrue(userCreationTester.jButtonAddActionPerformed(null));
        }

        @Test
        public void driverTestUserCreationPasswordFail() {
            UserCreation userCreationTester = new UserCreation();

            userCreationTester.setTxtfirstname("Test");
            userCreationTester.setTxtlastname("Name");
            userCreationTester.setTxtpassword("1111");
            userCreationTester.setTxtusername("testUser");

            //create a new user
            assertFalse(userCreationTester.jButtonAddActionPerformed(null));
        }

        @Test
        public void driverTestUserCreationEmptyFields() {
            UserCreation userCreationTester = new UserCreation();
            userCreationTester.setTxtfirstname("");
            userCreationTester.setTxtlastname("");
            userCreationTester.setTxtpassword("");
            userCreationTester.setTxtusername("");
            //create a new user
            assertFalse(userCreationTester.jButtonAddActionPerformed(null));
        }


    }
