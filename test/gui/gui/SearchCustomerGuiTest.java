package gui;

import main.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test Class for gui functions of search customer
 * Created By: Alan Norman
 */
public class SearchCustomerGuiTest {

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
	public void invalidFindCustomerButtonClickedTest()  {
		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.textBox("custId").enterText("CS01");
		window.button("find").click();


	}

	@Test
	public void validFindCustomerButtonClickedTest() {
		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.textBox("custId").enterText("CS001");
		window.button("find").click();

	}
	@Test
	public void validUpdateCustomerButtonClickedTest()  {
		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.textBox("firstname").enterText("Alan");
		window.textBox("lastname").enterText("Norman");
		window.textBox("nic").enterText("111111111B");
		window.textBox("passport").enterText("999999");
		window.textBox("address").enterText("Fl");
		window.panel("date").textBox().setText("Apr 21, 1997");
		window.radioButton("male").click();
		window.textBox("contact").enterText("715");
		window.button("update").click();

	}

	@Test
	public void invalidUpdateCustomerButtonClickedTest() {
		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.textBox("firstname").enterText("");
		window.textBox("lastname").enterText("Norman");
		window.textBox("nic").enterText("111111111B");
		window.textBox("passport").enterText("999999");
		window.textBox("address").enterText("Fl");
		window.panel("date").textBox().setText("Apr 21, 1997");
		window.radioButton("male").click();
		window.textBox("contact").enterText("715");
		window.button("update").click();
		window.optionPane().okButton();

	}

	@Test
	public void validCancelCustomerButtonClickedTest()  {

		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.button("cancel").click();


	}

	@Test
	public void browseButtonClickedTest() {

		window.menuItem("customerPanel").click();
		window.menuItem("searchCustomer").click();
		window.button("browse").click();

	}


}
