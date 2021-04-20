package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;

public class SearchCustomerPerformanceTest {

	private JPanel panel = new JPanel();
	SearchCustomer customerTester = new SearchCustomer();
	Main testMain = new Main();


	@BeforeEach
	public void initCustomer() throws IOException {
		customerTester.setTxtfirstname("Alan");
		customerTester.setTxtlastname("Norman");
		customerTester.setTxtnic("343553433A");
		customerTester.setTxtpassport("768993");
		customerTester.setTxtaddress("US");
		String dd = "1997-08-02";
		Date date = Date.valueOf(dd);
		customerTester.setTxtdob(date);
		customerTester.setRadioButtonMale(true);
		customerTester.setTxtcontact("715");
		customerTester.setUserImageWithPath("img/testphoto.jpg");
	}

	@Test
	public void multipleSearchCustomerWindowsTest() {
		Assertions.assertDoesNotThrow(() -> testMain.jMenuItemSearchCusActionPerformed(null));

	}

	//Will use jmeter to set up one user thread that tries to add a valid customer 100 times
	@Test
	public void multipleSingleUserFindCustomerValidAttemptTest() {

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonFindActionPerformed(null));


	}

	//Will use jmeter to set up one user thread that tries to add a valid customer 100 times with incorrect details
	@Test
	public void multipleSingleUserFindCustomerInvalidAttemptTest() {

		customerTester.setTxtcustid("C001");

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonFindActionPerformed(null));

	}

	//will use jmeter to setup 100 user threads and have them try to add a valid customer all at once
	@Test
	public void multipleUsersValidFindCustomerAttemptTest() {

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonFindActionPerformed(null));

	}

	//will use jmeter to setup 100 user threads and have them try to add an invalid customer all at once
	@Test
	public void multipleUsersInvalidFindCustomerAttemptTest() {

		customerTester.setTxtcustid("C001");
		Assertions.assertDoesNotThrow(() -> customerTester.jButtonFindActionPerformed(null));

	}

	@Test
	public void multipleSingleUserValidUpdateAttemptTest() {

Assertions.assertDoesNotThrow(() -> customerTester.jButtonUpdateActionPerformed(null));

	}

	@Test
	public void multipleSingleUserInvalidUpdateAttemptTest() {
		customerTester.setTxtaddress("");
		Assertions.assertDoesNotThrow(() -> customerTester.jButtonUpdateActionPerformed(null));


	}

	@Test
	public void multipleUsersValidUpdateAttemptTest() {

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonUpdateActionPerformed(null));


	}

	@Test
	public void multipleUsersInvalidUpdateAttemptTest() {
		customerTester.setTxtaddress("");
		Assertions.assertDoesNotThrow(() -> customerTester.jButtonUpdateActionPerformed(null));
	}



}
