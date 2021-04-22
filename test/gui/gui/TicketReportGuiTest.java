package gui;

import main.Main;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test Class for gui functions of TicketReport
 * Created By: Alan Norman
 */
public class TicketReportGuiTest {

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
	public void openTicketReport() {
		window.menuItem("ticketPanel").click();
		window.menuItem("ticketReport").click();
		window.button("cancel").click();

	}


}
