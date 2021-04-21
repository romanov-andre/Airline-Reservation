package main;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketGuiTest {

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
	public void validCancelButtonClickedTest() {

		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.button("cancel").click();

	}


	@Test
	public void validAddTicketTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.textBox("custid").enterText("CS001");
		window.button("search2").click();
		window.textBox("flightnum").enterText("2");
		window.textBox("flightname").enterText("test");
		window.textBox("departtime").enterText("4");
		window.comboBox("class").selectItem(0);
		window.textBox("price").enterText("20");
		window.spinner("seats").increment(5);
		window.button("book").click();

	}

	@Test
	public void invalidAddTicketTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.textBox("flightnum").enterText("2");
		window.textBox("flightname").enterText("test");
		window.textBox("departtime").enterText("4");
		window.comboBox("class").selectItem(0);
		window.textBox("price").enterText("20");
		window.spinner("seats").increment(5);
		window.button("book").click();
		window.optionPane().button().click();
	}

	@Test
	public void validSearchUserTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.textBox("custid").enterText("CS001");
		window.button("search2").click();

	}

	@Test
	public void invalidSearchUserTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.textBox("custid").enterText("CS01");
		window.button("search2").click();
		window.optionPane().button().click();

	}

	@Test
	public void validSearchSourceTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.button("search").click();

	}

	@Test
	public void invalidSearchSourceTest() {
		window.menuItem("ticketPanel").click();
		window.menuItem("bookTicket").click();
		window.comboBox("source").selectItem(1);
		window.comboBox("depart").selectItem(1);
		window.button("search").click();
		window.optionPane().button().click();

	}


}
