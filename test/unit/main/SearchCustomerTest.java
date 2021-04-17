package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;

public class SearchCustomerTest extends SearchCustomer {


	//Reference variable to test the methods in Login
	private SearchCustomer searchTester = new SearchCustomer();
	private JPanel panel = new JPanel();

	@BeforeEach
	void initUpdateValues() throws IOException {
		searchTester.setTxtfirstname("TestFirstName");
		searchTester.setTxtlastname("TestLastName");
		searchTester.setTxtnic("11112");
		searchTester.setTxtpassport("999999");
		searchTester.setTxtaddress("TT");
		String dd = "1998-07-04";
		Date date = Date.valueOf(dd);
		searchTester.setTxtdob(date);
		searchTester.setRadioButtonMale(false);
		searchTester.setTxtcontact("7116");
		searchTester.setUserImageWithPath("img/testphoto.jpg");
	}

	@Test
	void fileChooserTest() {
		Assertions.assertTrue(searchTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void cancelCustomerTest() {
		searchTester.jButtonCancelActionPerformed(null);
	}

	@Test
	void browseImageTest() {
		searchTester.path = "img/testphoto.jpg";
		Assertions.assertTrue(searchTester.jButtonBrowseActionPerformed(null));

		searchTester.path = "img/tesphoto.jpg";
		Assertions.assertFalse(searchTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void emptyFirstNameTest() throws IOException {
		//Empty first name
		searchTester.setTxtfirstname("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}
	@Test
	void emptyLastNameTest() {
		//Empty last name
		searchTester.setTxtlastname("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));

	}

	@Test
	void emptyNicTest() {
		//Empty nic value
		searchTester.setTxtnic("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}
	@Test
	void emptyPassportTest() {
		//Empty passport id
		searchTester.setTxtpassport("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}
	@Test
	void emptyAddressTest() {
		//Empty address
		searchTester.setTxtaddress("");
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	@Test
	void emptyGenderTest() {
		searchTester.setRadioButtonMale(false);
		Assertions.assertFalse(searchTester.jButtonUpdateActionPerformed(null));
	}

	@Test
	void updateCustomerTest() {

		searchTester.setTxtcustid("CS001");
		searchTester.jButtonFindActionPerformed(null);
		Assertions.assertTrue(searchTester.jButtonUpdateActionPerformed(null));
	}

	@Test
	void findInvalidCustomerTest() {
		searchTester.setTxtcustid("CS000");
		Assertions.assertFalse(searchTester.jButtonFindActionPerformed(null));
	}

	@Test
	void findValidCustomerTest() {
		searchTester.setTxtcustid("CS001");
		Assertions.assertTrue(searchTester.jButtonFindActionPerformed(null));
	}

}
