package main;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public class LoginGuiTest {

private FrameFixture window;

@BeforeEach
public void setUp() {
	Login frame = GuiActionRunner.execute((Callable<Login>) Login::new);

	window = new FrameFixture(frame);

	window.show();

	System.out.println("before");
}

@AfterEach
public void tearDown() {
window.cleanUp();
}

@Test
public void validLoginButtonClickedTest() throws SQLException {
window.textBox("txtuser").enterText("alannorman00");
window.textBox("txtpass").enterText("alan1234");
window.button("jButtonLogin").click();

	}

	@Test
	public void validCancelButtonClickedTest() throws SQLException {
		window.textBox("txtuser").enterText("alannorman00");
		window.textBox("txtpass").enterText("alan1234");
		window.button("jButtonLogin").click();

	}

}