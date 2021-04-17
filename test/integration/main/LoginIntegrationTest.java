package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginIntegrationTest {

	private Login loginTester = new Login();

	@Test
	void testLoginWithMock() throws SQLException {


	}


	@Test
	void emptyLoginTest() throws Exception {

		Login loginMock = mock(Login.class);

		loginMock.setUsername("");
		loginMock.setPassword("alan1234");
		//stub
		when(loginMock.jButtonLoginActionPerformed(null)).thenReturn(false);
		Assertions.assertFalse(loginMock.jButtonLoginActionPerformed(null));

		loginMock.setUsername("alannorman00");
		loginMock.setPassword("");
		//stub
		when(loginMock.jButtonLoginActionPerformed(null)).thenReturn(false);
		Assertions.assertFalse(loginTester.jButtonLoginActionPerformed(null));
	}

	//Handle exceptions from mock database connection
	@Test
	void exceptionHandlerTest() {



	}

}
