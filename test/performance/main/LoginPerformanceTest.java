package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class LoginPerformanceTest {

	private JPanel panel = new JPanel();
	Login loginTester= new Login();


//Will use Jmeter to open multiple threads
	@Test
	public void multipleLoginWindowsTest() {
		Assertions.assertDoesNotThrow(() -> loginTester.main(null));
	}


	//Will use jmeter to set up one user thread that tries to login 100 times with correct details
	@Test
	public void multipleSingleUserValidLoginAttemptsTest() {

		//incorrect login combo
		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");

		Assertions.assertDoesNotThrow(() -> loginTester.jButtonLoginActionPerformed(null));



	}

	//Will use jmeter to set up one user thread that tries to login 100 times with incorrect details
	@Test
	public void multipleSingleUserInvalidLoginAttemptsTest() {

		//incorrect login combo
		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan123");

		Assertions.assertDoesNotThrow(() -> loginTester.jButtonLoginActionPerformed(null));



	}

	//will use jmeter to setup 100 user threads and have them try to login all at once
	@Test
	public void multipleUsersValidLoginAttemptTest() {
		//correct login combo
		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan1234");

		Assertions.assertDoesNotThrow(() -> loginTester.jButtonLoginActionPerformed(null));

	}

	//will use jmeter to setup 100 user threads and have them try to login all at once
	@Test
	public void multipleUsersInvalidLoginAttemptTest() {
		//correct login combo
		loginTester.setUsername("alannorman00");
		loginTester.setPassword("alan123");

		Assertions.assertDoesNotThrow(() -> loginTester.jButtonLoginActionPerformed(null));

	}

}