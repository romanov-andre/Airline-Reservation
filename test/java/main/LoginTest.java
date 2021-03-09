package main;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;


class LoginTest {

	//Reference variable to test the methods in Login
	private Login loginTester = new Login();
	private JButton jButtonLogin = new JButton();
	private String testUsername;
	private String testPassword;
	private JPanel panel = new JPanel();

	//Allows for detection of exceptions within the main classes
	@Rule
	public final ExpectedException exception = ExpectedException.none();

//Test for determining if empty username and password fields will return an error prompt
//The inputs are empty strings and the expected result is a string stating the error prompt
	@Test
	void nullOrEmptyLoginTest() {

		//Set the fields to empty strings
		testUsername = "";
		testPassword = "";

		//Create the exception handler before the function is executed
		exception.expect(NullPointerException.class);
exception.expectMessage("One or more values entered are null or blank");

//The expected return string from the function being tested
String expectedOutput = "one or more blank fields";

//AssertEquals uses .equals to determine if 2 strings are the equal
//Pass the expected return result and the function call and will determine if they are the same
Assert.assertEquals(expectedOutput, loginTester.loginTester(testPassword, testUsername));

//This will only output if the test is successful and an error prompt is displayed
		System.out.println("Blank or null values are accounted for when logging in");
	}

	//A test to determine if correct values for username and password will function properly
	//The inputs are strings equivalent to a user that exists in the current Database
	@Test
	void positiveLoginTest() {

		//Set the fields to a current users account
		testUsername = "alannorman00";
		testPassword = "alan1234";

		//The expected return value will be a success string
		String expectedOutput = "success";

		//Compare the expected output to the string returned from executing the function
	  Assert.assertEquals(expectedOutput, loginTester.loginTester(testPassword, testUsername));

	  //This will only print if the test executed and the fields provided correspond to an active account
		System.out.println("The test was successful");

	}

	//This tests invalid usernames or passwords that aren't null or empty
	//The inputs are a correct password but an incorrect username
	@Test
	void invalidLoginTest() {

		//Set the fields so one is correct but the other doesn't match (can be either field that's incorrect)
		testUsername = "alannorman00";
		testPassword = "alan123";

		//This is the expected out put when the test is ran
		String expectedOutput = "username or password do not match";

		//Compare the expected output to the return value gathered from the function being executed
		Assert.assertEquals(expectedOutput, loginTester.loginTester(testPassword, testUsername));

		//This will only print if the test successfully detects an incorrect combination and displays the error prompt
		System.out.println("Testing invalid username and password combination leads to error prompt");


	}
}