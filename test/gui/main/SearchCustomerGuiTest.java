package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

public class SearchCustomerGuiTest {

	SearchCustomer searchTester = new SearchCustomer();

	@BeforeEach
	public void setUp() throws Exception {

		searchTester.setTxtfirstname("Alan");
		searchTester.setTxtlastname("Norman");
		searchTester.setTxtnic("111111111B");
		searchTester.setTxtpassport("768994");
		searchTester.setTxtaddress("US");
		String dd = "1997-08-02";
		Date date = Date.valueOf(dd);
		searchTester.setTxtdob(date);
		searchTester.setRadioButtonMale(true);
		searchTester.setTxtcontact("715");
		searchTester.setUserImageWithPath("img/testphoto.jpg");

		System.out.println("Before");

	}

	@Test
	public void validFindCustomerButtonClickedTest() throws SQLException {

		searchTester.setTxtcustid("CS001");

		searchTester.getjButtonFind().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}
	@Test
	public void validUpdateCustomerButtonClickedTest() throws SQLException {

		searchTester.setTxtcustid("CS001");

		searchTester.getjButtonUpdate().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void validCancelCustomerButtonClickedTest() throws SQLException {



		searchTester.getjButtonCancel().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void browseCancelButtonClickedTest() throws SQLException {

		Assertions.assertThrows(NullPointerException.class,() -> 	searchTester.getjButtonBrowse().doClick());

	}

	@Test
	public void validFemaleButtonClickedTest() throws SQLException {

		searchTester.getRadioButtonFemale().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

	@Test
	public void validMaleButtonClickedTest() throws SQLException {

		searchTester.getRadioButtonMale().doClick();
		//Assertions.assertDoesNotThrow(() -> 	loginTester.getLoginButton().doClick());

	}

}
