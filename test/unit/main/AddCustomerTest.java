package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;

class AddCustomerTest {

	//Reference variable to test the methods in Login
	private AddCustomer customerTester = new AddCustomer();
	private JPanel panel = new JPanel();

	@BeforeEach
	void initCustomer() throws IOException {
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


	@Test
	void invalidPhotoTest() {
		customerTester.userimage = null;
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}

	//possible integration test
	@Test
	void fileChooserTest() {
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void cancelCustomerTest()	 {
		customerTester.jButtonCancelActionPerformed(null);
	}

	//possible integration test
	@Test
	void browseImageTest() {
		customerTester.path = "img/testphoto.jpg";
		Assertions.assertTrue(customerTester.jButtonBrowseActionPerformed(null));

		customerTester.path = "img/tesphoto.jpg";
		Assertions.assertFalse(customerTester.jButtonBrowseActionPerformed(null));
	}

	@Test
	void emptyFirstNameTest() {
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

	//contact needs error handling and black box testing
	@Test
	void invalidContactTest() {
		customerTester.setTxtcontact("abc");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
		customerTester.setTxtcontact("a715c");
		Assertions.assertFalse(customerTester.jButtonAddActionPerformed(null));
	}


	@Test
	void validCustomerTest() {
		Assertions.assertTrue(customerTester.jButtonAddActionPerformed(null));
	}

}
