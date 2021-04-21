package gui;

import main.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AddCustomerGuiTest {


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
	public void validAddCustomerButtonClickedTest() throws SQLException {
		window.menuItem("customerPanel").click();
		window.menuItem("addCustomer").click();
		window.textBox("firstname").enterText("Alan");
		window.textBox("lastname").enterText("Norman");
		window.textBox("nic").enterText("111111111B");
		window.textBox("passport").enterText("999999");
		window.textBox("address").enterText("Fl");
		window.panel("date").textBox().setText("Apr 21, 1997");
		window.radioButton("male").click();
		window.textBox("contact").enterText("715");
		window.button("add").click();

	}

	@Test
	public void invalidAddCustomerButtonClickedTest() throws SQLException {
		window.menuItem("customerPanel").click();
		window.menuItem("addCustomer").click();
		window.textBox("firstname").enterText("");
		window.textBox("lastname").enterText("Norman");
		window.textBox("nic").enterText("111111111B");
		window.textBox("passport").enterText("999999");
		window.textBox("address").enterText("Fl");
		window.panel("date").textBox().setText("Apr 21, 1997");
		window.radioButton("male").click();
		window.textBox("contact").enterText("715");
		window.button("add").click();
		window.optionPane().okButton();

	}

	@Test
	public void validCancelButtonClickedTest() throws SQLException {
		window.menuItem("customerPanel").click();
		window.menuItem("addCustomer").click();
		window.button("cancel").click();

	}

	@Test
	public void browseButtonClickedTest() throws SQLException {
		window.menuItem("customerPanel").click();
		window.menuItem("addCustomer").click();
		window.button("browse").click();
	}


}
