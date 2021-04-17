package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;


class LoginTest {

	//Reference variable to test the methods in Login
	private Login loginTester = new Login();
	private JPanel panel = new JPanel();


	//A test to determine if correct values for username and password will function properly
	//The inputs are strings equivalent to a user that exists in the current Database
	@Test
	void positiveLoginTest() throws Exception {

		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");

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