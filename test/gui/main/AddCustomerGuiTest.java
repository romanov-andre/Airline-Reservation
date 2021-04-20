package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

public class AddCustomerGuiTest {


	AddCustomer customerTester = new AddCustomer();

	@BeforeEach
	public void setUp() throws Exception {

		customerTester.setTxtfirstname("Alan");
		customerTester.setTxtlastname("Norman");
		customerTester.setTxtnic("111111111B");
		customerTester.setTxtpassport("768994");
		customerTester.setTxtaddress("US");
		String dd = "1997-08-02";
		Date date = Date.valueOf(dd);
		customerTester.setTxtdob(date);
		customerTester.setRadioButtonMale(true);
		customerTester.setTxtcontact("715");
		customerTester.setUserImageWithPath("img/testphoto.jpg");

		System.out.println("Before");

	}

	@Test
	public void validAddCustomerButtonClickedTest() throws SQLException {

		customerTester.getjButtonAdd().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void validCancelButtonClickedTest() throws SQLException {

		customerTester.getjButtonCancel().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void browseCancelButtonClickedTest() throws SQLException {

		Assertions.assertThrows(NullPointerException.class,() -> 	customerTester.getjButtonBrowse().doClick());

	}


	@Test
	public void validFemaleButtonClickedTest() throws SQLException {

		customerTester.getRadioButtonFemale().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void validMaleButtonClickedTest() throws SQLException {

		customerTester.getRadioButtonMale().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}
}
