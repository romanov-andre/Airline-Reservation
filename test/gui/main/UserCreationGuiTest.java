package main;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserCreationGuiTest {

	private FrameFixture window;

	@BeforeEach
	public void setUp() throws Exception {
		Main frame = GuiActionRunner.execute(Main::new);

		window = new FrameFixture(frame);

		window.show();

	}

	@AfterEach
	public void tearDown() {
		window.cleanUp();
	}

@Test
	public void validUserCreationTest() {
	window.menuItem("user").click();
	window.menuItem("userCreation").click();
	window.textBox("first").enterText("test");
	window.textBox("last").enterText("test");
	window.textBox("username").enterText("test");
	window.textBox("password").enterText("1111111111");
	window.button("add").click();


}


@Test
public void invalidUserCreationTest() {
	window.menuItem("user").click();
	window.menuItem("userCreation").click();
	window.textBox("first").enterText("test");
	window.textBox("last").enterText("test");
	window.textBox("username").enterText("test");
	window.textBox("password").enterText("111");
	window.button("add").click();
	window.optionPane().button().click();

}

	@Test
	public void emptyUserCreationTest() {
		window.menuItem("user").click();
		window.menuItem("userCreation").click();
		window.textBox("first").enterText("test");
		window.textBox("last").enterText("test");
		window.textBox("username").enterText("test");
		window.textBox("password").enterText("");
		window.button("add").click();
		window.optionPane().button().click();

	}

@Test
	public void cancelButtonClicked() {
	window.menuItem("user").click();
	window.menuItem("userCreation").click();
	window.button("cancel").click();
}



}
