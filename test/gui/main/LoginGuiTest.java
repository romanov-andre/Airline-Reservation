package main;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class LoginGuiTest {


Login loginTester = new Login();

@Test
public void validLoginButtonClickedTest() throws SQLException {

	loginTester.setUsername("alannorman00");
	loginTester.setPassword("alan1234");

	loginTester.getLoginButton().doClick();


	}

	@Test
	public void validCancelButtonClickedTest() throws SQLException {

		loginTester.getCancelButton().doClick();


	}
}