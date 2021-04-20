package main;

public class TicketReportTest {

	private Ticketreport testReport = new Ticketreport();

	@Test
	public void LoadDataTest() {

		Assertions.assertTrue(testReport.LoadData());

	}

	@Test
	public void cancelReportTest() {

		Assertions.assertTrue(testReport.jButton1ActionPerformed(null));
	}

}

