package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Test class to verify methods in SearchCustomer
 * Created By: Alan Norman
 */
public class SearchCustomerTest extends SearchCustomer {


	//Reference variable to test the methods in Login
	private SearchCustomer searchTester = new SearchCustomer();
	private JPanel panel = new JPanel();

	/**
	 * @throws IOException
	 * Used to init a customer before testing
	 */
	@BeforeEach
	void initUpdateValues() throws IOException {
		searchTester.setTxtfirstname("TestFirstName");
		searchTester.setTxtlastname("TestLastName");
		searchTester.setTxtnic("111111111A");
		searchTester.setTxtpassport("999999");
		searchTester.setTxtaddress("TT");
		String dd = "1998-07-04";
		Date date = Date.valueOf(dd);
		searchTester.setTxtdob(date);
		searchTester.setRadioButtonMale(true);
		searchTester.setTxtcontact("7116");
		searchTester.setUserImageWithPath("img/testphoto.jpg");
	}

	/**
	 * Method to verify an invalid passport id wont work
	 */
	@Test
	public void invalidPassportNumberTest() {
		//invalid number by -1 digit
		searchTester.setTxtpassport("11111");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by +1 digit with letter
		searchTester.setTxtpassport("1111111");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by -1 digit and letter
		searchTester.setTxtpassport("111111A");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by +1 digit and letter
		searchTester.setTxtpassport("1111111A");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

	}

	/**
	 * Method to verify a invalid nic number wont work
	 */
	@Test
	public void invalidNicNumberTest() {
		//valid no letter
		searchTester.setTxtnic("111111111");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by -1 digit with letter
		searchTester.setTxtnic("1111111A");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by -1 digit and no letter
		searchTester.setTxtnic("1111111");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//invalid number by +1 digit and letter
		searchTester.setTxtnic("1111111111A");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

		//valid number with invalid letter combo
		searchTester.setTxtnic("111111111AA");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	/**
	 * Method to verify a file can be chosen
	 */
	@Test
	public void fileChooserTest() {
		Assertions.assertTrue(searchTester.jButtonBrowseActionPerformed(null));
	}

	/**
	 * Method to verify cancelling a customer
	 */
	@Test
	public void cancelCustomerTest() {
		searchTester.jButtonCancelActionPerformed(null);
	}

	/**
	 * Method to verify browsing an image works with a valid image path and returns an error with an invalid image path
	 */
	@Test
	public void browseImageTest() {
		searchTester.path = "img/testphoto.jpg";
		Assertions.assertTrue(searchTester.jButtonBrowseActionPerformed(null));

		searchTester.path = "img/tesphoto.jpg";
		Assertions.assertFalse(searchTester.jButtonBrowseActionPerformed(null));
	}

	/**
	 * Method to verify an empty first name causes an error
	 */
	@Test
	public void emptyFirstNameTest()  {
		//Empty first name
		searchTester.setTxtfirstname("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	/**
	 * Method to verify an empty last name causes an error
	 */
	@Test
	public void emptyLastNameTest() {
		//Empty last name
		searchTester.setTxtlastname("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

	}

	/**
	 *  Method to verify an empty nic causes an error
	 */
	@Test
	public void emptyNicTest() {
		//Empty nic value
		searchTester.setTxtnic("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	/**
	 * Method to verify an empty passport id causes an error
	 */
	@Test
	public void emptyPassportTest() {
		//Empty passport id
		searchTester.setTxtpassport("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	/**
	 * Method to verify an empty address causes an error
	 */
	@Test
	public void emptyAddressTest() {
		//Empty address
		searchTester.setTxtaddress("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	/**
	 * Method to verify an empty gender causes an error
	 */
	@Test
	public void emptyGenderTest() {
		searchTester.setRadioButtonMale(false);
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}


	/**
	 * Method used to verify that a customer updated successfully
	 */
	@Test
	public void updateCustomerTest() {

		searchTester.setRadioButtonFemale(true);
		searchTester.setRadioButtonMale(false);

		Assertions.assertTrue(searchTester.jButtonUpdateActionPerformed(null));
	}


	/**
	 * Method used to verify that a finding a customer was not successful
	 */
	@Test
	public void findInvalidCustomerTest() {
		searchTester.setTxtcustid("CS000");
		Assertions.assertFalse(searchTester.jButtonFindActionPerformed(null));
		searchTester.setTxtcustid("C019");
		Assertions.assertFalse(searchTester.jButtonFindActionPerformed(null));
	}


	/**
	 * Method used to verify that a finding a customer was successful
	 */
	@Test
	public void findValidCustomerTest() {
		searchTester.setTxtcustid("CS001");
		Assertions.assertTrue(searchTester.jButtonFindActionPerformed(null));

		searchTester.setTxtcustid("CS129");
		Assertions.assertTrue(searchTester.jButtonFindActionPerformed(null));
	}

	/**
	 * @throws SQLException
	 * Method to cover the SqlException when executing an update
	 */
	@Test
	public void updateExceptionTest() throws SQLException {

		searchTester.setTxtcustid("CS001");
		searchTester.setPst("Select");

		Assertions.assertThrows(SQLException.class, () -> searchTester.jButtonUpdateActionPerformed(null));
	}

}
