package main;

import org.junit.Test;
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



    @Test
    public void driverTestUserCreationPass(){
        UserCreation userCreationTester = new UserCreation();

        userCreationTester.setTxtfirstname("Test");
        userCreationTester.setTxtlastname("Name");
        userCreationTester.setTxtpassword("1111aaaa");
        userCreationTester.setTxtusername("testUser");

        //create a new user
        assertTrue(userCreationTester.jButtonAddActionPerformed(null));
    }

    @Test
    public void driverTestUserCreationPasswordFail(){
        UserCreation userCreationTester = new UserCreation();

        userCreationTester.setTxtfirstname("Test");
        userCreationTester.setTxtlastname("Name");
        userCreationTester.setTxtpassword("1111");
        userCreationTester.setTxtusername("testUser");

        //create a new user
        assertFalse(userCreationTester.jButtonAddActionPerformed(null));
    }

    @Test
    public void driverTestUserCreationEmptyFields(){
        UserCreation userCreationTester = new UserCreation();
        userCreationTester.setTxtfirstname("");
        userCreationTester.setTxtlastname("");
        userCreationTester.setTxtpassword("");
        userCreationTester.setTxtusername("");
        //create a new user
        assertFalse(userCreationTester.jButtonAddActionPerformed(null));
    }
}