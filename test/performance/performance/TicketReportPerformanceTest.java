package performance;

import main.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class TicketReportPerformanceTest {

	private JPanel panel = new JPanel();
	private Main main = new Main();


	//Will open multiple threads
	@Test
	public void multipleTicketWindowsTest() {
		Assertions.assertDoesNotThrow(() -> main.jMenuItemTicketReportActionPerformed(null));
	}

}
