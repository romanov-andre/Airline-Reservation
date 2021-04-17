package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

class AddCustomerTest {

	//Reference variable to test the methods in Login
	private AddCustomer customerTester = new AddCustomer();
	private JPanel panel = new JPanel();

	@BeforeEach
	void initCustomer() throws IOException {
		customerTester.setTxtfirstname("Alan");
		customerTester.setTxtlastname("Norman");
		customerTester.setTxtnic("34355343");
		customerTester.setTxtpassport("76899");
		customerTester.setTxtaddress("US");
		String dd = "1997-08-02";
		Date date = Date.valueOf(dd);
		customerTester.setTxtdob(date);
		customerTester.setRadioButtonMale(true);
		customerTester.setTxtcontact("715");
		customerTester.setUserImageWithPath("img/testphoto.jpg");
	}

	@Test
	void invalidPhotoTest() {
		customerTester.userimage = null;
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	@Test
	void invalidDateTest() {
		customerTester.setTxtdob(null);
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	@Test
	void fileChooserTest() {
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void cancelCustomerTest() {
		customerTester.jButtonCancelActionPerformed(null);
	}

	@Test
	void browseImageTest() {
		customerTester.path = "img/testphoto.jpg";
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));

		customerTester.path = "img/tesphoto.jpg";
		Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void emptyFirstNameTest() throws IOException {
		//Empty first name
		customerTester.setTxtfirstname("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}
	@Test
	void emptyLastNameTest() {
		//Empty last name
		customerTester.setTxtlastname("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));

	}

	@Test
			void emptyNicTest() {
		//Empty nic value
		customerTester.setTxtnic("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}
	@Test
	void emptyPassportTest() {
		//Empty passport id
		customerTester.setTxtpassport("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}
	@Test
	void emptyAddressTest() {
		//Empty address
		customerTester.setTxtaddress("");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	@Test
	void emptyGenderTest() {
		customerTester.setRadioButtonMale(false);
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	@Test
	void invalidContactTest() {
		customerTester.setTxtcontact("abc");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
		customerTester.setTxtcontact("a715c");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	@Test
	void validCustomerTest() throws ParseException, IOException {
Assertions.assertTrue(customerTester.jButtonAddActionPerformed(null));
	}

}
