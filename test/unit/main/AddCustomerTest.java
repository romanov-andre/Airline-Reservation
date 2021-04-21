package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Test class to verify methods in AddCustomer
 * Created By: Alan Norman
 */
public class AddCustomerTest {

	//Reference variable to test the methods in Login
	private AddCustomer customerTester = new AddCustomer();
	private JPanel panel = new JPanel();

	/**
	 * @throws IOException
	 * Used to init a customer before testing
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
	 * Method to verify a SQLexception is thrown in AutoID
	 */
	@Test
	public void autoIDExceptionTest() {

	customerTester.setStatementString("Select * ");

	Assertions.assertThrows(SQLException.class, () -> customerTester.autoID());

	}


	/**
	 * Method to verify an invalid passport id wont work
	 */
	@Test
	public void invalidPassportNumberTest() {
		//invalid number by -1 digit
		customerTester.setTxtpassport("11111");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by +1 digit with letter
		customerTester.setTxtpassport("1111111");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by -1 digit and letter
		customerTester.setTxtpassport("111111A");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by +1 digit and letter
		customerTester.setTxtpassport("1111111A");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

	}

	/**
	 * Method to verify a invalid nic number wont work
	 */
	@Test
	public void invalidNicNumberTest() {
		//valid no letter
		customerTester.setTxtnic("111111111");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by -1 digit with letter
		customerTester.setTxtnic("1111111A");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by -1 digit and no letter
		customerTester.setTxtnic("1111111");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//invalid number by +1 digit and letter
		customerTester.setTxtnic("1111111111A");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

		//valid number with invalid letter combo
		customerTester.setTxtnic("111111111AA");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}


	/**
	 * Method to verify an invalid photo will throw an error
	 */
	@Test
	void invalidPhotoTest() {
		customerTester.userimage = null;
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}


	/**
	 * Method to verify a file can be chosen
	 */
	@Test
	void fileChooserTest() {
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));
	}

	/**
	 * Method to verify cancelling a customer
	 */
	@Test
	void cancelCustomerTest()	 {
		customerTester.jButtonCancelActionPerformed(null);
	}


	/**
	 * Method to verify browsing an image works with a valid image path and returns an error with an invalid image path
	 */
	@Test
	void browseImageTest() {
		customerTester.path = "img/testphoto.jpg";
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));

		customerTester.path = "img/tesphoto.jpg";
		Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));
	}

	/**
	 * Method to verify an empty first name causes an error
	 */
	@Test
	void emptyFirstNameTest() {
		//Empty first name
		customerTester.setTxtfirstname("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	/**
	 * Method to verify an empty last name causes an error
	 */
	@Test
	void emptyLastNameTest() {
		//Empty last name
		customerTester.setTxtlastname("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

	}

	/**
	 * Method to verify an empty nic causes an error
	 */
	@Test
			void emptyNicTest() {
		//Empty nic value
		customerTester.setTxtnic("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	/**
	 * Method to verify an empty passport id causes an error
	 */
	@Test
	void emptyPassportTest() {
		//Empty passport id
		customerTester.setTxtpassport("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	/**
	 * Method to verify an empty address causes an error
	 */
	@Test
	void emptyAddressTest() {
		//Empty address
		customerTester.setTxtaddress("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}


	/**
	 * Method to verify an empty gender causes an error
	 */
	@Test
	void emptyGenderTest() {
		customerTester.setRadioButtonMale(false);
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	/**
	 * Method to verify contact matchers the desired pattern
	 */
	@Test
	void invalidContactTest() {
		customerTester.setTxtcontact("abc");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
		customerTester.setTxtcontact("a715c");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}


	/**
	 * Method to test adding a valid customer both male and female
	 */
	@Test
	void validCustomerTest() {
		Assertions.assertTrue(customerTester.jButtonAddActionPerformed(null));

		customerTester.setRadioButtonFemale(true);
		customerTester.setRadioButtonMale(false);

		Assertions.assertTrue(customerTester.jButtonAddActionPerformed(null));
	}

}
