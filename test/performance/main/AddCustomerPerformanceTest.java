package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;

/**
 * Class for testing Addcustomer performance
 * Created By: Alan Norman
 */
public class AddCustomerPerformanceTest {


	private JPanel panel = new JPanel();
	AddCustomer customerTester = new AddCustomer();
	Main testMain = new Main();


	/**
	 * @throws IOException
	 */
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

	/**
	 * Will open multiple threads
	 */
	//
	@Test
	public void multipleAddCustomerWindowsTest() {
		Assertions.assertDoesNotThrow(() -> testMain.jMenuItemAddCusActionPerformed(null));
	}


	/**
	 * Will to set up one user thread that tries to add a valid customer 100 times
	 */
//
	@Test
	public void multipleSingleUserAddCustomerValidAttemptTest() {

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonAddActionPerformed(null));


	}

	/**
	 * Will to set up one user thread that tries to add a valid customer 100 times with incorrect details
	 */
	//
	@Test
	public void multipleSingleUserAddCustomerInvalidAttemptTest() {

		customerTester.setTxtnic("11111");

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonAddActionPerformed(null));

	}

	/**
	 * Will to setup 100 user threads and have them try to add a valid customer all at once
	 */

	@Test
	public void multipleUsersValidAddCustomerAttemptTest() {

		Assertions.assertDoesNotThrow(() -> customerTester.jButtonAddActionPerformed(null));

	}

	/**
	 * Will setup 100 user threads and have them try to add an invalid customer all at once
	 */
	@Test
	public void multipleUsersInvalidAddCustomerAttemptTest() {

		customerTester.setTxtnic("11111");
		Assertions.assertDoesNotThrow(() -> customerTester.jButtonAddActionPerformed(null));

	}


}
