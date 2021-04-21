package main;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserCreationIntegrationTest {

	@Mock
	private PreparedStatement preparedStatement;

	@Mock
	private ResultSet resultSet;

	@Test
	public void mockTestUserCreation() {
		UserCreation userCreationTester = mock(UserCreation.class);

		//set the username and password fields to acceptable values.
		when(userCreationTester.jButtonAddActionPerformed(null)).thenReturn(true);

		//return true for a valid username and password length of 6-15 characters in length.
		assertTrue(userCreationTester.jButtonAddActionPerformed(null));
	}

//    @Test
//    public void mockUserCreationPass() throws Exception {
//        UserCreation userCreationTester = new UserCreation();
//
//        Connection con = mock(Connection.class);
//        PreparedStatement preparedStatement = mock(PreparedStatement.class);
//        mockStatic(DriverManager.class);
//
//        userCreationTester.setTxtfirstname("Test");
//        userCreationTester.setTxtlastname("Name");
//        userCreationTester.setTxtpassword("1111aaaa");
//        userCreationTester.setTxtusername("testUser");
//
//        when(con.prepareStatement(
//                "insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)")).thenReturn(preparedStatement);
//        expect(DriverManager.getConnection("jdbc:mysql://138-128-247-248.cloud-xip.io/Airline?serverTimezone = UTC",
//                "root", "Airline123456789")).andReturn(con);
//        expect(DriverManager.getConnection(null)).andReturn(null);
//
//        replay(DriverManager.class);
//
//        verify(preparedStatement.executeUpdate());
//    }

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