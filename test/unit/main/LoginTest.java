package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;


class LoginTest {

	//Reference variable to test the methods in Login
	private Login loginTester = new Login();
	private JPanel panel = new JPanel();


	@Test
	public void clickLoginTest() {
		Assertions.assertDoesNotThrow(() -> loginTester.getjButtonLogin().doClick());
	}

	@Test
	public void clickCancelTest() {

		Assertions.assertDoesNotThrow(() -> loginTester.getjButtonCancel().doClick());

	}

	@Test
	public void loginMainTest() {

		String[] test = {"yo"};
		Assertions.assertDoesNotThrow(() -> loginTester.main(test));
	}

//Test for determining if empty username and password fields will return an error prompt
//The inputs are empty strings and the expected result is a string stating the error prompt
	@Test
	void EmptyLoginTest() throws Exception {

		loginTester.setUsername("");
		loginTester.setPassword("alan1234");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("");

		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

	}

	@Test
	void exceptionHandlerTest() throws SQLException {

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");
		loginTester.setPst("Select");

		Assertions.assertThrows(SQLException.class, () -> loginTester.jButtonLoginActionPerformed(null));

	}

	//A test to determine if correct values for username and password will function properly
	//The inputs are strings equivalent to a user that exists in the current Database
	@Test
	void positiveLoginTest() throws Exception {

		loginTester.setUsername("john");
		loginTester.setPassword("123");

		Assertions.assertTrue(loginTester.jButtonLoginActionPerformed(null));

	}

	//This tests invalid usernames or passwords that aren't null or empty
	//The inputs are a correct password but an incorrect username
	@Test
	void invalidLoginTest() throws Exception {

loginTester.setUsername("alannorman00");
loginTester.setPassword("alan123");

Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));

loginTester.setUsername("alannorman0");
loginTester.setPassword("alan1234");

Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));



	}
}