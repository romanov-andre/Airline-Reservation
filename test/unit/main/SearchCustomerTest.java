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

	//TODO: add exception handling test for anything thrown


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

	//possible integration test
	@Test
	void fileChooserTest() {
		Assertions.assertTrue(searchTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void cancelCustomerTest() {
		searchTester.jButtonCancelActionPerformed(null);
	}

	//possible integration test
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


	//possible integration test
	@Test
	void updateCustomerTest() {

		searchTester.setTxtcustid("CS001");
		searchTester.jButtonFindActionPerformed(null);
		Assertions.assertTrue(searchTester.jButtonUpdateActionPerformed(null));
	}


	//possible integration test
	@Test
	void findInvalidCustomerTest() {
		searchTester.setTxtcustid("CS000");
		Assertions.assertFalse(searchTester.jButtonFindActionPerformed(null));
		searchTester.setTxtcustid("C019");
		Assertions.assertFalse(searchTester.jButtonFindActionPerformed(null));
	}

	//possible integration test
	@Test
	void findValidCustomerTest() {
		searchTester.setTxtcustid("CS001");
		Assertions.assertTrue(searchTester.jButtonFindActionPerformed(null));
	}

}
