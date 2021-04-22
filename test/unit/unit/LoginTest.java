package unit;

import main.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;


/**
 * Test class for the Login source class
 * Created by: Alan Norman
 */
class LoginTest {

	//Reference variable to test the methods in Login
	private Login loginTester = new Login();
	private JPanel panel = new JPanel();


	/**
	 * Method test the login button is clicked
	 */
	@Test
	public void clickLoginTest() {
		Assertions.assertDoesNotThrow(() -> loginTester.getjButtonLogin().doClick());
	}


	/**
	 * Method to test that the cancel button was clicked
	 */
	@Test
	public void clickCancelTest() {

		Assertions.assertDoesNotThrow(() -> loginTester.getjButtonCancel().doClick());

	}

	/**
	 * Method to test that login can be ran using main
	 */
	@Test
	public void loginMainTest() {

		String[] test = {"yo"};
		Assertions.assertDoesNotThrow(() -> loginTester.main(test));
	}

//Test for determining if empty username and password fields will return an error prompt
//The inputs are empty strings and the expected result is a string stating the error prompt


	/**
	 * Test for determining if empty username and password fields will return an error prompt
	 * The inputs are empty strings and the expected result is a string stating the error prompt
	 */
	@Test
	public void EmptyLoginTest() {

		//empty login
		loginTester.setUsername("");
		loginTester.setPassword("alan1234");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

		//empty login
		loginTester.setUsername("alannorman00");
		loginTester.setPassword("");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

	}

	/**
	 * @throws SQLException thrown when pst is set to Select
	 * Handles the exception branch of Logging in
	 */
	@Test
	public void exceptionHandlerTest() throws SQLException {

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");

		Assertions.assertDoesNotThrow(() -> loginTester.jButtonLoginActionPerformed(null));

	}

	/**
	 * Method to assert the login works properly
	 * A test to determine if correct values for username and password will function properly
	 * The inputs are strings equivalent to a user that exists in the current Database
	 */
	@Test
	public void positiveLoginTest() {

		loginTester.setUsername("john");
		loginTester.setPassword("123");

		Assertions.assertTrue(loginTester.jButtonLoginActionPerformed(null));

	}

	/**
	 * Method to determine if an invalid login combination provides an error
	 * This tests invalid usernames or passwords that aren't null or empty
	 * The inputs are a correct password but an incorrect username
	 */
	@Test
	public void invalidLoginTest() {

loginTester.setUsername("alannorman00");
loginTester.setPassword("alan123");

Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

loginTester.setUsername("alannorman0");
loginTester.setPassword("alan1234");

Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));



	}
}