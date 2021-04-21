package gui;

import main.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddflightGuiTest {

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
	public void validAddFlightTest() {
	window.menuItem("flightTab").click();
	window.menuItem("flight").click();
	window.textBox("flight").enterText("test");
	window.comboBox("source").selectItem(1);
	window.comboBox("depart").selectItem(2);
	window.panel("date").textBox().setText("Apr 21, 1997");
	window.textBox("departTime").enterText("4");
	window.textBox("arrival").enterText("5");
	window.textBox("charge").enterText("40");
	window.button("add").click();

}

	@Test
	public void invalidAddFlightTest() {
		window.menuItem("flightTab").click();
		window.menuItem("flight").click();
		window.textBox("flight").enterText("test");
		window.comboBox("source").selectItem(1);
		window.comboBox("depart").selectItem(2);
		window.panel("date").textBox().setText("Apr 21, 1997");
		window.textBox("departTime").enterText("4");
		window.textBox("arrival").enterText("");
		window.textBox("charge").enterText("40");
		window.button("add").click();
		window.optionPane().button().click();
	}

	@Test
	public void cancelButtonTest() {
		window.menuItem("flightTab").click();
		window.menuItem("flight").click();
		window.button("cancel").click();
	}

}
