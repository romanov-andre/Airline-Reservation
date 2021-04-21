package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

public class UserCreationPerformanceTest {

	private JPanel panel = new JPanel();
	private Main main = new Main();
	private UserCreation userTest = new UserCreation();


	@BeforeEach
	void initUser() throws IOException {
		userTest.setTxtuserid("1234");
		userTest.setTxtfirstname("Test");
		userTest.setTxtlastname("Name");
		userTest.setTxtpassword("111111111");
		userTest.setTxtusername("testUser");
	}

	//Will open multiple threads
	@Test
	public void multipleTicketWindowsTest() {
		Assertions.assertDoesNotThrow(() -> main.jMenuItemUserCreationActionPerformed(null));
	}

//Should run one thread that creates multiple users
	@Test
	public void onePersonMakesMultipleUsersTest() {
		Assertions.assertTrue(userTest.jButtonAddActionPerformed(null));

	}

	//Should run multiple threads that all add one user
	@Test
	public void multiplePeopleMakeOneUserTest() {
		Assertions.assertTrue(userTest.jButtonAddActionPerformed(null));
	}

}
